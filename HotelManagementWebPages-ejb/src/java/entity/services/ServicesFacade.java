/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.services;

import entity.AbstractFacade;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author trach
 */
@Stateless
public class ServicesFacade extends AbstractFacade<Services> implements ServicesFacadeLocal {
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ServicesFacade() {
        super(Services.class);
    }

    @Override
    public List<Services> searchName(String nameS) {
          List<Services> list = new ArrayList<>();
        Query q = em.createQuery("SELECT s FROM Services s WHERE s.serviceName LIKE :serviceName");
          q.setParameter("serviceName", "%"+nameS+"%");
          list = q.getResultList();
        return list;
    }

   
   
    
}
