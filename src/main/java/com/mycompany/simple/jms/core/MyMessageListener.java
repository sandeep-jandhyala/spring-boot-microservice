/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.core;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.springframework.stereotype.Component;

/**
 *
 * @author jan_s
 */
@Component
public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message m) {

        try {
           System.out.println("Received a new message ");
            MapMessage message = (MapMessage) m;
            System.out.println("Sending offer letter for " + message.getLong("employeeId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
