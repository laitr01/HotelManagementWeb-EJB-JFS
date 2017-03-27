/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.reservation;

import entity.room.Rooms;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trach
 */
@Entity
@Table(name = "ReservationDetail", catalog = "HotelManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReservationDetail.findAll", query = "SELECT r FROM ReservationDetail r"),
    @NamedQuery(name = "ReservationDetail.findByRoomID", query = "SELECT r FROM ReservationDetail r WHERE r.reservationDetailPK.roomID = :roomID"),
    @NamedQuery(name = "ReservationDetail.findByReservationID", query = "SELECT r FROM ReservationDetail r WHERE r.reservationDetailPK.reservationID = :reservationID"),
    @NamedQuery(name = "ReservationDetail.findByDate", query = "SELECT r FROM ReservationDetail r WHERE r.date = :date"),
    @NamedQuery(name = "ReservationDetail.findByRate", query = "SELECT r FROM ReservationDetail r WHERE r.rate = :rate")})
public class ReservationDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReservationDetailPK reservationDetailPK;
    @Column(name = "Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Rate", precision = 19, scale = 4)
    private BigDecimal rate;
    @JoinColumn(name = "ReservationID", referencedColumnName = "ReservationID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Reservation reservation;
    @JoinColumn(name = "RoomID", referencedColumnName = "RoomID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Rooms rooms;

    public ReservationDetail() {
    }

    public ReservationDetail(ReservationDetailPK reservationDetailPK) {
        this.reservationDetailPK = reservationDetailPK;
    }

    public ReservationDetail(int roomID, int reservationID) {
        this.reservationDetailPK = new ReservationDetailPK(roomID, reservationID);
    }

    public ReservationDetailPK getReservationDetailPK() {
        return reservationDetailPK;
    }

    public void setReservationDetailPK(ReservationDetailPK reservationDetailPK) {
        this.reservationDetailPK = reservationDetailPK;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationDetailPK != null ? reservationDetailPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationDetail)) {
            return false;
        }
        ReservationDetail other = (ReservationDetail) object;
        if ((this.reservationDetailPK == null && other.reservationDetailPK != null) || (this.reservationDetailPK != null && !this.reservationDetailPK.equals(other.reservationDetailPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReservationDetail[ reservationDetailPK=" + reservationDetailPK + " ]";
    }
    
}
