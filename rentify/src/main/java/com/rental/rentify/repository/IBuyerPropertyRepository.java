package com.rental.rentify.repository;

import com.rental.rentify.model.BuyerProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBuyerPropertyRepository extends JpaRepository<BuyerProperty, Long> {
}
