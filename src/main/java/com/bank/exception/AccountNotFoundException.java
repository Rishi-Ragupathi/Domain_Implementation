package com.bank.exception;

/**
 * Exception thrown when a bank account is not found.
 */
public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException(String message) {
    super(message);
  }
}