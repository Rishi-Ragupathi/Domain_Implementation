package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Saving account for customer.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SavingAccount extends BankAccount {

  private Integer withdrawLimit;
  private Double maxWithdrawalPerWeek;
}