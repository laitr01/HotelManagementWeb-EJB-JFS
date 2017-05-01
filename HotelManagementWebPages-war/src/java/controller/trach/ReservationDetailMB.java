/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.trach;

import entity.reservation.Reservation;
import entity.reservation.ReservationDetail;
import entity.reservation.ReservationDetailFacadeLocal;
import entity.reservation.ReservationFacadeLocal;
import entity.room.Rooms;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import utils.SessionUtils;

/**
 *
 * @author trach
 */
@ManagedBean
@SessionScoped
public class ReservationDetailMB {
    @EJB
    private ReservationFacadeLocal reservationFacade;
    @EJB
    private ReservationDetailFacadeLocal reservationDetailFacade;
    
    private Reservation reservation;
    
    private double rate;
    
    private Rooms room;
      
    
    public ReservationDetailMB() {
    }
    
    public ReservationDetail reservationDetailByResIdAndRoomId(int resId, int roomid){
        
        if(resId==0 || roomid==0){
            resId = SessionUtils.getResId();
            roomid = SessionUtils.getRoomId();
        }
        return reservationDetailFacade.findResDetailByResIdAndRoomId(resId, roomid);
    }
    
    public ReservationDetail reservationDetailByResIdAndRoomId(){
        
        //if(resId==0 || roomid==0){
        int resId = SessionUtils.getResId();
        int roomid = SessionUtils.getRoomId();
        //}
        return reservationDetailFacade.findResDetailByResIdAndRoomId(resId, roomid);
    }
    
    public String updateDetailRes(){
        
        return "";
    }
    
    public String redirectToUpdateDetail(int resId, int roomid){
        if(reservationDetailByResIdAndRoomId(resId, roomid)==null){
            return "/faces/users/trach/list_detail_reservations.xhtml?faces-redirect=true";
        }
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("resId", resId);
        session.setAttribute("roomId", roomid);
        return "/faces/users/trach/update_res_detail.xhtml?faces-redirect=true";
    }
    
    public ReservationDetail listResDetailByResId(int resId){
        return reservationDetailFacade.findResDetailByResId(resId);
    }

    public Reservation getReservationId() {
        return reservation;
    }

    public void setReservationId(Reservation reservation) {
        this.reservation = reservation;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public Rooms getRoomId() {
        return room;
    }

    public void setRoomId(Rooms room) {
        this.room = room;
    }

}
