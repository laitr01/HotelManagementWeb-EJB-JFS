/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.reservation;

import entity.bill.Bill;
import entity.customer.Customer;
import entity.room.Rooms;
import entity.services.ServiceDetail;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author trach
 */
@Entity
@Table(name = "Reservation", catalog = "HotelManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reservation.findAll", query = "SELECT r FROM Reservation r"),
    @NamedQuery(name = "Reservation.findByReservationID", query = "SELECT r FROM Reservation r WHERE r.reservationID = :reservationID"),
    @NamedQuery(name = "Reservation.findByStartDate", query = "SELECT r FROM Reservation r WHERE r.startDate = :startDate"),
    @NamedQuery(name = "Reservation.findByEndDate", query = "SELECT r FROM Reservation r WHERE r.endDate = :endDate"),
    @NamedQuery(name = "Reservation.findByNumberOfPeople", query = "SELECT r FROM Reservation r WHERE r.numberOfPeople = :numberOfPeople"),
    @NamedQuery(name = "Reservation.findByPaid", query = "SELECT r FROM Reservation r WHERE r.paid = :paid"),
    @NamedQuery(name = "Reservation.findByStatus", query = "SELECT r FROM Reservation r WHERE r.status = :status")})
public class Reservation implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ReservationID", nullable = false)
    private Integer reservationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "StartDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EndDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NumberOfPeople", nullable = false)
    private int numberOfPeople;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Paid", precision = 19, scale = 4)
    private BigDecimal paid;
    @Size(max = 10)
    @Column(name = "Status", length = 10)
    private String status;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerID;
    @JoinColumn(name = "RoomID", referencedColumnName = "RoomID", nullable = false)
    @ManyToOne(optional = false)
    private Rooms roomID;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservationID")
    private Collection<Bill> billCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservationID")
    private Collection<ServiceDetail> serviceDetailCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reservation")
    private Collection<ReservationDetail> reservationDetailCollection;

    public Reservation() {
    }

    public Reservation(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Reservation(Date startDate, 
            Date endDate, int numberOfPeople, BigDecimal paid, 
            String status, Customer customerID, Rooms roomID) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfPeople = numberOfPeople;
        this.paid = paid;
        this.status = status;
        this.customerID = customerID;
        this.roomID = roomID;
    }
    
    public Reservation(Integer reservationID, Date startDate, Date endDate, int numberOfPeople) {
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfPeople = numberOfPeople;
    }

    public Integer getReservationID() {
        return reservationID;
    }

    public void setReservationID(Integer reservationID) {
        this.reservationID = reservationID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public Rooms getRoomID() {
        return roomID;
    }

    public void setRoomID(Rooms roomID) {
        this.roomID = roomID;
    }

    @XmlTransient
    public Collection<Bill> getBillCollection() {
        return billCollection;
    }

    public void setBillCollection(Collection<Bill> billCollection) {
        this.billCollection = billCollection;
    }

    @XmlTransient
    public Collection<ServiceDetail> getServiceDetailCollection() {
        return serviceDetailCollection;
    }

    public void setServiceDetailCollection(Collection<ServiceDetail> serviceDetailCollection) {
        this.serviceDetailCollection = serviceDetailCollection;
    }

    @XmlTransient
    public Collection<ReservationDetail> getReservationDetailCollection() {
        return reservationDetailCollection;
    }

    public void setReservationDetailCollection(Collection<ReservationDetail> reservationDetailCollection) {
        this.reservationDetailCollection = reservationDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservationID != null ? reservationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.reservationID == null && other.reservationID != null) || 
                (this.reservationID != null && !this.reservationID.equals(other.reservationID))) {
            return false;
        }
        //trach note - add bellow code for removing same reservation without id
        if(this.reservationID == null && other.reservationID ==null){
            return (this.roomID.getRoomID().intValue() == other.roomID.getRoomID().intValue())
                    &&(this.startDate.getTime()==other.startDate.getTime())
                    &&(this.endDate.getTime()== other.getEndDate().getTime()
                    && this.roomID.getRoomTypeID().getRoomTypeID()== other.roomID.getRoomTypeID().getRoomTypeID());
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Reservation[ reservationID=" + reservationID + " ]";
    }
    
}
