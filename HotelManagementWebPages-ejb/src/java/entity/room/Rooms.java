/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.room;

import entity.reservation.Reservation;
import entity.reservation.ReservationDetail;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author trach
 */
@Entity
@Table(name = "Rooms", catalog = "HotelManagement", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RoomName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rooms.findAll", query = "SELECT r FROM Rooms r"),
    @NamedQuery(name = "Rooms.findByRoomID", query = "SELECT r FROM Rooms r WHERE r.roomID = :roomID"),
    @NamedQuery(name = "Rooms.findByRoomName", query = "SELECT r FROM Rooms r WHERE r.roomName = :roomName"),
    @NamedQuery(name = "Rooms.findByRoomStatus", query = "SELECT r FROM Rooms r WHERE r.roomStatus = :roomStatus"),
    @NamedQuery(name = "Rooms.findByImage1", query = "SELECT r FROM Rooms r WHERE r.image1 = :image1"),
    @NamedQuery(name = "Rooms.findByImage2", query = "SELECT r FROM Rooms r WHERE r.image2 = :image2"),
    @NamedQuery(name = "Rooms.findByImage3", query = "SELECT r FROM Rooms r WHERE r.image3 = :image3")})
public class Rooms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RoomID", nullable = false)
    private Integer roomID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RoomName", nullable = false, length = 50)
    private String roomName;
    @Size(max = 50)
    @Column(name = "RoomStatus", length = 50)
    private String roomStatus;
    @Size(max = 200)
    @Column(name = "Image1", length = 200)
    private String image1;
    @Size(max = 200)
    @Column(name = "Image2", length = 200)
    private String image2;
    @Size(max = 200)
    @Column(name = "Image3", length = 200)
    private String image3;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "roomID")
    private Collection<Reservation> reservationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rooms")
    private Collection<ReservationDetail> reservationDetailCollection;
    @JoinColumn(name = "RoomTypeID", referencedColumnName = "RoomTypeID")
    @ManyToOne
    private RoomType roomTypeID;

    public Rooms() {
    }

    public Rooms(Integer roomID) {
        this.roomID = roomID;
    }

    public Rooms(Integer roomID, String roomName) {
        this.roomID = roomID;
        this.roomName = roomName;
    }

    public Integer getRoomID() {
        return roomID;
    }

    public void setRoomID(Integer roomID) {
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

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @XmlTransient
    public Collection<ReservationDetail> getReservationDetailCollection() {
        return reservationDetailCollection;
    }

    public void setReservationDetailCollection(Collection<ReservationDetail> reservationDetailCollection) {
        this.reservationDetailCollection = reservationDetailCollection;
    }

    public RoomType getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(RoomType roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomID != null ? roomID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rooms)) {
            return false;
        }
        Rooms other = (Rooms) object;
        if ((this.roomID == null && other.roomID != null) || (this.roomID != null && !this.roomID.equals(other.roomID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Rooms[ roomID=" + roomID + " ]";
    }
    
}
