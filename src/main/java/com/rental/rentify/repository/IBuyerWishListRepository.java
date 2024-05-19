package com.rental.rentify.repository;

import com.rental.rentify.model.BuyerWishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBuyerWishListRepository extends JpaRepository<BuyerWishList,Long> {
}
