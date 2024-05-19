package com.rental.rentify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;


import java.util.ArrayList;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details1")
public class UserDetails {

  @Id
  @GeneratedValue(generator = "Incremental")
  @GenericGenerator(
    name = "Incremental",
    strategy = "org.hibernate.id.IncrementGenerator"
  )
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String phoneNumber;
  private UserType userType;
  private String password;
}
