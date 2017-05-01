/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.reservation;

import entity.AbstractFacade;
import entity.customer.Customer;
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
public class ReservationDetailFacade extends AbstractFacade<ReservationDetail> implements ReservationDetailFacadeLocal {
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservationDetailFacade() {
        super(ReservationDetail.class);
    }

    @Override
    public ReservationDetail findResDetailByResId(int resId) {        
        String sql = "SELECT r FROM ReservationDetail r WHERE r.reservationDetailPK.reservationID = :reservationID";
        Query q = em.createQuery(sql);
        try{
            q.setParameter("reservationID", resId);            
            return (ReservationDetail) q.getSingleResult();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ReservationDetail findResDetailByResIdAndRoomId(int resId, int roomId) {
        String sql = "SELECT r FROM ReservationDetail r WHERE r.reservationDetailPK.reservationID = :reservationID and r.reservationDetailPK.roomID = :roomID";
        Query q = em.createQuery(sql);
        try{
            q.setParameter("reservationID", resId);     
            q.setParameter("roomID", roomId); 
            return (ReservationDetail) q.getSingleResult();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
    
}
