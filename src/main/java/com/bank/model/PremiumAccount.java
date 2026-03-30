package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a premium bank account with loyalty points.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PremiumAccount extends BankAccount {

  private Integer loyaltyPoints;
}