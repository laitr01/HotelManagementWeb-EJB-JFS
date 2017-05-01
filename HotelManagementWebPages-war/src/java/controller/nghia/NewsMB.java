/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controller.nghia;

import entity.employee.Employee;
import entity.employee.EmployeeFacadeLocal;
import entity.news.News;
import entity.news.NewsFacadeLocal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/**
 *
 * @author NghiaHCM
 */
@ManagedBean(name = "News")
@SessionScoped
public class NewsMB {
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    @EJB
    private NewsFacadeLocal newsFacade;
    
   private int newsID;
    private String newsTitle;
    private String imageUri;
     private Date datePost;
      private Employee employeeID;
     private int idEmployee;
     List<Employee> listEmployee;
     List<News> list;
           private News curNews;
           private int fromId;
    /**
     * Creates a new instance of NewsMB
     */
    public NewsMB() {
        list = new ArrayList<News>();
         listEmployee = new ArrayList<Employee>();
    }

    public List<Employee> getListEmployee() {
          listEmployee = new ArrayList<Employee>();
        listEmployee = employeeFacade.findAll();
        return listEmployee;
    }

    public void setListEmployee(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }
   
    public Employee getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Employee employeeID) {
        this.employeeID = employeeID;
    }


    public EmployeeFacadeLocal getEmployeeFacade() {
        return employeeFacade;
    }

    public void setEmployeeFacade(EmployeeFacadeLocal employeeFacade) {
        this.employeeFacade = employeeFacade;
    }

    public NewsFacadeLocal getNewsFacade() {
        return newsFacade;
    }

    public void setNewsFacade(NewsFacadeLocal newsFacade) {
        this.newsFacade = newsFacade;
    }

    public int getNewsID() {
        return newsID;
    }

    public void setNewsID(int newsID) {
        this.newsID = newsID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public Date getDatePost() {
        return datePost;
    }

    public void setDatePost(Date datePost) {
        this.datePost = datePost;
    }

    public int getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public List<News> getList() {
        
         list = new ArrayList<News>();
        
           list = newsFacade.getViewNew(idEmployee);
           list = newsFacade.findAll();
        return list;
    }
    
  
    public void setList(List<News> list) {
        this.list = list;
    }

    public News getCurNews() {
        return curNews;
    }

    public void setCurNews(News curNews) {
        this.curNews = curNews;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }
     public String showS(){
        list = newsFacade.findAll();
        return "newAdmin";
    }
    //create
    public String displayCreate(){
        curNews= new News();
        return "addNew";
    }    
    public String create(){
         idEmployee = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("idEmployee").toString());
         String userName = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("EmployeeUserName");
         idEmployee =  employeeFacade.getEmployeeID(userName).get(0).getEmployeeID();
        curNews= new News(newsTitle, imageUri, datePost, employeeID);
        curNews.setNewsTitle(newsTitle);
        curNews.setImageUri(imageUri);
        curNews.setDatePost(datePost);
        curNews.setEmployeeID(employeeFacade.find(idEmployee));
        newsFacade.create(curNews);
        return "newAdmin";
    } 
    public String delete(int newsID){
        curNews = newsFacade.find(newsID);
        newsFacade.remove(curNews);
        return "newAdmin";
    }   
      public TimeZone getTimeZone() {
        return TimeZone.getDefault();
    }
      //search
    public String search(){
        list = newsFacade.searchFromId(fromId);
        return "search";
    } 
     public String ShowUpdate(int newsID){
        
            curNews = newsFacade.find(newsID);
            return "addNew";
       
    }
    
    public String update(){
        try {
            newsFacade.edit(curNews);
            return "newAdmin";
        } catch (Exception e) {
            return "error";
        }
    }
}
