/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.service;

import com.mycompany.simple.jms.core.MyMessageSender;
import com.mycompany.simple.jms.dao.EmployeeRepository;
import com.mycompany.simple.jms.data.Employee;
import com.mycompany.simple.jms.data.EmployeeCreatedEvent;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Component
public class EmployeeServiceImpl {

    @Autowired
    MyMessageSender sender;

    @Autowired
    EmployeeCreatedEvent event;

    @Autowired
    EmployeeRepository repo;
    
    public Employee findEmployeeById(Long id)
    {
       return  repo.findOne(id);
    }
    
    public List<Employee> findEmployeeByLastName(String lastName)
    {
       return  repo.findByLastName(lastName);
    }

    public Long createEmployee(Employee e) {
        Employee ec = repo.save(e);
        System.out.println("New Employee record created , employee ID " + ec.getEmployeeId() + " assigned. Publishing event.");

        event.setEmployeeId(ec.getEmployeeId());   
        sender.sendMessage(event);
        
        return ec.getEmployeeId();

    }

}
