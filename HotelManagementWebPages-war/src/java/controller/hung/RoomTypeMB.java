/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.hung;

import entity.room.RoomType;
import entity.room.RoomTypeFacadeLocal;
import java.util.ArrayList;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author trach
 */
@ManagedBean
@SessionScoped
public class RoomTypeMB {

    @EJB
    private RoomTypeFacadeLocal roomTypeFacade;
    private Integer roomTypeID=-1;
    private String roomTypeName;
    private double roomRate;
    private String description;
    private int numOfChild;
    private int numOfAdult;
    private List<RoomType> listTypes = new ArrayList<RoomType>();
    private String note;
    RoomType roomtype;

    public RoomType getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(RoomType roomtype) {
        this.roomtype = roomtype;
    }

    public List<RoomType> getListTypes() {
        return listTypes;
    }

    public void setListTypes(List<RoomType> listTypes) {
        this.listTypes = listTypes;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public RoomTypeMB() {

    }

    public List<RoomType> listRoomType() {
        listTypes = roomTypeFacade.findAll();
        return listTypes;
    }

    public RoomTypeFacadeLocal getRoomTypeFacade() {
        return roomTypeFacade;
    }

    public void setRoomTypeFacade(RoomTypeFacadeLocal roomTypeFacade) {
        this.roomTypeFacade = roomTypeFacade;
    }

    public int getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(int roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    public double getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(double roomRate) {
        this.roomRate = roomRate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfChild() {
        return numOfChild;
    }

    public void setNumOfChild(int numOfChild) {
        this.numOfChild = numOfChild;
    }

    public int getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(int numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    public List<RoomType> showRoom() {
        return roomTypeFacade.findAll();
    }

    public String insert() {
        
        if (!check(roomTypeName)) {
            roomtype = new RoomType(roomTypeName, roomRate, note, numOfChild, numOfAdult);
            roomTypeFacade.create(roomtype);
            roomTypeName="";
            roomRate=0;
            note="";
            numOfChild=0;
            numOfAdult=0;
            return "/faces/admin/tableRoomtype?faces-redirect=true";

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("RoomtypeName is exist"));
            return "/faces/admin/addRoomType";
        }

    }

    public String Showupdate(int roomTypeID) {
        roomtype = roomTypeFacade.find(roomTypeID);
        return "/faces/admin/updateRoomtype?faces-redirect=true";
    }

    public String Update() {
        if(!check(roomTypeName)){
            roomTypeFacade.edit(roomtype);
        return "/faces/admin/tableRoomtype?faces-redirect=true";
            
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("RoomtypeName is exist"));
            return "/faces/admin/addRoomType";
        }
        
    }

    public String deleteRoomType(int roomTypeID) {
        roomtype = roomTypeFacade.find(roomTypeID);
        roomTypeFacade.remove(roomtype);
        return "/faces/admin/tableRoomtype?faces-redirect=true";
    }

    public boolean check(String name) {
        for (RoomType roomType : roomTypeFacade.findAll()) {
            if (roomType.getRoomTypeName().equalsIgnoreCase(name)&&roomType.getRoomTypeID()!=roomTypeID) {
                return true;
            }
        }
        return false;
    }

}
