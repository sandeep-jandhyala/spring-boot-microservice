/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.controller;

import com.mycompany.simple.jms.data.Employee;
import com.mycompany.simple.jms.service.EmployeeServiceImpl;
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

    @Autowired
    EmployeeServiceImpl eService;

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
        System.out.println("creating an Employee");
        Long id = eService.createEmployee(emp);
        return new ResponseEntity<String>("Employee Created , id " +id, HttpStatus.OK);

    }

}
