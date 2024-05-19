package com.rental.rentify.iservice;

import com.rental.rentify.model.LoginUserCredentials;
import com.rental.rentify.model.UserDetails;

public interface IUserService {
  UserDetails register(UserDetails userDetails);

  Boolean loginUser(LoginUserCredentials loginUserCredentials) throws Exception;

}
