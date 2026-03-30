package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents an address of a customer.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  private Long addressId;
  private String street;
  private String city;
  private String state;
  private Long zipCode;
}

