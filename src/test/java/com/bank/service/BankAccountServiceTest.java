package com.bank.service;

import com.bank.model.*;
import com.bank.repository.BankAccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountServiceTest {

        private BankAccountService bankAccountService;

        @BeforeEach
        void setup(){
            BankAccountRepository bankAccountRepository = new BankAccountRepository();
            bankAccountService = new BankAccountService(bankAccountRepository);
        }

        private SavingAccount createTestAccount(long id){
            SavingAccount savingAccount = new SavingAccount();
            savingAccount.setId(id);
            savingAccount.setAccountNumber(123412341235L);
            savingAccount.setBalance(10000D);
            savingAccount.setAccountType(AccountType.SAVINGS);
            savingAccount.setOpenDate(new Date());

            Customer customer = new Customer(id,"test","test@gmail.com",123412341234L,"998877665544");
            Address address = new Address(id,"Tambaram","Chennai","Tamil Nadu",636002L);

            savingAccount.setCustomer(customer);
            savingAccount.setAddress(address);

            return savingAccount;
        }

        @Test
        void testById(){
            BankAccount bankAccount = bankAccountService.createAccount(createTestAccount(3L));
            BankAccount found = bankAccountService.getById(3L);

            assertNotNull(found);
            assertEquals(bankAccount.getId(), found.getId());
        }

        @Test
        void getByAccountNumber(){
            BankAccount bankAccount = bankAccountService.createAccount(createTestAccount(3L));

            BankAccount found = bankAccountService.getByAccountNumber(123412341235L);

            assertNotNull(found);
            assertEquals(bankAccount.getAccountNumber(), found.getAccountNumber());
        }

    @Test
    void updateTest() {
        bankAccountService.createAccount(createTestAccount(10L));

        BankAccount updated = bankAccountService.update(10L, 20000D);

        assertNotNull(updated);
        assertEquals(20000D, updated.getBalance());

        BankAccount fetched = bankAccountService.getById(10L);
        assertEquals(20000D, fetched.getBalance());
    }

        @Test
        void depositTest(){
            bankAccountService.createAccount(createTestAccount(3L));

            bankAccountService.deposit(3L, 5000);

            BankAccount updated = bankAccountService.getById(3L);
            assertEquals(15000D, updated.getBalance());
        }

        @Test
        void invalidDepositTest(){
            bankAccountService.createAccount(createTestAccount(4L));
            Exception exception = assertThrows(RuntimeException.class,()-> bankAccountService.deposit(4L,-100));
            assertEquals("Invalid amount",exception.getMessage());
        }

        @Test
        void testGetAll(){
            bankAccountService.createAccount(createTestAccount(5L));
            bankAccountService.createAccount(createTestAccount(6L));

            List<BankAccount> list = bankAccountService.getAll();

            assertEquals(2,list.size());
        }

        @Test
        void deleteTest(){
            bankAccountService.createAccount(createTestAccount(7L));
            bankAccountService.delete(7L);

            Exception exception = assertThrows(RuntimeException.class,()-> bankAccountService.getById(7L));

            assertEquals("Account not found",exception.getMessage());
        }

    @Test
    void filterByIdTest(){
        SavingAccount savingAccount = createTestAccount(8L);

        PremiumAccount premiumAccount = getPremiumAccount();

        bankAccountService.createAccount(savingAccount);
        bankAccountService.createAccount(premiumAccount);

        List<BankAccount> list = bankAccountService.getByType("PREMIUM");

        assertEquals(1, list.size());
        assertEquals(AccountType.PREMIUM, list.get(0).getAccountType());
    }

    private static PremiumAccount getPremiumAccount() {
        PremiumAccount premiumAccount = new PremiumAccount();
        premiumAccount.setId(9L);
        premiumAccount.setAccountNumber(123451234566L);
        premiumAccount.setBalance(500000D);
        premiumAccount.setAccountType(AccountType.PREMIUM);
        premiumAccount.setOpenDate(new Date());

        Customer customer = new Customer(9L,"test","test@gmail.com",123412341234L,"998877665544");
        Address address = new Address(9L,"Tambaram","Chennai","Tamil Nadu",636002L);

        premiumAccount.setCustomer(customer);
        premiumAccount.setAddress(address);
        return premiumAccount;
    }
}