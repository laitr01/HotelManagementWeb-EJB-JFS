/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.feedback;

import entity.AbstractFacade;
import entity.customer.Customer;
import entity.employee.Employee;
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
public class FeedbackFacade extends AbstractFacade<Feedback> implements FeedbackFacadeLocal {
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FeedbackFacade() {
        super(Feedback.class);
    }

    @Override
    public List<Feedback> searchFromId(int fromId) {
         List<Feedback> list = new ArrayList<Feedback>();
        Query q = em.createQuery("SELECT f FROM Feedback f WHERE f.feedbackID >= :fromId");
        q.setParameter("fromId", fromId);
        list = q.getResultList();
        return list;
    }
    @Override
    public List<Employee> getEmployeeID(String userName) {
         Query query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName");
        query.setParameter("userName", userName);
        query.setMaxResults(1);
        return query.getResultList();
    }

    @Override
    public List<Customer> getCustomerID(String fullName) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.fullName = :fullName");
        query.setParameter("fullName", fullName);
        query.setMaxResults(1);
        return query.getResultList();
    }

    
}
