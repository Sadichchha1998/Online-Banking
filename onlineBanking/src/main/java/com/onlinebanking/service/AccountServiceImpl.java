package com.onlinebanking.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.Status;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.entity.User;
import com.onlinebanking.repository.AccountRepository;
import com.onlinebanking.util.AccountNumberCreater;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepo;

	@Override
	public Account createAccount(Account account) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Optional<Account> acc = accountRepo.findByAccountNumber(account.getAccountNumber());
		if (acc.isPresent()) {
			throw new RuntimeException("Account Already exist with same account number");
		}
		account.setAccountNumber(AccountNumberCreater.generateRandomString(10));
		account.setAccountOpeningDate(LocalDate.now());
		account.setUser(user);
		account.setStatus(Status.ACTIVE);
		Account a = accountRepo.save(account);

		return a;
	}

	@Override
	public Account getAccountById(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> getAccountsByUserId(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deleteAccount(String accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account deposit(Long accountId, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account withdraw(Long accountId, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Transaction transfer(Long sourceAccountId, Long destinationAccountId, double amount) {
		// TODO Auto-generated method stub
		return null;
	}

}
