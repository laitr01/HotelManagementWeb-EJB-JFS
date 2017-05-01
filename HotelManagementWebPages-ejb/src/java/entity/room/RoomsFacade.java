/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.room;

import entity.AbstractFacade;
import entity.employee.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author trach
 */
@Stateless
public class RoomsFacade extends AbstractFacade<Rooms> implements RoomsFacadeLocal {
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomsFacade() {
        super(Rooms.class);
    }
    
    @Override
    public boolean searchRoomType(int roomtype){
        Query q = em.createQuery("SELECT r FROM RoomType r WHERE r.roomTypeID = :typeid");
        try {
            q.setParameter("typeid", roomtype);

            RoomType rtype = (RoomType) q.getSingleResult();
            if (rtype != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    
    @Override
   public boolean searchRoomName(String rooname){
        Query q = em.createQuery("SELECT r FROM Rooms r WHERE r.roomName = :roomName");
        try {
            q.setParameter("roomName", rooname);

            Rooms rtype = (Rooms) q.getSingleResult();
            if (rtype != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }
           
    
}
