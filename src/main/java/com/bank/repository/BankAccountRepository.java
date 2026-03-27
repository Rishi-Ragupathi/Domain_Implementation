package com.bank.repository;

import com.bank.model.BankAccount;

import java.util.*;

public class BankAccountRepository {

    Map<Long, BankAccount> accountById = new HashMap<>();
    Map<Long, BankAccount> accountByNumber = new HashMap<>();

    public BankAccount save(BankAccount account){
        accountById.put(account.getId(),account);
        accountByNumber.put(account.getAccountNumber(),account);
        return account;
    }

    public BankAccount findById(Long id){
        return accountById.get(id);
    }

    public BankAccount findByAccountNumber(Long accountNumber){
        return accountByNumber.get(accountNumber);
    }

    public List<BankAccount> findAll(){
        return new ArrayList<>(accountById.values());
    }

    public void delete(Long id){
        BankAccount removed = accountById.remove(id);
        if (removed != null) {
            accountByNumber.remove(removed.getAccountNumber());
        }
    }

    public List<BankAccount> filterByType(String type){
        List<BankAccount> filter = new ArrayList<>();

        for (BankAccount account : accountById.values()){
            if(account.getAccountType().name().equalsIgnoreCase(type)){
                filter.add(account);
            }
        }
        return filter;
    }

}