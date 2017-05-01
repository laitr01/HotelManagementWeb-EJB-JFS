/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.room;

import entity.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author trach
 */
@Stateless
public class RoomTypeFacade extends AbstractFacade<RoomType> implements RoomTypeFacadeLocal {
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomTypeFacade() {
        super(RoomType.class);
    }
    
    
    
}
