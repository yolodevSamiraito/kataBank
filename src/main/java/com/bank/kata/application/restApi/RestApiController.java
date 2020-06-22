package com.bank.kata.application.restApi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class RestApiController {


    @GetMapping("/")
    public String Home(){
        return "The server is On, the time in the Server is: "+ LocalDateTime.now();
    }


}