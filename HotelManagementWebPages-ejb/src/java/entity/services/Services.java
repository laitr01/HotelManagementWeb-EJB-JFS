/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.services;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "Services", catalog = "HotelManagement", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"ServiceName"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Services.findAll", query = "SELECT s FROM Services s"),
    @NamedQuery(name = "Services.findByServiceID", query = "SELECT s FROM Services s WHERE s.serviceID = :serviceID"),
    @NamedQuery(name = "Services.findByServiceName", query = "SELECT s FROM Services s WHERE s.serviceName = :serviceName"),
    @NamedQuery(name = "Services.findByPrice", query = "SELECT s FROM Services s WHERE s.price = :price")})
public class Services implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ServiceID", nullable = false)
    private Integer serviceID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ServiceName", nullable = false, length = 50)
    private String serviceName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "Price", precision = 19, scale = 4)
    private BigDecimal price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviceName")
    private Collection<ServiceDetail> serviceDetailCollection;

    public Services() {
    }

    public Services(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public Services(Integer serviceID, String serviceName) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
    }

    public Integer getServiceID() {
        return serviceID;
    }

    public void setServiceID(Integer serviceID) {
        this.serviceID = serviceID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlTransient
    public Collection<ServiceDetail> getServiceDetailCollection() {
        return serviceDetailCollection;
    }

    public void setServiceDetailCollection(Collection<ServiceDetail> serviceDetailCollection) {
        this.serviceDetailCollection = serviceDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (serviceID != null ? serviceID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.serviceID == null && other.serviceID != null) || (this.serviceID != null && !this.serviceID.equals(other.serviceID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Services[ serviceID=" + serviceID + " ]";
    }
    
}
