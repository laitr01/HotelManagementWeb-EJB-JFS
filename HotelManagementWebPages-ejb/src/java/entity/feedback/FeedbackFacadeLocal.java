/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.feedback;

import entity.customer.Customer;
import entity.employee.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface FeedbackFacadeLocal {

    void create(Feedback feedback);

    void edit(Feedback feedback);

    void remove(Feedback feedback);

    Feedback find(Object id);

    List<Feedback> findAll();

    List<Feedback> findRange(int[] range);

    int count();

    List<Feedback> searchFromId(int fromId);
      List<Employee> getEmployeeID(String userName);

    List<Customer> getCustomerID(String fullName);
}
