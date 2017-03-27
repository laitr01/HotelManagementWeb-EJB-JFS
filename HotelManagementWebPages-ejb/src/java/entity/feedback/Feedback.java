/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.feedback;

import entity.employee.Employee;
import entity.customer.Customer;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author trach
 */
@Entity
@Table(name = "Feedback", catalog = "HotelManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedback.findAll", query = "SELECT f FROM Feedback f"),
    @NamedQuery(name = "Feedback.findByFeedbackID", query = "SELECT f FROM Feedback f WHERE f.feedbackID = :feedbackID"),
    @NamedQuery(name = "Feedback.findByDateReceive", query = "SELECT f FROM Feedback f WHERE f.dateReceive = :dateReceive"),
    @NamedQuery(name = "Feedback.findByDateFeedback", query = "SELECT f FROM Feedback f WHERE f.dateFeedback = :dateFeedback"),
    @NamedQuery(name = "Feedback.findByQuestion", query = "SELECT f FROM Feedback f WHERE f.question = :question"),
    @NamedQuery(name = "Feedback.findByFeedback", query = "SELECT f FROM Feedback f WHERE f.feedback = :feedback")})
public class Feedback implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "FeedbackID", nullable = false)
    private Integer feedbackID;
    @Column(name = "DateReceive")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReceive;
    @Column(name = "DateFeedback")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFeedback;
    @Size(max = 50)
    @Column(name = "Question", length = 50)
    private String question;
    @Size(max = 50)
    @Column(name = "Feedback", length = 50)
    private String feedback;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID", nullable = false)
    @ManyToOne(optional = false)
    private Customer customerID;
    @JoinColumn(name = "EmployeeID", referencedColumnName = "EmployeeID", nullable = false)
    @ManyToOne(optional = false)
    private Employee employeeID;

    public Feedback() {
    }

    public Feedback(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Integer getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Date getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public Date getDateFeedback() {
        return dateFeedback;
    }

    public void setDateFeedback(Date dateFeedback) {
        this.dateFeedback = dateFeedback;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Customer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customer customerID) {
        this.customerID = customerID;
    }

    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (feedbackID != null ? feedbackID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedback)) {
            return false;
        }
        Feedback other = (Feedback) object;
        if ((this.feedbackID == null && other.feedbackID != null) || (this.feedbackID != null && !this.feedbackID.equals(other.feedbackID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Feedback[ feedbackID=" + feedbackID + " ]";
    }
    
}
