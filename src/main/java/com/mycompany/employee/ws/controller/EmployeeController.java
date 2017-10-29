/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.employee.ws.controller;

import com.mycompany.employee.ws.data.Employee;
import com.mycompany.employee.ws.service.EmployeeService;
import com.mycompany.employee.ws.service.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jan_s
 */
@RestController
public class EmployeeController {
    
    public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService eService;

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getEmployee(@PathVariable("id") long id) {
        Employee e = eService.findEmployeeById(id);

        if (e == null) {
            return new ResponseEntity<String>("No Matching Employee Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(e, HttpStatus.OK);

    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public ResponseEntity<?> createEmployee(@RequestBody Employee emp) {
        
        logger.info("creating a new Employee");
        Long id = eService.createEmployee(emp);
        return new ResponseEntity<String>("Employee Created , id " +id, HttpStatus.OK);

    }

}
