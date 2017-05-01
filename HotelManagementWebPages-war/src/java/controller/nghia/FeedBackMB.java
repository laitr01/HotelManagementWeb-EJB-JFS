/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.nghia;

import entity.customer.Customer;
import entity.customer.CustomerFacadeLocal;
import entity.employee.Employee;
import entity.employee.EmployeeFacadeLocal;
import entity.feedback.Feedback;
import entity.feedback.FeedbackFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.xml.crypto.Data;
import org.apache.jasper.tagplugins.jstl.ForEach;
import utils.SessionUtils;

/**
 *
 * @author KeVin
 */
@ManagedBean
@SessionScoped
public class FeedBackMB {
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    @EJB
    private CustomerFacadeLocal customerFacade;
    @EJB
    private FeedbackFacadeLocal feedbackFacade;
    private int customerId;
    private int empID;
    private int feedbackId;
    private Date dateFeedback=new Date(System.currentTimeMillis());
    private Date dateReceive=new Date(System.currentTimeMillis());
    private String question,feedback;
    private List<Feedback> list = new ArrayList<Feedback>();
    private Feedback fb;

    public Customer getCus() {
        return cus;
    }

    public EmployeeFacadeLocal getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacadeLocal employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }
    private Customer cus;

    public Feedback getFb() {
        return fb;
    }

    public void setFb(Feedback fb) {
        this.fb = fb;
    }

    public CustomerFacadeLocal getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacadeLocal customerFacade) {
        this.customerFacade = customerFacade;
    }

    public FeedbackFacadeLocal getFeedbackFacade() {
        return feedbackFacade;
    }

    public void setFeedbackFacade(FeedbackFacadeLocal feedbackFacade) {
        this.feedbackFacade = feedbackFacade;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmpID() {
        return empID;
    }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public Date getDateReceive() {
        return dateReceive;
    }

    public void setDateReceive(Date dateReceive) {
        this.dateReceive = dateReceive;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public List<Feedback> getList() {
        return list;
    }

    public void setList(List<Feedback> list) {
        this.list = list;
    }
    public FeedBackMB() {
    }
    public List<Feedback> showall(){
        return feedbackFacade.findAll();
    }
    public String insert(){
        Customer customer = SessionUtils.getUser();
        fb=new Feedback(customer,new Date(), question);
        feedbackFacade.create(fb);
        return "/faces/index.xhtml";
    }
    
    public String showinfofeedback(int feedID){
        fb=feedbackFacade.find(feedID);
        return "/faces/admin/infofeedback?faces-redirect=true";
        
    }
    public String update(){
        Employee emp=SessionUtils.getAdmin();
        fb.setEmployeeID(emp);
        fb.setFeedback(feedback);
        fb.setDateReceive(new Date());
        feedbackFacade.edit(fb);
        return "/faces/admin/feedbackAdmin?faces-redirect=true";
    }
    
    public List<Feedback> showmyfeedBack(){
        List<Feedback> listfb=new ArrayList<Feedback>();
        for (Feedback feedback1 : feedbackFacade.findAll()) {
            if(feedback1.getCustomerID().getCustomerID().intValue()==SessionUtils.getUser().getCustomerID().intValue()){
                listfb.add(feedback1);
            }
            
        }
        return listfb;
    } 
}
