package com.rental.rentify.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Property")
public class Property {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String place;
  private String area;
  private int numberOfBedrooms;
  private String hospitalsNearby;
  private String collegesNearby;
  private int totalLikes;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  private UserDetails userDetails;
}
