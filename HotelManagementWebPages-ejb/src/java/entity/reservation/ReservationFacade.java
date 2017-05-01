/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.reservation;

import entity.AbstractFacade;
import entity.customer.Customer;
import entity.customer.CustomerFacadeLocal;
import entity.employee.EmployeeFacadeLocal;
import entity.room.Rooms;
import entity.room.RoomsFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author trach
 */
@Stateless
public class ReservationFacade extends AbstractFacade<Reservation> implements ReservationFacadeLocal {
    @EJB
    private CustomerFacadeLocal customerFacade;
    @EJB
    private RoomsFacadeLocal roomsFacade;
    
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationFacade() {
        super(Reservation.class);
    }

    @Override
    public List<Rooms> getAllRooms() {
        return roomsFacade.findAll();
    }
    @Override
    public List<Reservation> getUserReservation(String email) {
        Customer customer = getCustomerByEmail(email);
        
        String sql = "SELECT r FROM Reservation r WHERE r.customerID = :customerID";
        Query q = em.createQuery(sql);
        try{
            q.setParameter("customerID", customer);            
            return q.getResultList();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
     
    public Customer getCustomerByEmail(String email){
        return customerFacade.findCustomerByEmail(email);
    }

    @Override
    public boolean updateStatus(Reservation reservation) {
        try{
            this.edit(reservation);
            return true;
        }catch(Exception ex){
            return false;
        }        
    }

   
}
