package com.rental.rentify.repository;

import com.rental.rentify.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPropertyRepository extends JpaRepository<Property,Long> {
}
