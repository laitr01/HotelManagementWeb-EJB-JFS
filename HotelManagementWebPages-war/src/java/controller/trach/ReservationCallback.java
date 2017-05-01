/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.trach;

import entity.reservation.Reservation;
import java.util.List;

/**
 *
 * @author trach
 */
public interface ReservationCallback {
    
    void onSuccess(String message);
    
    void onFailure(Reservation listRes);
    
}
