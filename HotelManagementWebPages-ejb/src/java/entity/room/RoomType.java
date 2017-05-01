/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.room;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "RoomType", catalog = "HotelManagement", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"RoomTypeName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoomType.findAll", query = "SELECT r FROM RoomType r"),
    @NamedQuery(name = "RoomType.findByRoomTypeID", query = "SELECT r FROM RoomType r WHERE r.roomTypeID = :roomTypeID"),
    @NamedQuery(name = "RoomType.findByRoomTypeName", query = "SELECT r FROM RoomType r WHERE r.roomTypeName = :roomTypeName"),
    @NamedQuery(name = "RoomType.findByRoomRate", query = "SELECT r FROM RoomType r WHERE r.roomRate = :roomRate"),
    @NamedQuery(name = "RoomType.findByNote", query = "SELECT r FROM RoomType r WHERE r.note = :note"),
    @NamedQuery(name = "RoomType.findByNumOfChild", query = "SELECT r FROM RoomType r WHERE r.numOfChild = :numOfChild"),
    @NamedQuery(name = "RoomType.findByNumOfAdult", query = "SELECT r FROM RoomType r WHERE r.numOfAdult = :numOfAdult")})
public class RoomType implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RoomRate", precision = 19, scale = 4)
    private Double roomRate;
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RoomTypeID", nullable = false)
    private Integer roomTypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RoomTypeName", nullable = false, length = 50)
    private String roomTypeName;
    @Size(max = 250)
    @Column(name = "Note", length = 250)
    private String note;
    @Column(name = "NumOfChild")
    private Integer numOfChild;
    @Column(name = "NumOfAdult")
    private Integer numOfAdult;
    @OneToMany(mappedBy = "roomTypeID")
    private Collection<Rooms> roomsCollection;

    public RoomType() {
    }

    public RoomType(Integer roomTypeID, String roomTypeName, 
            Double roomRate, String note, Integer numOfChild, Integer numOfAdult) {
        this.roomTypeID = roomTypeID;
        this.roomTypeName = roomTypeName;
        this.roomRate = roomRate;
        this.note = note;
        this.numOfChild = numOfChild;
        this.numOfAdult = numOfAdult;
    }
    
    public RoomType(String roomTypeName, 
            Double roomRate, String note, Integer numOfChild, Integer numOfAdult) {
        
        this.roomTypeName = roomTypeName;
        this.roomRate = roomRate;
        this.note = note;
        this.numOfChild = numOfChild;
        this.numOfAdult = numOfAdult;
    }
    
    public RoomType(Integer roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public RoomType(Integer roomTypeID, String roomTypeName) {
        this.roomTypeID = roomTypeID;
        this.roomTypeName = roomTypeName;
    }

    public Integer getRoomTypeID() {
        return roomTypeID;
    }

    public void setRoomTypeID(Integer roomTypeID) {
        this.roomTypeID = roomTypeID;
    }

    public String getRoomTypeName() {
        return roomTypeName;
    }

    public void setRoomTypeName(String roomTypeName) {
        this.roomTypeName = roomTypeName;
    }

    

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumOfChild() {
        return numOfChild;
    }

    public void setNumOfChild(Integer numOfChild) {
        this.numOfChild = numOfChild;
    }

    public Integer getNumOfAdult() {
        return numOfAdult;
    }

    public void setNumOfAdult(Integer numOfAdult) {
        this.numOfAdult = numOfAdult;
    }

    @XmlTransient
    public Collection<Rooms> getRoomsCollection() {
        return roomsCollection;
    }

    public void setRoomsCollection(Collection<Rooms> roomsCollection) {
        this.roomsCollection = roomsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roomTypeID != null ? roomTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoomType)) {
            return false;
        }
        RoomType other = (RoomType) object;
        if ((this.roomTypeID == null && other.roomTypeID != null) || (this.roomTypeID != null && !this.roomTypeID.equals(other.roomTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.RoomType[ roomTypeID=" + roomTypeID + " ]";
    }

    public Double getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(Double roomRate) {
        this.roomRate = roomRate;
    }

    

    
    
}
