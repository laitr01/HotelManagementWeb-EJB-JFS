package controller.vu;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entity.employee.Employee;
import entity.employee.EmployeeFacadeLocal;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import utils.SessionUtils;

/**
 *
 * @author KeVin
 */
@ManagedBean
@SessionScoped
public class EmployeeMB{

    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    private String pass2;
    
    private String username, pass, gender, email, role;

    private Date dob;
    
    private Integer empID;

    public Integer getEmpID() {
        return empID;
    }

    public void setEmpID(Integer empID) {
        this.empID = empID;
    }
    
    private Employee emp;
    private List<Employee> list = new ArrayList<Employee>();

    public EmployeeFacadeLocal getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacadeLocal employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public String getPass2() {
        return pass2;
    }

    public void setPass2(String pass2) {
        this.pass2 = pass2;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Employee getEmp() {
        return emp;
    }

    public void setEmp(Employee emp) {
        this.emp = emp;
    }

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }

    

    public EmployeeMB() {
    }

    public List<entity.employee.Employee> showall() {
        return employeeFacade.findAll();
    }
    private final static long A_YEAR = 1*365*24*60*60*1000; 
    public String insert() {
        
        if(duplicateUser(username)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("UserName is exist"));
            return "/faces/users/vu/registeradmin";
        }
        if(duplicateEmail(email)){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email is exist"));
            return "/faces/users/vu/registeradmin";
        }
        long current = System.currentTimeMillis();
        if((current-dob.getTime())/A_YEAR < 18 ||
                (current-dob.getTime())/A_YEAR>1000){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Year must be greater 18 and less than 100"));
            return "/faces/users/vu/registeradmin";
        }
        emp = new Employee(username, pass, new java.sql.Date(dob.getTime()), gender, email);
        employeeFacade.create(emp);
        return "/faces/admin/index.xhtml?faces-redirect=true";

    }

    public String login() {
        if (employeeFacade.login(username, pass)) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("admin", employeeFacade.findUser(username));
            return "/faces/admin/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Wrong User or pass"));
            return "/faces/admin/login";
        }
    }
    
    public Employee getEmpSession(){
        return SessionUtils.getAdmin();
    }
    public String logout(){
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "/faces/admin/login?faces-redirect=true";
    }
    
    public String ShowUpdate(int empID) {

        emp = employeeFacade.find(empID);
        return "/faces/admin/profile?faces-redirect=true";
    }
    
    public String UpdateCustomer(Employee emp) {
        employeeFacade.edit(getEmpSession());
        return "/faces/users/vu/information?faces-redirect=true";
    }
    public String changePassword() {
        if (getEmpSession().getPassWord().equals(pass)) {
            employeeFacade.upPass(pass2, getEmpSession().getEmployeeID());
            return "/faces/admin/index?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong password", null));
            return "/faces/admin/changepassword";
        }

    }
    
    public List<Employee> listemployee(){
        return employeeFacade.showEmp();
    }
    
    public String deleteEmp(int empID){
        emp=employeeFacade.find(empID);
        employeeFacade.remove(emp);
        return "/faces/admin/employeeAdmin?faces-forward=true";
    }
    
    public String updateEmp(Employee emp){
        employeeFacade.edit(getEmpSession());
        return "/faces/admin/profile?faces-redirect=true";
    }
    
     public boolean duplicateEmail(String email){
        for (Employee employee : employeeFacade.findAll()) {
            if(employee.getEmail().equalsIgnoreCase(email)){
                return true;
            }
        }
        return false;
    }
     public boolean duplicateUser(String name){
        for (Employee employee : employeeFacade.findAll()) {
            if(employee.getUserName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
     
    

}
