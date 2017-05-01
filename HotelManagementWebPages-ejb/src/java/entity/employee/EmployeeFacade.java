/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.employee;

import entity.AbstractFacade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author trach
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal {

    @PersistenceContext(unitName = "HotelManagementWebPages-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public boolean login(String username, String pass) {
        Query q = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName and e.passWord =:pass");

        try {
            q.setParameter("userName", username);
            q.setParameter("pass", pass);
            Employee emp = (Employee) q.getSingleResult();
            if (emp != null) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public Employee findUser(String userName) {
        Query q = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :name");
        try {
            q.setParameter("name", userName);

            Employee emp = (Employee) q.getSingleResult();
            if (emp != null) {
                return emp;
            }
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public List<Employee> getEmployeeID(String userName) {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.userName = :userName");
        query.setParameter("userName", userName);
        query.setMaxResults(1);
        return query.getResultList();
    }

    @Override
    public boolean upPass(String pass, int empID) {
        Query q = em.createQuery("UPDATE Employee e SET e.passWord =:pass WHERE e.employeeID = :empID");
        try {
            q.setParameter("pass", pass);
            q.setParameter("empID", empID);
            return q.executeUpdate() > 0 ? true : false;
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public List<Employee> showEmp() {
        Query query = em.createQuery("SELECT e FROM Employee e WHERE e.role = 'E'");
        
        return query.getResultList();
    }
    

}
