package com.onlinebanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
