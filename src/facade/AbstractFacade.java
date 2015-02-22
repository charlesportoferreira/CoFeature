package facade;

import cofeature.Feature;
import cofeature.Informacao;
import cofeature.Informacao2;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;

    }

    protected abstract SessionFactory getSessionFactory();

    public void save(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
    }

    public void edit(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(entity);
        transaction.commit();
        session.close();
    }

    public void remove(T entity) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    public T find(Long id) {
        Session session = getSessionFactory().openSession();
        T entity = (T) session.get(entityClass, id);
        session.close();
        return entity;
    }

    public List<T> findAll() {
        Session session = getSessionFactory().openSession();
        Criteria crit = session.createCriteria(entityClass);
        crit.addOrder(Order.asc("nome"));
        //crit.setMaxResults(50);
        List results = crit.list();
        session.close();
        return results;

    }

    public List<T> specialEdit(String nome, double infogain) {
        String query = "update feature set infogain = " + infogain + " where nome = '" + nome + "'";
        Session session = getSessionFactory().openSession();
        //Transaction transaction = session.beginTransaction();
        session.createSQLQuery(query).executeUpdate();
        //transaction.commit();
        session.close();
        return null;

    }

    public List<T> findAll(double infogain) {
        Session session = getSessionFactory().openSession();
        List results = session.createSQLQuery("SELECT * FROM feature where infogain >= " + infogain).addEntity(entityClass).list();
        session.close();
        return results;

    }

    public List<T> findAll(double infogain, String classe) {
        Session session = getSessionFactory().openSession();
        List results = session.createSQLQuery("SELECT * FROM feature where infogain >= " + infogain
                + " and nomeClasseOrigem = " + "'" + classe + "'").addEntity(entityClass).list();
        session.close();
        return results;

    }

    public List<T> findAll(double dominancia, String classe, String arquivo) {
        Session session = getSessionFactory().openSession();
        List results = session.createSQLQuery("SELECT * FROM cofeature where dominancia >= " + dominancia
                + " and nomeClasseOrigem = " + "'" + classe + "'" + " and nomeArquivoOrigem = "
                + "'" + arquivo + "'").addEntity(entityClass).list();
        session.close();
        return results;

    }

    public List<T> findAllSQL() {
        Session session = getSessionFactory().openSession();
        List results = session.createSQLQuery("SELECT * FROM feature where nome = 'house'").addEntity(entityClass).list();
        session.close();
        return results;
    }

    public List<Informacao> findAllSQLTeste(String nome) {
        List<Informacao> dados = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        String query = "SELECT nome, nomeClasseOrigem, count(*) FROM feature where nome = " + "'" + nome + "'"
                + " group by nomeClasseOrigem";
        List results = session.createSQLQuery(query).list();
        session.close();
        for (Iterator it = results.iterator(); it.hasNext();) {
            Object[] obj = (Object[]) it.next();
            // System.out.println(obj[0]);
            dados.add(new Informacao((String) obj[0], (String) obj[1], (BigInteger) obj[2]));
        }
        return dados;
    }

    public List<Informacao2> findAllSQLTeste2(List<String> nomeClasses) {
        String subQuery = "select nome";
        for (String nomeClasse : nomeClasses) {
            subQuery += ", sum(if(nomeclasseorigem = '" + nomeClasse + "',1,0)) as '" + nomeClasse + "'";
        }
        subQuery += " from cofeature group by nome order by nome";
        List<Informacao2> dados = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        List results = session.createSQLQuery(subQuery).list();
        session.close();
        System.out.println("Processando dados lidos");
        for (Iterator it = results.iterator(); it.hasNext();) {
            Object[] obj = (Object[]) it.next();
            Informacao2 info2 = new Informacao2((String) obj[0]);
            for (int i = 1; i < obj.length; i++) {
                info2.add((BigDecimal) obj[i]);
            }
            // System.out.println(obj[0]);"
            dados.add(info2);
        }
        return dados;
    }
    
    
    public List<Informacao2> findAllSQLTeste3(List<String> nomeClasses) {
        String subQuery = "select nome";
        for (String nomeClasse : nomeClasses) {
            subQuery += ", sum(if(nomeclasseorigem = '" + nomeClasse + "',1,0)) as '" + nomeClasse + "'";
        }
        subQuery += " from feature group by nome order by nome";
        List<Informacao2> dados = new ArrayList<>();
        Session session = getSessionFactory().openSession();
        List results = session.createSQLQuery(subQuery).list();
        session.close();
        for (Iterator it = results.iterator(); it.hasNext();) {
            Object[] obj = (Object[]) it.next();
            Informacao2 info2 = new Informacao2((String) obj[0]);
            for (int i = 1; i < obj.length; i++) {
                info2.add((BigDecimal) obj[i]);
            }
            // System.out.println(obj[0]);"
            dados.add(info2);
        }
        return dados;
    }
    
    
    
    
    
    
    
    

//    public List<T> findRange(int[] range) {
//        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
//        cq.select(cq.from(entityClass));
//        javax.persistence.Query q = getEntityManager().createQuery(cq);
//        q.setMaxResults(range[1] - range[0] + 1);
//        q.setFirstResult(range[0]);
//        return q.getResultList();
//    }
    public int count() {
        Session session = getSessionFactory().openSession();
        Criteria crit = session.createCriteria(entityClass);
        int count = ((Number) crit.setProjection(Projections.rowCount())
                .uniqueResult()).intValue();
        session.close();
        return count;
    }
}
