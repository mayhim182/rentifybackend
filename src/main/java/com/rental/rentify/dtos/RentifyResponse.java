package com.rental.rentify.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentifyResponse<T> {
  private String message;
  private String status;
  private T data;
}
