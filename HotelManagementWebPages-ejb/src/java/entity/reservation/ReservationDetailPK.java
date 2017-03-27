/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.reservation;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author trach
 */
@Embeddable
public class ReservationDetailPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "RoomID", nullable = false)
    private int roomID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ReservationID", nullable = false)
    private int reservationID;

    public ReservationDetailPK() {
    }

    public ReservationDetailPK(int roomID, int reservationID) {
        this.roomID = roomID;
        this.reservationID = reservationID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) roomID;
        hash += (int) reservationID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservationDetailPK)) {
            return false;
        }
        ReservationDetailPK other = (ReservationDetailPK) object;
        if (this.roomID != other.roomID) {
            return false;
        }
        if (this.reservationID != other.reservationID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ReservationDetailPK[ roomID=" + roomID + ", reservationID=" + reservationID + " ]";
    }
    
}
