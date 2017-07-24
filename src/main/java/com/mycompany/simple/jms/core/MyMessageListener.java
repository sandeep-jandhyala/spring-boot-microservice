/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.core;

import com.mycompany.simple.jms.service.EmployeeServiceImpl;
import static com.mycompany.simple.jms.service.EmployeeServiceImpl.logger;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Component
public class MyMessageListener implements MessageListener {
    
    public static final Logger logger = LoggerFactory.getLogger(MyMessageListener.class);

    @Override
    public void onMessage(Message m) {

        try {
           logger.info("Received a new message ");
            MapMessage message = (MapMessage) m;
            logger.info("Sending offer letter for employeeId " + message.getLong("employeeId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
