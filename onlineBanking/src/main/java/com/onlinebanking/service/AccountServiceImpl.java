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
import com.onlinebanking.repository.TransactionRepository;
import com.onlinebanking.repository.UserRepository;
import com.onlinebanking.util.AccountNumberCreater;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepo;
	@Autowired
	private UserRepository userRepo;
    private TransactionRepository trasactionRepo;
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
		user.getAccounts().add(a);
		userRepo.save(user);
		return a;
	}

	@Override
	public Account getAccountById(String accountNumber) {
		Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			throw new RuntimeException("Account is not found with account number");
		}
		return account.get();
	}

	@Override
	public List<Account> getAllAccounts() {
		List<Account> list = accountRepo.findAll();
		if (list.isEmpty()) {
			throw new RuntimeException("Somthing went wrong account list is empty");
		}
		return list;
	}

	@Override
	public List<Account> getAccountsByUserId(String username) {
		Optional<User> u = userRepo.findByUsername(username);
		if (!u.isPresent()) {
			throw new RuntimeException("User provided username is not present please provid valid username");
		}
		List<Account> list = u.get().getAccounts();
		if (list.isEmpty()) {
			throw new RuntimeException("account list is empty user don't have any account");
		}
		return list;
	}

	@Override
	public Account deleteAccount(String accountNumber) {
		Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			throw new RuntimeException("Provided account number is not present");
		}
		accountRepo.delete(account.get());

		return account.get();
	}

	@Override
	public Account deposit(String accountNumber, double amount) {
		Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			throw new RuntimeException("Provided account number is not present");
		}
		Account acc = account.get();
		double totalAmount = acc.getBalance() + amount;
		acc.setBalance(totalAmount);

		return accountRepo.save(acc);
	}

	@Override
	public Account withdraw(String accountNumber, double amount) {
		
		
   		/**finding the account number in database*/
		Optional<Account> account = accountRepo.findByAccountNumber(accountNumber);
		if (!account.isPresent()) {
			throw new RuntimeException("Provided account number is not present");
		}
		Account acc = account.get();
		
		/** ensuring the user if he has same account or not*/
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Account> list= user.getAccounts();
		Optional<Account> ac=list.stream().filter(a->a.getAccountNumber()==accountNumber).findAny();
		if(!ac.isPresent()) {
			throw new RuntimeException("Account number you entered is not your account please verify your account number");
		}
		
		/** ensuring available balance is enough for withdraw*/
		if (acc.getBalance() < amount) {
			throw new RuntimeException("Insufficient funds for withdraw");
		}
		
		/** finally withdraw process is done*/
		double totalbal = acc.getBalance() - amount;
		acc.setBalance(totalbal);
		return accountRepo.save(acc);
	}

	@Override
	public Transaction transfer(String sourceAccountId, String destinationAccountId, double amount) {
		Account sourceAcc= withdraw(sourceAccountId, amount);
		Account destinationAcc= deposit(destinationAccountId, amount);
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Transaction transaction= new Transaction(sourceAcc, destinationAcc, user, amount, LocalDate.now());
		return trasactionRepo.save(transaction);
	}

}
