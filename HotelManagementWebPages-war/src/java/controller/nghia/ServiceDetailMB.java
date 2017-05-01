/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.nghia;

import entity.reservation.Reservation;
import entity.reservation.ReservationFacadeLocal;
import entity.room.Rooms;
import entity.services.ServiceDetail;
import entity.services.ServiceDetailFacadeLocal;
import entity.services.Services;
import entity.services.ServicesFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author trach
 */
@ManagedBean
@RequestScoped
public class ServiceDetailMB {
    @EJB
    private ServicesFacadeLocal servicesFacade;
    @EJB
    private ReservationFacadeLocal reservationFacade;
    @EJB
    private ServiceDetailFacadeLocal serviceDetailFacade;

    private Date dateOrderd;
    private double money;
    private int quantity;
    private Services serviceName;
    private int serviceid;
    private int reservationid;
    private Rooms room;
    private Reservation reservation;
    
    public ServiceDetailMB() {
        
    }
    
    public List<Reservation> reservationForServices(){
       
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
    
    public String insertServiceDetail(){
        ServiceDetail detail = new ServiceDetail();
        detail.setDateOfUse(new Date(System.currentTimeMillis()));
        List<Reservation> list = reservationFacade.findAll();
        for (Reservation reser : list) {
            if(reser.getReservationID().intValue()==reservationid){
                detail.setReservationID(reser);
            }
        }
        double total = 0.0d;
        List<Services> services = servicesFacade.findAll();
        for (Services ser : services) {
            if(ser.getServiceID().intValue()==serviceid){
                detail.setServiceName(ser);
                total = ser.getPrice().doubleValue()*quantity;
            }
        }
        detail.setPrice(total);
        serviceDetailFacade.create(detail);
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage("Inserted successfully. You have already spend "+total));
        return "/faces/admin/service_detail.xhtml";
    }
    public Date generateCurrent(){
        return new Date(System.currentTimeMillis());
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Services getServiceName() {
        return serviceName;
    }

    public void setServiceName(Services serviceName) {
        this.serviceName = serviceName;
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
