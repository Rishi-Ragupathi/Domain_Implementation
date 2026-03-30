package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;
import java.util.List;

/**
 * Service class for handling BankAccount business logic.
 */
public class BankAccountService {

  private final BankAccountRepository repository;

  /**
   * Constructor for BankAccountService.
   */
  public BankAccountService(BankAccountRepository repository) {
    this.repository = repository;
  }

  /**
   * Creates a new bank account.
   */
  public BankAccount createAccount(BankAccount account) {
    return repository.save(account);
  }

  /**
   * Fetches account by ID.
   */
  public BankAccount getById(Long id) {
    BankAccount acc = repository.findById(id);
    if (acc == null) {
      throw new AccountNotFoundException("Account not found");
    }
    return acc;
  }

  /**
   * Fetches account by account number.
   */
  public BankAccount getByAccountNumber(Long accountNumber) {
    BankAccount acc = repository.findByAccountNumber(accountNumber);
    if (acc == null) {
      throw new AccountNotFoundException("Account not found");
    }
    return acc;
  }

  /**
   * Fetches all accounts.
   */
  public List<BankAccount> getAll() {
    return repository.findAll();
  }

  /**
   * Updates account balance.
   */
  public BankAccount update(Long id, double newBalance) {
    BankAccount acc = getById(id);
    acc.setBalance(newBalance);
    return repository.save(acc);
  }

  /**
   * Deletes an account.
   */
  public void delete(Long id) {
    getById(id);
    repository.delete(id);
  }

  /**
   * Deposits amount into account.
   */
  public void deposit(Long id, double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Invalid amount");
    }

    BankAccount acc = getById(id);
    acc.setBalance(acc.getBalance() + amount);
    repository.save(acc);
  }

  /**
   * Fetches accounts by type.
   */
  public List<BankAccount> getByType(String type) {
    return repository.filterByType(type);
  }
}