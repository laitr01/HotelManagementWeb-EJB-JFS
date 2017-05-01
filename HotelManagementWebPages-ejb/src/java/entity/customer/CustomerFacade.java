/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.customer;

import entity.AbstractFacade;
import entity.room.RoomType;
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
public class CustomerFacade extends AbstractFacade<Customer> implements CustomerFacadeLocal {
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }
    @Override
    public boolean login(String email, String pass) {
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email and c.passWord =:pass");
        try {
            q.setParameter("email", email);
            q.setParameter("pass", pass);
            Customer cus = (Customer)q.getSingleResult();
            if(cus != null){
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    @Override
    public boolean checkstatus(String email){
        String sql="SELECT c FROM Customer c WHERE c.email = :email";
        Query q=em.createQuery(sql);
        try{
            q.setParameter("email", email);
            
            Customer cus = (Customer) q.getSingleResult();
            if(cus.getStatus().equals("Y"))
                return true;
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean upPass(String pass, int cusId){
        Query q = em.createQuery("UPDATE Customer c SET c.passWord =:pass WHERE c.customerID = :cusId");
        try{
            q.setParameter("pass", pass);
            q.setParameter("cusId", cusId);
            return q.executeUpdate()>0?true:false;
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public boolean lock(int cusid){
        Query q = em.createQuery("UPDATE Customer c SET c.status ='N' WHERE c.customerID = :cusId");
        try{
            q.setParameter("cusId", cusid);
            return q.executeUpdate()>0?true:false;
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public boolean unLock(int cusid){
        Query q = em.createQuery("UPDATE Customer c SET c.status ='Y' WHERE c.customerID = :cusId");
        try{
            q.setParameter("cusId", cusid);
            return q.executeUpdate()>0?true:false;
        } catch (Exception e) {
        }
        return false;
    }
    
    @Override
    public Customer findEmail(String email){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
        try {
            q.setParameter("email", email);
            
            Customer cus = (Customer)q.getSingleResult();
            if(cus != null){
                return cus;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    public Customer findCustomerByEmail(String email) {       
        String sql = "SELECT c FROM Customer c WHERE c.email = :email";
        Query q = em.createQuery(sql);
        try{
            q.setParameter("email", email);            
            return (Customer) q.getSingleResult();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public List<Customer> getCustomerID(String fullName) {
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.fullName = :fullName");
        query.setParameter("fullName", fullName);
        query.setMaxResults(1);
        return query.getResultList();
    }
    
    @Override
    public boolean duplicateEmail(String email){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email");
        try {
            q.setParameter("email", email);

            Customer cus = (Customer) q.getSingleResult();
            if (cus != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    @Override
    public boolean duplicateIdentifier(String identifier){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.personIdentifier = :personIdentifier");
        try {
            q.setParameter("personIdentifier", identifier);

            Customer cus = (Customer) q.getSingleResult();
            if (cus != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    @Override
    public boolean duplicatePhone(String phone){
        Query q = em.createQuery("SELECT c FROM Customer c WHERE c.phone = :phone");
        try {
            q.setParameter("phone", phone);

            Customer cus = (Customer) q.getSingleResult();
            if (cus != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
        
}
