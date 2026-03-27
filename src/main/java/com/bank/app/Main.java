package com.bank.app;

import com.bank.model.*;
import com.bank.repository.BankAccountRepository;
import com.bank.service.BankAccountService;
import com.bank.exception.AccountNotFoundException;

import java.util.Date;

public class Main {

    public static void main(String[] args) {

        BankAccountRepository repository = new BankAccountRepository();
        BankAccountService service = new BankAccountService(repository);

        try {
            SavingAccount savingAccount = getSavingAccount();
            PremiumAccount premiumAccount = getPremiumAccount();

            BankAccount created = service.createAccount(savingAccount);

            System.out.println("------------Saving Account Created----------");
            printAccount(created);

            System.out.println("------------After Deposit------------");
            service.deposit(created.getId(), 5000);
            System.out.println("Final Balance: " + service.getById(created.getId()).getBalance());
            System.out.println("-------------------------------------");

            BankAccount createdPremium = service.createAccount(premiumAccount);

            System.out.println("------------Premium Account Created----------");
            printAccount(createdPremium);

            service.getById(999L);

        } catch (AccountNotFoundException e) {
            System.out.println("Account Error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected Error: " + e.getMessage());
        }

        System.out.println("Program continues safely...");
    }

    private static void printAccount(BankAccount acc){
        System.out.println("Name : " + acc.getCustomer().getCustomerName());
        System.out.println("Email : " + acc.getCustomer().getCustomerEmail());
        System.out.println("Aadhaar Number : " + acc.getCustomer().getAadhaarNumber());
        System.out.println("Phone Number : " + acc.getCustomer().getPhoneNumber());
        System.out.println("Account Number: " + acc.getAccountNumber());
        System.out.println("Account Balance : " + acc.getBalance());
        System.out.println("Street : " + acc.getAddress().getStreet());
        System.out.println("City : " + acc.getAddress().getCity());
        System.out.println("State : " + acc.getAddress().getState());
        System.out.println("ZipCode : " + acc.getAddress().getZipCode());
        System.out.println("-------------------------------------");
    }

    private static SavingAccount getSavingAccount() {
        Customer customer = new Customer(
                1L,
                "Rishi",
                "risgiragu25@gmail.com",
                580630308668L,
                "9443444404"
        );

        Address address = new Address(
                1L,
                "Tambaram",
                "Chennai",
                "Tamil Nadu",
                636005L
        );

        return getSavingAccount(customer, address);
    }

    private static SavingAccount getSavingAccount(Customer customer, Address address) {
        SavingAccount savingAccount = new SavingAccount();
        savingAccount.setId(1L);
        savingAccount.setCustomer(customer);
        savingAccount.setAddress(address);
        savingAccount.setAccountNumber(12345678900L);
        savingAccount.setBalance(150000D);
        savingAccount.setInterestRate(5D);
        savingAccount.setOpenDate(new Date());
        savingAccount.setAccountType(AccountType.SAVINGS);
        savingAccount.setWithdrawLimit(10);
        savingAccount.setMaxWithdrawalPerWeek(10000D);
        return savingAccount;
    }

    private static PremiumAccount getPremiumAccount() {
        Customer customer = new Customer(
                2L,
                "Rishi Kumar",
                "rishikumar25@gmail.com",
                580630308667L,
                "9443444405"
        );

        Address address = new Address(
                2L,
                "Tambaram",
                "Chennai",
                "Tamil Nadu",
                636005L
        );

        PremiumAccount premiumAccount = new PremiumAccount();
        premiumAccount.setId(2L);
        premiumAccount.setCustomer(customer);
        premiumAccount.setAddress(address);
        premiumAccount.setAccountNumber(12345678901L);
        premiumAccount.setBalance(150000D);
        premiumAccount.setInterestRate(5D);
        premiumAccount.setOpenDate(new Date());
        premiumAccount.setAccountType(AccountType.PREMIUM);
        premiumAccount.setLoyaltyPoints(10);

        return premiumAccount;
    }
}