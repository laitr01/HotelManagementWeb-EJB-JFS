/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.employee;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface EmployeeFacadeLocal {

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    List<Employee> findRange(int[] range);

    int count();

    boolean login(String username, String pass);

    List<Employee> getEmployeeID(String userName);

    Employee findUser(String userName);

    boolean upPass(String pass, int empID);
    
    List<Employee> showEmp();

}
