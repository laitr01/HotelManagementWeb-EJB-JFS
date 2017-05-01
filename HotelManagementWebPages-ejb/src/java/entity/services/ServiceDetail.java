/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.services;

import entity.reservation.Reservation;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trach
 */
@Entity
@Table(name = "ServiceDetail", catalog = "HotelManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceDetail.findAll", query = "SELECT s FROM ServiceDetail s"),
    @NamedQuery(name = "ServiceDetail.findByServiceDetailID", query = "SELECT s FROM ServiceDetail s WHERE s.serviceDetailID = :serviceDetailID"),
    @NamedQuery(name = "ServiceDetail.findByPrice", query = "SELECT s FROM ServiceDetail s WHERE s.price = :price"),
    @NamedQuery(name = "ServiceDetail.findByDateOfUse", query = "SELECT s FROM ServiceDetail s WHERE s.dateOfUse = :dateOfUse")})
public class ServiceDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ServiceDetailID", nullable = false)
    private Integer serviceDetailID;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price", precision = 19, scale = 4)
    private Double price;
    @Column(name = "DateOfUse")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfUse;
    @JoinColumn(name = "ReservationID", referencedColumnName = "ReservationID", nullable = false)
    @ManyToOne(optional = false)
    private Reservation reservationID;
    @JoinColumn(name = "ServiceName", referencedColumnName = "ServiceName", nullable = false)
    @ManyToOne(optional = false)
    private Services serviceName;

    public ServiceDetail() {
    }

    public ServiceDetail(Integer serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
    }

    public Integer getServiceDetailID() {
        return serviceDetailID;
    }

    public void setServiceDetailID(Integer serviceDetailID) {
        this.serviceDetailID = serviceDetailID;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDateOfUse() {
        return dateOfUse;
    }

    public void setDateOfUse(Date dateOfUse) {
        this.dateOfUse = dateOfUse;
    }

    public Reservation getReservationID() {
        return reservationID;
    }

    public void setReservationID(Reservation reservationID) {
        this.reservationID = reservationID;
    }

    public Services getServiceName() {
        return serviceName;
    }

    public void setServiceName(Services serviceName) {
        this.serviceName = serviceName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceDetailID != null ? serviceDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceDetail)) {
            return false;
        }
        ServiceDetail other = (ServiceDetail) object;
        if ((this.serviceDetailID == null && other.serviceDetailID != null) || (this.serviceDetailID != null && !this.serviceDetailID.equals(other.serviceDetailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.ServiceDetail[ serviceDetailID=" + serviceDetailID + " ]";
    }
    
}
