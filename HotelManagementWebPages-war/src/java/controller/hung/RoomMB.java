/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.hung;


import com.google.common.io.Files;
import entity.reservation.Reservation;
import entity.reservation.ReservationFacadeLocal;
import entity.room.RoomType;
import entity.room.RoomTypeFacadeLocal;
import entity.room.Rooms;
import entity.room.RoomsFacadeLocal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;


/**
 *
 * @author trach
 */
@ManagedBean
@SessionScoped
public class RoomMB {
    @EJB
    private ReservationFacadeLocal reservationFacade;
    @EJB
    private RoomTypeFacadeLocal roomTypeFacade;

    
    @EJB
    private RoomsFacadeLocal roomsFacade;
    
    private int roomID;
    private String roomName;
    private String roomStatus;
    private String image1;
    private String image2;
    private List<Rooms> list = new ArrayList<Rooms>();
    
    private String image3;
    private int roomtype;
    private Rooms room;
    
    private Part img1, img2, img3;

    public Part getImg1() {
        return img1;
    }

    public void setImg1(Part img1) {
        this.img1 = img1;
    }

    public Part getImg2() {
        return img2;
    }

    public void setImg2(Part img2) {
        this.img2 = img2;
    }

    public Part getImg3() {
        return img3;
    }

    public void setImg3(Part img3) {
        this.img3 = img3;
    }
    
    
    
    public int getRoomtype() {
        return roomtype;
    }

    public void setRoomtype(int roomtype) {
        this.roomtype = roomtype;
    }

    public Rooms getRoom() {
        return room;
    }

    public void setRoom(Rooms room) {
        this.room = room;
    }

    public RoomMB() {

    }
    
    
    public List<Rooms> listAll() {
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

    public List<Rooms> getList() {
        return list;
    }

    public void setList(List<Rooms> list) {
        this.list = list;
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

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    String path =FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    public String upload() throws IOException {
        String image1 = this.room.getRoomName()+"_" +getFilename(img1);
        String image2 = this.room.getRoomName() +"_" +getFilename(img2);
        String image3 = this.room.getRoomName() +"_" +getFilename(img3);
        
        
        this.room.setImage1(image1);
        this.room.setImage2(image2);
        this.room.setImage3(image3);
        
        doUpload(img1, image1);
        doUpload(img2, image2);
        doUpload(img3, image3);
        
        return "";
    }
    
    public String imageLink(){
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        String raw= externalContext.getInitParameter("uploadDirectory");
        if(raw.contains("/")){
            raw.replace("/", "\\");
        }
        return path;
    } 
    private void doUpload(Part img1, String image) throws IOException{
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        String directory = externalContext.getInitParameter("uploadDirectory");
        OutputStream out = null;
        InputStream filecontent = null;
        try {
            out = new FileOutputStream(new File(path 
                    + image));
            System.out.println(path 
                    + image);
            filecontent = img1.getInputStream();

            int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
           
        } catch (Exception fne) {
            System.out.println("You either did not specify a file to upload or are "
                    + "trying to upload a file to a protected or nonexistent "
                    + "location.");
            fne.printStackTrace();

        } finally {
            if (out != null) {
                out.close();
            }
            if (filecontent != null) {
                filecontent.close();
            }
        }
    }
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    
    public RoomsFacadeLocal getRoomsFacade() {
        return roomsFacade;
    }

    public void setRoomsFacade(RoomsFacadeLocal roomsFacade) {
        this.roomsFacade = roomsFacade;
    }

    public List<Rooms> showRoom() {
        return roomsFacade.findAll();
    }

    public String insert() throws IOException {
        
        if (!roomsFacade.searchRoomName(roomName)) {
            RoomType roomType = roomTypeFacade.find(roomtype);
            room = new Rooms(roomName, image1, image2, image3, roomType);
            upload();
            roomsFacade.create(room);
            roomName="";
            image1="";
            image2="";
            image3="";
            
            return "/faces/admin/tableRoom?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("RoomName is exist"));
            return "/faces/admin/addRoom";
        }

    }
    
    public String showUpdate(int roomID){
        room=roomsFacade.find(roomID);
        return "/faces/admin/updateRoom?faces-redirect=true";
        
    }
    
    public String update() throws IOException{
        RoomType roomType = roomTypeFacade.find(roomtype);
        //roomTypeFacade.edit(roomType);
        room.setRoomTypeID(roomType);
        upload();
        roomsFacade.edit(room);
        
        return "/faces/admin/tableRoom?faces-redirect=true";
    }
    public String delete(int roomid){
        room=roomsFacade.find(roomid);
        List<Reservation> listreservation = reservationFacade.findAll();
        for (Reservation reservation : listreservation) {
            if(reservation.getRoomID().getRoomID() == roomid){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Room is check in"));
                return "/faces/admin/tableRoom";
            }
        }        
        roomsFacade.remove(room);
        return "/faces/admin/tableRoom?faces-forward=true";
    }
    
    public List<Rooms> randomRooms(){
        List<Rooms> randRooms = new ArrayList<>();
        int size = roomsFacade.findAll().size();
        int num = (size>6)?size/2:size;
        for (int i = 0; i < num; i++) {
            Random ran = new Random();
            Rooms room = roomsFacade.findAll().get((size>1)?ran.nextInt(size-1):0);
            randRooms.add(room);
        }
        return randRooms;
    }

    public List<Rooms> roomsForServices(){
        List<Rooms> allRoom = roomsFacade.findAll();
        List<Rooms> roomCheckin = new ArrayList<>();
        for (Rooms room : allRoom) {
            if(room.getRoomStatus().equals("check-in")){
                roomCheckin.add(room);
            }
        }
        return roomCheckin;
    }
    public String showUpdateList(int roomID){
        room=roomsFacade.find(roomID);
        return "/faces/users/hung/roomdetail?faces-redirect=true";
        
    }
    
}
