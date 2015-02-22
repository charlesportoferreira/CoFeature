/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import cofeature.Feature;
import controller.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class FeatureFacade extends AbstractFacade<Feature>{

    public FeatureFacade(){
        super(Feature.class);
    }
    
    @Override
    protected SessionFactory getSessionFactory(){
        return HibernateUtil.getSessionFactory();
    }

//   public List<Feature> findAllSQL() {
//        Session session = getSessionFactory().openSession();
//        List results = session.createSQLQuery("SELECT * FROM feature where nome = 'house'").addEntity(Feature.class).list();
//        session.close();
//        return results;
//    }
   
}
