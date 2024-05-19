package com.rental.rentify.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserCredentials {
  private String email;
  private String phoneNumber;
  private String password;
  private UserType buyerOrSeller;
}
