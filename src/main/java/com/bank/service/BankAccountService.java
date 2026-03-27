package com.bank.service;

import com.bank.exception.AccountNotFoundException;
import com.bank.model.BankAccount;
import com.bank.repository.BankAccountRepository;

import java.util.List;

public class BankAccountService {

    private final BankAccountRepository repository;

    public BankAccountService(BankAccountRepository repository) {
        this.repository = repository;
    }

    public BankAccount createAccount(BankAccount account){
        return repository.save(account);
    }

    public BankAccount getById(Long id) {
        BankAccount acc = repository.findById(id);
        if (acc == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return acc;
    }

    public BankAccount getByAccountNumber(Long accountNumber) throws AccountNotFoundException {
        BankAccount acc = repository.findByAccountNumber(accountNumber);
        if (acc == null) throw new AccountNotFoundException("Account not found");
        return acc;
    }

    public List<BankAccount> getAll() {
        return repository.findAll();
    }

    public BankAccount update(Long id, double newBalance) {
        BankAccount acc = getById(id);
        acc.setBalance(newBalance);
        return repository.save(acc);
    }

    public void delete(Long id) {
        getById(id);
        repository.delete(id);
    }

    public void deposit(Long id, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }

        BankAccount acc = getById(id);
        acc.setBalance(acc.getBalance() + amount);
        repository.save(acc);
    }

    public List<BankAccount> getByType(String type) {
        return repository.filterByType(type);
    }
}