package com.onlinebanking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlinebanking.entity.Transaction;
import com.onlinebanking.repository.TransactionRepository;

@Service
public class TransationServiceImpl implements TransactionService {
	
	private TransactionRepository tr;

	public TransationServiceImpl(TransactionRepository tr) {
		super();
		this.tr = tr;
	}

	@Override
	public List<Transaction> getAllTransactions() {
		List<Transaction> list= tr.findAll();
		if(list.isEmpty()) {
			throw new RuntimeException("no trasation happen still now");
		}
		return list;
	}

	@Override
	public List<Transaction> getTransactionsByAccountId(String accountId) {
		List<Transaction> list= tr.retrivebyaccountnumber(accountId);
		if(list.isEmpty()) {
			throw new RuntimeException("User doesn't have any trasation");
		}
		return list;
	}

}
