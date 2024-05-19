package com.rental.rentify.service;

import com.rental.rentify.dtos.PropertyFetchDto;
import com.rental.rentify.iservice.IPropertyService;
import com.rental.rentify.model.*;
import com.rental.rentify.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class PropertyService implements IPropertyService {

  @Autowired
  IUserRepository iUserRepository;

  @Autowired
  IBuyerWishListRepository iBuyerWishListRepository;

  @Autowired
  IPropertyRepository iPropertyRepository;

  @Autowired
  IBuyerRepository buyerRepository;

  @Autowired
  IBuyerPropertyRepository iBuyerPropertyRepository;

  //Currently giving option to add only one property
  @Override
  public String addPropertyForUser(Property property) throws Exception{
    int buyerOrSeller = property.getUserDetails().getUserType().equals(UserType.SELLER) ? 0 : 1 ;
    if(buyerOrSeller == 1) {
      throw new Exception("Buyer Cannot Add Property");
    }
    UserDetails userDetails = iUserRepository.findByEitherPhoneNumberOrEmailBasedOnSeller(property.getUserDetails().getEmail(),
      property.getUserDetails().getPhoneNumber(), property.getUserDetails().getUserType());
    if(userDetails == null) {
      throw new Exception("User Does not Exists, Cannot Add Property");
    }
    Property propertyToBeAdded = Property.builder()
      .place(property.getPlace())
      .area(property.getArea())
      .collegesNearby(property.getCollegesNearby())
      .hospitalsNearby(property.getHospitalsNearby())
      .numberOfBedrooms(property.getNumberOfBedrooms())
      .totalLikes(property.getTotalLikes())
      .userDetails(userDetails)
      .build();
    Property property1 = iPropertyRepository.save(propertyToBeAdded);
    if(property1 == null) {
      throw new Exception("Property could not be added");
    }
    return "Success";
  }

  public List<PropertyFetchDto> getAllProperties() throws Exception {
    List<Property> properties = iPropertyRepository.findAll();
    if(CollectionUtils.isEmpty(properties)) {
      throw new Exception("No Records found");
    }
    List<PropertyFetchDto> propertyFetchDtos = new ArrayList<>();

    properties.forEach(property -> {
      propertyFetchDtos.add(PropertyFetchDto.builder()
          .place(property.getPlace())
          .area(property.getArea())
          .id(property.getId())
          .collegesNearby(property.getCollegesNearby())
          .email(property.getUserDetails().getEmail())
          .phoneNumber(property.getUserDetails().getPhoneNumber())
          .hospitalsNearby(property.getHospitalsNearby())
          .numberOfBedrooms(property.getNumberOfBedrooms())
          .totalLikes(property.getTotalLikes())
        .build());
    });
    return propertyFetchDtos;
  }

  @Transactional(rollbackFor = Exception.class)
  @Override
  public PropertyFetchDto buyerLikingPropertyV1(PropertyFetchDto propertyFetchDto) throws Exception {
    UserDetails userDetails = iUserRepository.findByEitherPhoneNumberOrEmailBasedOnSeller(propertyFetchDto.getEmail(),propertyFetchDto.getPhoneNumber(),UserType.BUYER);
    if(userDetails == null) {
      throw new Exception("Seller Does Not Exist");
    }
    Optional<Property> property = iPropertyRepository.findById(propertyFetchDto.getId());
    if(property.isEmpty()) {
      throw new Exception("Property Not Found");
    }
    Property property1 = property.get();
    Set<Property> propertySet = new HashSet<>();

    return null;
  }

//  @Transactional(rollbackFor = Exception.class)
  @Override
  public PropertyFetchDto buyerLikingProperty(PropertyFetchDto propertyFetchDto) throws Exception {
    UserDetails userDetails = iUserRepository.findByEitherPhoneNumberOrEmailBasedOnSeller(propertyFetchDto.getEmail(),propertyFetchDto.getPhoneNumber(),UserType.BUYER);
    if(userDetails == null) {
      throw new Exception("Seller Does Not Exist");
    }
    Optional<Property> property = iPropertyRepository.findById(propertyFetchDto.getId());
    if(property.isEmpty()) {
      throw new Exception("Property Not Found");
    }

    Set<Property> propertySet = buyerRepository.findWishListByPropertyId(propertyFetchDto.getId());

    Buyer buyer = buyerRepository.findByUserId(userDetails.getId());
    if(buyer == null) {
      buyer = new Buyer();
    }
    buyer.setUserDetails(userDetails);
    buyer.setWishlist(propertySet);
    buyerRepository.save(buyer);

    Property property1 = property.get();
    property1.setTotalLikes(property.get().getTotalLikes()+1);
    iPropertyRepository.save(property1);
    propertySet.add(property.get());
//
//    BuyerWishList buyerWishList = BuyerWishList.builder()
//      .buyer_id(userDetails.getId())
//      .property_id(property1.getId())
//      .build();
//    iBuyerWishListRepository.save(buyerWishList)
    return propertyFetchDto;
  }

}
