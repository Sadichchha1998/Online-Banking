package com.onlinebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.entity.Account;
import com.onlinebanking.entity.Transaction;
import com.onlinebanking.service.AccountService;

@RestController
@RequestMapping("/acc")
public class AccountController {
	@Autowired
	private AccountService as;

	@PostMapping("/createaccount")
	public ResponseEntity<Account> createAccountHandler(@RequestBody Account account) {
		Account acc = as.createAccount(account);
		return new ResponseEntity<Account>(acc, HttpStatus.CREATED);
	}

	@GetMapping("/{accountNumber}")
	public ResponseEntity<Account> getaccountByacc(@PathVariable("accountNumber") String accountNumber) {
		Account acc = as.getAccountById(accountNumber);
		return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
	}

	@GetMapping("/findbyusername/{username}")
	public ResponseEntity<List<Account>> getbyusernameHandler(@PathVariable("username") String username) {
		List<Account> list = as.getAccountsByUserId(username);
		return new ResponseEntity<List<Account>>(list, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{accountNumber}")
	public ResponseEntity<Account> deletaccounthandler(@PathVariable("accountNumber") String accountNumber) {
		Account acc = as.deleteAccount(accountNumber);
		return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/deposit")
	public ResponseEntity<Account> deposit(@RequestParam("accountNumber") String accountnumber,
			@RequestParam("amount") double amount) {
		Account acc = as.deposit(accountnumber, amount);
		return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
	}

	@PatchMapping("/withdraw")
	public ResponseEntity<Account> withdraw(@RequestParam("accountNumber") String accountnumber,
			@RequestParam("amount") double amount) {
		Account acc = as.withdraw(accountnumber, amount);
		return new ResponseEntity<Account>(acc, HttpStatus.ACCEPTED);
	}

	@PostMapping("/trasfer")
	public ResponseEntity<Transaction> transfer(@RequestParam("sourceAccount") String sourceAccount,
			@RequestParam("destinationAccount") String destinationAccount, @RequestParam("amount") double amount) {
		Transaction transation = as.transfer(sourceAccount, destinationAccount, amount);
		return new ResponseEntity<Transaction>(transation, HttpStatus.ACCEPTED);
	}
    
}
