/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.reservation;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface ReservationDetailFacadeLocal {

    void create(ReservationDetail reservationDetail);

    void edit(ReservationDetail reservationDetail);

    void remove(ReservationDetail reservationDetail);

    ReservationDetail find(Object id);

    List<ReservationDetail> findAll();

    List<ReservationDetail> findRange(int[] range);

    int count();
    
    ReservationDetail findResDetailByResId(int resId);
    
    ReservationDetail findResDetailByResIdAndRoomId(int resId, int roomId);
    
}
