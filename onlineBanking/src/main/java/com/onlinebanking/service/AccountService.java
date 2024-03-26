package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.Transaction;

public interface AccountService {
   public Account createAccount(Account account);
    
   public Account getAccountById(String accountNumber);
    
   public List<Account> getAllAccounts();
    
   public List<Account> getAccountsByUserId(String username);
    
   public Account deleteAccount(String accountNumber);
    
   public Account deposit(Long accountId, double amount);
    
   public Account withdraw(Long accountId, double amount);
    
   public Transaction transfer(Long sourceAccountId, Long destinationAccountId, double amount);
}

