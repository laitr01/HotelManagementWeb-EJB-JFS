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
    @NamedQuery(name = "RoomType.findByNumOfPeople", query = "SELECT r FROM RoomType r WHERE r.numOfPeople = :numOfPeople")})
public class RoomType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "RoomTypeID", nullable = false)
    private Integer roomTypeID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "RoomTypeName", nullable = false, length = 50)
    private String roomTypeName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "RoomRate", precision = 19, scale = 4)
    private BigDecimal roomRate;
    @Size(max = 100)
    @Column(name = "Note", length = 100)
    private String note;
    @Column(name = "NumOfPeople")
    private Integer numOfPeople;
    @OneToMany(mappedBy = "roomTypeID")
    private Collection<Rooms> roomsCollection;

    public RoomType() {
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

    public BigDecimal getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(BigDecimal roomRate) {
        this.roomRate = roomRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getNumOfPeople() {
        return numOfPeople;
    }

    public void setNumOfPeople(Integer numOfPeople) {
        this.numOfPeople = numOfPeople;
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
    
}
