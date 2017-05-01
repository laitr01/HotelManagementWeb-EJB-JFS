/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.nghia;

import entity.services.Services;
import entity.services.ServicesFacadeLocal;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author NghiaHCM
 */
@ManagedBean(name = "Services")
@SessionScoped
public class ServiceMB {
    @EJB
    private ServicesFacadeLocal servicesFacade;
    private Services curServices;
    private int serviceID;
    private String serviceName="";
    private BigDecimal price;
    List<Services> list;

    public ServicesFacadeLocal getServicesFacade() {
        return servicesFacade;
    }

    public void setServicesFacade(ServicesFacadeLocal servicesFacade) {
        this.servicesFacade = servicesFacade;
    }

    public Services getCurServices() {
        return curServices;
    }

    public void setCurServices(Services curServices) {
        this.curServices = curServices;
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

  
    public List<Services> getList() {
        return list;
    }

    public void setList(List<Services> list) {
        this.list = list;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

   
    
    /**
     * Creates a new instance of ServiceMB
     */
    public ServiceMB() {
    }
      
    public List<Services> showall(){
        return servicesFacade.findAll();
    }
    //create
    public String displayCreate(){
        curServices= new Services();
        return "addService";
    }    
    public String create(){
        servicesFacade.create(curServices);
        return "serviceAdmin";
    } 
    public String delete(int serviceID){
        curServices = servicesFacade.find(serviceID);
        servicesFacade.remove(curServices);
        return "serviceAdmin";
    }   
    
      //search
    public String search(){
        list = servicesFacade.searchName(serviceName);
        return "search";
    } 
     public String ShowUpdate(int serviceID){
        
          curServices = servicesFacade.find(serviceID);
       
            return "addService";
        
    }
    
    public String update(){
        try {
            servicesFacade.edit(curServices);
            return "serviceAdmin";
        } catch (Exception e) {
            return "error";
        }
    }
}
