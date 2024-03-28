package com.onlinebanking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinebanking.entity.Transaction;
import com.onlinebanking.service.TransactionService;

@RestController
@RequestMapping("/trans")
public class TransactionController {
	@Autowired
	private TransactionService trasserv;

	@GetMapping("/getalltransaction")
	public ResponseEntity<List<Transaction>> getMethodName() {
		List<Transaction> list = trasserv.getAllTransactions();
		return new ResponseEntity<List<Transaction>>(list, HttpStatus.ACCEPTED);
	}
    @GetMapping("/{accountNumber}")
	public ResponseEntity<List<Transaction>> gettrasctionbyaccountnum(@PathVariable("accountNumber") String accountNumber) {
		List<Transaction> list = trasserv.getTransactionsByAccountId(accountNumber);
		return new ResponseEntity<List<Transaction>>(list, HttpStatus.ACCEPTED);
	}

}
