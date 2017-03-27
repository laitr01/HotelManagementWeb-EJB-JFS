/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.hung;

import entity.room.Rooms;
import entity.room.RoomsFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author trach
 */
@ManagedBean
@SessionScoped
public class RoomMB {
    @EJB
    private RoomsFacadeLocal roomsFacade;
    private int roomID;
    private String roomName;
    private String roomStatus;
    private String image1;
    private String image2;
    List<Rooms> list=new ArrayList<Rooms>();
    private String image3; 

    public RoomMB(String roomName, 
            String roomStatus, String image1, 
            String image2, String image3) {
        this.roomName = roomName;
        this.roomStatus = roomStatus;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
    }
    
    
    public RoomMB() {
        
    }
    
    public List<Rooms> getList(){
        list = roomsFacade.findAll();
        return list;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public RoomsFacadeLocal getRoomsFacade() {
        return roomsFacade;
    }

    public void setRoomsFacade(RoomsFacadeLocal roomsFacade) {
        this.roomsFacade = roomsFacade;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    
}
