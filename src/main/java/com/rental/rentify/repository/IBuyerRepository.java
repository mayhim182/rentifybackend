package com.rental.rentify.repository;

import com.rental.rentify.model.Buyer;
import com.rental.rentify.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface IBuyerRepository extends JpaRepository<Buyer, Long> {

  @Query("select b.wishlist from Buyer b join b.wishlist w where w.id =:propertyId ")
  Set<Property> findWishListByPropertyId(@Param("propertyId") Long propertyId);

  @Query("select b from Buyer b where b.userDetails.id =:userDetailId")
  Buyer findByUserId(@Param("userDetailId") Long userDetailId);
}
