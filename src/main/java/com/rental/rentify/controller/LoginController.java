package com.rental.rentify.controller;

import com.rental.rentify.iservice.IUserService;
import com.rental.rentify.model.LoginUserCredentials;
import com.rental.rentify.model.UserDetails;
import com.rental.rentify.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentify")
public class LoginController {

  @Autowired
  IUserService userService;

  @PostMapping("/register/v1")
  public ResponseEntity<UserDetails> register(@RequestBody UserDetails userDetails) throws Exception{
    UserDetails userDetails1 = null;
    try{
      userDetails1 = userService.register(userDetails);
      return ResponseEntity.status(HttpStatus.CREATED).body(userDetails1);
    }
    catch (Exception e){
      String message = e.getMessage();
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userDetails1);
    }

  }

  @PostMapping("/login/v1")
  public ResponseEntity<String> login(@RequestBody LoginUserCredentials loginUserCredentials) throws Exception{
    try {
      Boolean userLoggedIn = userService.loginUser(loginUserCredentials);
      return userLoggedIn ? ResponseEntity.status(HttpStatus.OK).body("LoggedIn")
        : ResponseEntity.status(HttpStatus.NOT_FOUND).body("User Not Found");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(e.getMessage());
    }
  }
}
