/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.news;

import entity.employee.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface NewsFacadeLocal {

    void create(News news);

    void edit(News news);

    void remove(News news);

    News find(Object id);

    List<News> findAll();

    List<News> findRange(int[] range);

    int count();

    List<News> searchFromId(int fromId);

    List getViewNew(int idEmployee);

    List<Employee> getEmployeeID(String userName);

   
    
}
