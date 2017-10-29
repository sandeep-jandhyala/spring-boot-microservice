/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.spring.ws;

import com.mycompany.spring.ws.core.EmployeeCreatedMessageConverter;
import com.mycompany.spring.ws.core.MyMessageListener;
import javax.jms.Destination;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
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
    
    @Value("${spring.jms.broker.url}")
    private String brokerUrl;
    
    @Value("${spring.jms.queue.name}")
    private String queueName;
    
    @Bean
    public ActiveMQConnectionFactory connectionFactory()
    {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory() ;
        connectionFactory.setBrokerURL(brokerUrl);
        
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
        Destination d = new ActiveMQQueue(queueName);        
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
        Destination d = new ActiveMQQueue(queueName);     
        listnerContainer.setDestination(d);
        listnerContainer.setMessageListener(messageListner());        
        return listnerContainer;
    }
    
    
    
}
