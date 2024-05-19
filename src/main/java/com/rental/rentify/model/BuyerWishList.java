package com.rental.rentify.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Entity
@Table(name="buyer_wishlist1")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BuyerWishList {
  private Long buyer_id;
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long property_id;
}
