/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring.ws.core;

import com.mycompany.spring.ws.data.EmployeeCreatedEvent;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Component
public class EmployeeCreatedMessageConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        EmployeeCreatedEvent ec = (EmployeeCreatedEvent)o;
        MapMessage message = session.createMapMessage();
        message.setLong("employeeId", ec.getEmployeeId());
        return message;

    }

    @Override
    public Object fromMessage(Message msg) throws JMSException, MessageConversionException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
