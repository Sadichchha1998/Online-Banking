package com.onlinebanking.service;

import java.util.List;

import com.onlinebanking.entity.Transaction;

public interface TransactionService {
	public List<Transaction> getAllTransactions();

	public List<Transaction> getTransactionsByAccountId(String accountId);
}
