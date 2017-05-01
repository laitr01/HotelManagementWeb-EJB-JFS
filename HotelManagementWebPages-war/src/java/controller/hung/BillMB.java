
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.hung;

import entity.reservation.Reservation;
import entity.reservation.ReservationFacadeLocal;
import entity.room.Rooms;
import entity.services.ServiceDetailFacadeLocal;
import entity.services.Services;
import entity.services.ServicesFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author trach
 */
@ManagedBean
@RequestScoped
public class BillMB {
    @EJB
    private ServicesFacadeLocal servicesFacade;
    @EJB
    private ServiceDetailFacadeLocal serviceDetailFacade;
    @EJB
    private ReservationFacadeLocal reservationFacade;
    

    private Date dateOrderd;
    private double money;
    private int quantity;
    private Services serviceName;
    private int serviceid;
    private int reservationid;
    private Rooms room;
    private Reservation reservation;

    public BillMB() {
        
    }
    
     public List<Reservation> reservationForBill(){
       
        List<Reservation> allReservation = reservationFacade.findAll();
        List<Reservation> reservationCheckin = new ArrayList<>();
        for (Reservation res : allReservation) {
            System.out.println("Res:" +res.getRoomID().getRoomName() + ", status: "+ res.getStatus());
            if(res.getStatus().equals("check_in")){
                reservationCheckin.add(res);
            }
        }
        return reservationCheckin;
    
    }
     
    
     
    public List<Reservation> getListBillReservation(){
        return reservationForBill();
    }

    public Date getDateOrderd() {
        return dateOrderd;
    }

    public void setDateOrderd(Date dateOrderd) {
        this.dateOrderd = dateOrderd;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Services getServiceName() {
        return serviceName;
    }

    public void setServiceName(Services serviceName) {
        this.serviceName = serviceName;
    }

    public int getServiceid() {
        return serviceid;
    }

    public void setServiceid(int serviceid) {
        this.serviceid = serviceid;
    }

    public int getReservationid() {
        return reservationid;
    }

    public void setReservationid(int reservationid) {
        this.reservationid = reservationid;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    
    
}
