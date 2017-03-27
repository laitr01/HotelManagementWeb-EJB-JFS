/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity.services;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author trach
 */
@Local
public interface ServiceDetailFacadeLocal {

    void create(ServiceDetail serviceDetail);

    void edit(ServiceDetail serviceDetail);

    void remove(ServiceDetail serviceDetail);

    ServiceDetail find(Object id);

    List<ServiceDetail> findAll();

    List<ServiceDetail> findRange(int[] range);

    int count();
    
}
