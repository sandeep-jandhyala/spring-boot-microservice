/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.controller;

import com.mycompany.simple.jms.service.EmployeeServiceImpl;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 *
 * @author jan_s
 */
@RestController
public class DeferredResultController {
    
        public static final org.slf4j.Logger logger = LoggerFactory.getLogger(DeferredResultController.class);

    
     @RequestMapping("/lookup/users/{user}")
  public DeferredResult<String> get(@PathVariable("user") String input) {
    DeferredResult<String> defResult = new DeferredResult<>();

    new Thread(() -> { 
      String apiResponse = null;
        try {
            apiResponse = callApi(input);
        } catch (InterruptedException ex) {
           logger.error("Exception" +ex);
        }
      defResult.setResult(apiResponse);
    }).start();

    logger.info("Releasing servlet thread");
    return defResult;
  }

  String callApi(String str) throws InterruptedException {
    // restTemplate.invoke(...)
    sleep(5000);
    logger.info("Returning slow task response");
    return str.toUpperCase();
  }
    
    
}
