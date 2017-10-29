/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring.ws.service;

import com.mycompany.spring.ws.controller.EmployeeController;
import com.mycompany.spring.ws.core.MyMessageSender;
import com.mycompany.spring.ws.dao.EmployeeRepository;
import com.mycompany.spring.ws.data.Employee;
import com.mycompany.spring.ws.data.EmployeeCreatedEvent;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Component
public class EmployeeServiceImpl implements EmployeeService{
    
    public static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

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
        logger.info("New Employee record created , employee ID " + ec.getEmployeeId());
        
      //  event.setEmployeeId(ec.getEmployeeId());   
       // sender.sendMessage(event);
        
      //  logger.info("Published Employee Created event to eventbus");
        
        return ec.getEmployeeId();

    }

}
