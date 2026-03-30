package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
  private Long id;
  private Long accountNumber;
  private Double balance;
  private Double interestRate;
  private Date openDate;
  private AccountType accountType;
  private Customer customer;
  private Address address;

}