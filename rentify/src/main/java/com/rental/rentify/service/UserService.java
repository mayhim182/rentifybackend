package com.rental.rentify.service;

import com.rental.rentify.iservice.IUserService;
import com.rental.rentify.model.LoginUserCredentials;
import com.rental.rentify.model.UserDetails;
import com.rental.rentify.model.UserType;
import com.rental.rentify.repository.IUserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {

  @Autowired
  IUserRepository userRepository;

  @Override
  public UserDetails register(UserDetails userDetails) {

    //check if the user already exists
    int buyerOrSeller = userDetails.getUserType().equals(UserType.SELLER) ? 0 : 1;
    UserDetails userDetailsFromDb = userRepository.findByEitherPhoneNumberOrEmailBasedOnSeller(userDetails.getEmail(),userDetails.getPhoneNumber(), userDetails.getUserType());
    if (userDetailsFromDb != null) {
      throw new RuntimeException("User Already Exists");
    }

    UserDetails userDetails1 = userRepository.save(userDetails);
    return userDetails1;
  }

  @Override
  public Boolean loginUser(LoginUserCredentials loginUserCredentials) throws Exception{
    int buyerOrSeller = loginUserCredentials.getBuyerOrSeller().equals(UserType.SELLER) ? 0 : 1;
    UserDetails userDetails = userRepository.findByEitherPhoneNumberOrEmailBasedOnSeller(loginUserCredentials.getEmail(),loginUserCredentials.getPhoneNumber(), loginUserCredentials.getBuyerOrSeller());
    if(userDetails != null) {
      return loginUserCredentials.getPassword().equals(userDetails.getPassword());
    }
    return Boolean.FALSE;
  }
}
