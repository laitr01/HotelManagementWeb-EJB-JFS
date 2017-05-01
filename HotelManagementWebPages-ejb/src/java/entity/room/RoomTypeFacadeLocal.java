/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.room;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface RoomTypeFacadeLocal {

    void create(RoomType roomType);

    void edit(RoomType roomType);

    void remove(RoomType roomType);

    RoomType find(Object id);

    List<RoomType> findAll();

    List<RoomType> findRange(int[] range);

    int count();
    
    
    
}
