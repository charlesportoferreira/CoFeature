/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import cofeature.CoFeature;
import controller.HibernateUtil;
import org.hibernate.SessionFactory;

/**
 *
 * @author charleshenriqueportoferreira
 */
public class CoFeatureFacade extends AbstractFacade<CoFeature> {

    public CoFeatureFacade() {
        super(CoFeature.class);
    }

    @Override
    protected SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }
}
