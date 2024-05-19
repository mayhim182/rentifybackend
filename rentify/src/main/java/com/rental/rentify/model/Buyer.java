package com.rental.rentify.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "Buyer")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Builder
public class Buyer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(
    name = "buyer_wishlist",
    joinColumns = @JoinColumn(name = "buyer_id"),
    inverseJoinColumns = @JoinColumn(name = "property_id")
  )
  private Set<Property> wishlist;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "user_details_id")
  private UserDetails userDetails;
}
