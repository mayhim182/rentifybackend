package com.rental.rentify.repository;

import com.rental.rentify.model.UserDetails;
import com.rental.rentify.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserDetails, String> {
  List<UserDetails> findByEmail(String email);
  List<UserDetails> findByPhoneNumber(String phoneNumber);

  @Query("select ud from UserDetails ud where (ud.email =:email or ud.phoneNumber =:phoneNumber) and ud.userType =:buyerOrSeller")
  UserDetails findByEitherPhoneNumberOrEmailBasedOnSeller(String email, String phoneNumber, UserType buyerOrSeller);
}
