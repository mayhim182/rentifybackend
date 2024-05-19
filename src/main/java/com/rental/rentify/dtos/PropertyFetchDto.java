package com.rental.rentify.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyFetchDto {
  private Long id;
  private String place;
  private String area;
  private int numberOfBedrooms;
  private String hospitalsNearby;
  private String collegesNearby;
  private int totalLikes;
  private String email;
  private String phoneNumber;
}
