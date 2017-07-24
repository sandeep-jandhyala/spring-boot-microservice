/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.dao;

import com.mycompany.simple.jms.data.Employee;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author jan_s
 */
public interface EmployeeRepository extends CrudRepository<Employee,Long>
{
       
    List<Employee> findByLastName(String lastName);
    
    
}
