/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.simple.jms.controller;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GitHubDeferredController {
    
     @RequestMapping("/github/lookup/users/{user}")
  public DeferredResult<String> get(@PathVariable("user") String input) {
    DeferredResult<String> defResult = new DeferredResult<>();

    new Thread(() -> { 
      String apiResponse = null;
        try {
            apiResponse = callApi(input);
        } catch (InterruptedException ex) {
            Logger.getLogger(GitHubDeferredController.class.getName()).log(Level.SEVERE, null, ex);
        }
      defResult.setResult(apiResponse);
    }).start();

    return defResult;
  }

  String callApi(String str) throws InterruptedException {
    // restTemplate.invoke(...)
    sleep(5000);
    return str.toUpperCase();
  }
    
    
}
