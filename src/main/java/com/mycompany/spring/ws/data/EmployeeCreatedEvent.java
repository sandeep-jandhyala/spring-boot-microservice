/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring.ws.data;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Component
public class EmployeeCreatedEvent implements Serializable {
    
    Long employeeId;
    
    

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

   
    public String toString()
    {
        return "EmployeeCreatedEvent: EmployeeId(" + employeeId + ")" ;
    }
    
    }
