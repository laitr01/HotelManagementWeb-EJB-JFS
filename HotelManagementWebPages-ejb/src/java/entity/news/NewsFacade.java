/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.news;

import entity.AbstractFacade;
import entity.employee.Employee;
import entity.employee.EmployeeFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author trach
 */
@Stateless
public class NewsFacade extends AbstractFacade<News> implements NewsFacadeLocal {
    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }

    @Override
    public List<News> searchFromId(int fromId) {
       List<News> list = new ArrayList<News>();
        Query q = em.createQuery("SELECT n FROM News n WHERE n.newsID >= :fromId");
        q.setParameter("fromId", fromId);
        list = q.getResultList();
        return list;
    }

    @Override
    public List getViewNew(int idEmployee) {
          Query query = em.createQuery("SELECT n FROM News n where n.employeeID = :idEmployee order by n.employeeID desc");
         Employee id = employeeFacade.find(idEmployee);
       query.setParameter("idEmployee", id);
       List<News> list = query.getResultList();
       return list;
       
    }

    @Override
    public List<Employee> getEmployeeID(String userName) {
         Query query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName");
        query.setParameter("userName", userName);
        query.setMaxResults(1);
        return query.getResultList();
    }

     
    
}
