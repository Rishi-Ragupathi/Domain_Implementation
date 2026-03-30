package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  private Long customerId;
  private String customerName;
  private String customerEmail;
  private Long aadhaarNumber;
  private String phoneNumber;
}

