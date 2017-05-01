/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.vu;

import entity.customer.Customer;
import entity.customer.CustomerFacadeLocal;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utils.Contanst;
import utils.SessionUtils;

/**
 *
 * @author trach
 */
@ManagedBean
@SessionScoped
public class CustomerMB {

    @EJB
    private CustomerFacadeLocal customerFacade;

    private String email;

    private String password;

    private Integer customerID;
    private String address, company, gender;
    private String fullname;
    private Date dob;
    private int phone, identify;
    private String pass2;
    private List<Customer> list = new ArrayList<Customer>();
    private Customer cus;

    public Customer getCus() {
        return cus;
    }

    public CustomerFacadeLocal getCustomerFacade() {
        return customerFacade;
    }

    public void setCustomerFacade(CustomerFacadeLocal customerFacade) {
        this.customerFacade = customerFacade;
    }

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getIdentify() {
        return identify;
    }

    public void setIdentify(int identify) {
        this.identify = identify;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String status;

    public CustomerMB() {

    }

    public String login() {
        if (customerFacade.login(email, password)) {
            if (customerFacade.checkstatus(email)) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("user", customerFacade.findEmail(email));
                if(Contanst.CHECKOUT.equals("checkout")){
                    Contanst.CHECKOUT = "";
                    return Contanst.PATH_GLOBAL;
                }else{
                    return "/index?faces-redirect=true";
                }
                //cus=customerFacade.cusToEmail(email);                
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account is block"));
                return "/faces/users/vu/login";
            }

        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong email and password"));
            return "/faces/users/vu/login";
        }
    }

    public Customer getCusFromSession() {
        return SessionUtils.getUser();
    }

    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/index?faces-forward=true";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    private final static long A_YEAR = 1*365*24*60*60*1000;
    public String insert() {
        if(duplicateEmail(email)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email is exist"));
            return "/faces/users/vu/register";  
        }
        long current = System.currentTimeMillis();
        if((current-dob.getTime())/A_YEAR < 18 ||
                (current-dob.getTime())/A_YEAR>1000){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Year must be greater 18 and less than 100"));
            return "/faces/users/vu/register"; 
        }
        
        if(duplicatePhone(String.valueOf(identify))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person Identifier is exist"));
            return "/faces/users/vu/register";
        }
        
        if(duplicatePhone(String.valueOf(phone))){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Phone is exist"));
            return "/faces/users/vu/register";
        }
        
        cus = new Customer(String.valueOf(phone), new java.sql.Date(dob.getTime()), fullname, gender, company, address, String.valueOf(identify), email, password);
        customerFacade.create(cus);
        phone=0;
        fullname="";
        gender="";
        company="";
        address="";
        identify=0;
        email="";
        password="";
        return "/index?faces-redirect=true";
        
        
    }

    public String ShowUpdate(int customerID) {

        cus = customerFacade.find(customerID);
        return "/faces/users/vu/information?faces-redirect=true";
    }

    public String UpdateCustomer(Customer cus) {
        customerFacade.edit(getCusFromSession());
        return "/faces/users/vu/information?faces-redirect=true";
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String changePassword() {
        if (getCusFromSession().getPassWord().equals(password)) {
            customerFacade.upPass(pass2, getCusFromSession().getCustomerID());
            return "/faces/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong password"));
            return "/faces/users/vu/changepassword";
        }

    }
    public String lock(){
        customerFacade.lock(cus.getCustomerID());
        return "/faces/admin/tablecustomer?faces-redirect=true";
    }
    
    public String unLock(){
        customerFacade.unLock(cus.getCustomerID());
        return "/faces/admin/tablecustomer?faces-redirect=true";
    }
    
    public List<Customer> showAll(){
        return customerFacade.findAll();
    }
    public String Detail(int customerID) {

        cus = customerFacade.find(customerID);
        return "/faces/admin/information?faces-redirect=true";
    }
    
    public boolean duplicateEmail(String email){
        for (Customer customer : customerFacade.findAll()) {
            if(customer.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
    public boolean duplicatePhone(String phone){
        for (Customer customer : customerFacade.findAll()) {
            if(customer.getPhone().equalsIgnoreCase(phone)){
                return true;
            }
        }
        return false;
    }
    public boolean duplicateIdentifier(String identifier){
        for (Customer customer : customerFacade.findAll()) {
            if(customer.getPersonIdentifier().equalsIgnoreCase(identifier)){
                return true;
            }
        }
        return false;
    }
   
    public String check(){
        if(getCusFromSession()==null){
            return "/faces/users/vu/login";
        }
        return "";
    }
   

}
