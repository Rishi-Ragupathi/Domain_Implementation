package com.bank.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents a current bank account.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CurrentAccount extends BankAccount {

  private Double overDraftLimit;
}
