/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring.ws.service;

import com.mycompany.spring.ws.data.Employee;
import java.util.List;

/**
 *
 * @author jan_s
 */
public interface EmployeeService {
    
    public Employee findEmployeeById(Long id);
    public List<Employee> findEmployeeByLastName(String lastName);
    public Long createEmployee(Employee e);
    
}
