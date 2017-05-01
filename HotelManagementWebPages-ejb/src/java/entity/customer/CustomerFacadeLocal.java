/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.customer;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface CustomerFacadeLocal {

    void create(Customer customer);

    void edit(Customer customer);

    void remove(Customer customer);

    Customer find(Object id);

    List<Customer> findAll();

    List<Customer> findRange(int[] range);

    int count();
    
    boolean login(String email, String pass);
    
    boolean checkstatus(String email);
    
    Customer findEmail(String email);
    
    boolean upPass(String pass, int cusId);
    
    Customer findCustomerByEmail(String email);
    
    List<Customer> getCustomerID(String fullName);
    
    boolean lock(int cusid);
    
    boolean unLock(int cusid);
    
    boolean duplicateIdentifier(String identifier);
    
    boolean duplicatePhone(String phone);
    boolean duplicateEmail(String email);
}
