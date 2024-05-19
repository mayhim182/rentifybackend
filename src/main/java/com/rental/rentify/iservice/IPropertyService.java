package com.rental.rentify.iservice;

import com.rental.rentify.dtos.PropertyFetchDto;
import com.rental.rentify.model.Property;

import java.util.List;

public interface IPropertyService {
  String addPropertyForUser(Property property) throws Exception;

  List<PropertyFetchDto> getAllProperties() throws Exception;

  PropertyFetchDto buyerLikingProperty(PropertyFetchDto propertyFetchDto) throws Exception;

   PropertyFetchDto buyerLikingPropertyV1(PropertyFetchDto propertyFetchDtos) throws Exception;
}
