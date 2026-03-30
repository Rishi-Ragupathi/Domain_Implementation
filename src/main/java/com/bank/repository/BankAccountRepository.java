package com.bank.repository;

import com.bank.model.BankAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository class for managing BankAccount entities in memory.
 */
public class BankAccountRepository {

  private final Map<Long, BankAccount> accountById = new HashMap<>();
  private final Map<Long, BankAccount> accountByNumber = new HashMap<>();

  /**
   * Saves a bank account.
   */
  public BankAccount save(BankAccount account) {
    accountById.put(account.getId(), account);
    accountByNumber.put(account.getAccountNumber(), account);
    return account;
  }

  /**
   * Finds an account by ID.
   */
  public BankAccount findById(Long id) {
    return accountById.get(id);
  }

  /**
   * Finds an account by account number.
   */
  public BankAccount findByAccountNumber(Long accountNumber) {
    return accountByNumber.get(accountNumber);
  }

  /**
   * Returns all bank accounts.
   */
  public List<BankAccount> findAll() {
    return new ArrayList<>(accountById.values());
  }

  /**
   * Deletes an account by ID.
   */
  public void delete(Long id) {
    BankAccount removed = accountById.remove(id);
    if (removed != null) {
      accountByNumber.remove(removed.getAccountNumber());
    }
  }

  /**
   * Filters accounts by type.
   */
  public List<BankAccount> filterByType(String type) {
    List<BankAccount> filter = new ArrayList<>();

    for (BankAccount account : accountById.values()) {
      if (account.getAccountType().name().equalsIgnoreCase(type)) {
        filter.add(account);
      }
    }
    return filter;
  }
}