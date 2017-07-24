/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms;

import com.mycompany.simple.jms.core.EmployeeCreatedMessageConverter;
import com.mycompany.simple.jms.core.MyMessageListener;
import javax.jms.Destination;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

/**
 *
 * @author jan_s
 */
@Configuration
public class AppConfiguration {
    
    @Bean
    public ActiveMQConnectionFactory connectionFactory()
    {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory() ;
        connectionFactory.setBrokerURL("tcp://localhost:61616");
        
        return connectionFactory;
    }
    
    @Bean
    public EmployeeCreatedMessageConverter messageConverter()
    {
        return new EmployeeCreatedMessageConverter();
    }
    
     @Bean
    public JmsTemplate jmsTemplate()
    {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory()) ;
        Destination d = new ActiveMQQueue("myqueue");        
        jmsTemplate.setDefaultDestination(d);
        jmsTemplate.setMessageConverter(messageConverter());
        
        return jmsTemplate;
    }
    
    @Bean
    public MyMessageListener messageListner()
    {
        return new MyMessageListener();
    }
    
    @Bean
    public DefaultMessageListenerContainer jmsContainer()
    {
        DefaultMessageListenerContainer listnerContainer = new DefaultMessageListenerContainer() ;
        listnerContainer.setConnectionFactory(connectionFactory());
        Destination d = new ActiveMQQueue("myqueue");     
        listnerContainer.setDestination(d);
        listnerContainer.setMessageListener(messageListner());        
        return listnerContainer;
    }
    
    
    
}
