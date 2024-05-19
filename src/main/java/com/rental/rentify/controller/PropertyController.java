package com.rental.rentify.controller;

import com.rental.rentify.dtos.PropertyAddedDto;
import com.rental.rentify.dtos.PropertyFetchDto;
import com.rental.rentify.dtos.RentifyResponse;
import com.rental.rentify.iservice.IPropertyService;
import com.rental.rentify.model.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentify")
public class PropertyController {

  @Autowired
  IPropertyService iPropertyService;

  @Transactional(rollbackFor = Exception.class)
  @PostMapping("/addProperty/v1")
   public ResponseEntity<RentifyResponse<PropertyAddedDto>> addPropertyForUser(@RequestBody Property property) throws Exception {
    RentifyResponse<PropertyAddedDto> rentifyResponse = new RentifyResponse<>();
      try{
        String addPropertyMessage = iPropertyService.addPropertyForUser(property);
        PropertyAddedDto propertyAddedDto = PropertyAddedDto.builder().
        message(addPropertyMessage).build();
        rentifyResponse.setData(propertyAddedDto);
        rentifyResponse.setMessage(addPropertyMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(rentifyResponse);
      } catch (Exception e) {
        PropertyAddedDto propertyAddedDto = PropertyAddedDto.builder().
          message(e.getMessage()).build();
        rentifyResponse.setMessage(e.getMessage());
        rentifyResponse.setData(propertyAddedDto);
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(rentifyResponse);
      }
  }

  @PostMapping("/getAllProperties/v1")
  public ResponseEntity<RentifyResponse<List<PropertyFetchDto>>> getAllProperties() throws Exception {
    RentifyResponse<List<PropertyFetchDto>> rentifyResponse = new RentifyResponse<>();
    try {
      List<PropertyFetchDto> propertyFetchDtos = iPropertyService.getAllProperties();
      rentifyResponse.setData(propertyFetchDtos);
      rentifyResponse.setMessage("Success");
      return ResponseEntity.status(HttpStatus.OK).body(rentifyResponse);
    } catch (Exception e) {
      rentifyResponse.setData(null);
      rentifyResponse.setMessage(e.getMessage());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rentifyResponse);
    }
  }

  @Transactional(rollbackFor = Exception.class)
  @PostMapping("/update/likeproperty/v1")
  public ResponseEntity<RentifyResponse<PropertyFetchDto>> likeAProperty(@RequestBody PropertyFetchDto propertyFetchDto) {
    RentifyResponse<PropertyFetchDto> rentifyResponse = new RentifyResponse<>();
    try {
        PropertyFetchDto propertyFetchDto1 = iPropertyService.buyerLikingProperty(propertyFetchDto);
        rentifyResponse.setData(propertyFetchDto1);
        rentifyResponse.setMessage("Added To WishList");
        return ResponseEntity.status(HttpStatus.CREATED).body(rentifyResponse);
    } catch (Exception e){
      rentifyResponse.setData(null);
      rentifyResponse.setMessage("Could Not Update");
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(rentifyResponse);
    }
  }

}
