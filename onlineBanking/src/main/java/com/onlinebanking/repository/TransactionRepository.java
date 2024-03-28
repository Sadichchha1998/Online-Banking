package com.onlinebanking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlinebanking.entity.Transaction;
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
//	 @Query("SELECT t FROM Transaction t WHERE t.destinationAccount.accountNumber = :accountNumber OR t.sourceAccount.accountNumber = :accountNumber ORDER BY t.date")
	@Query("SELECT t FROM Transaction t INNER JOIN t.destinationAccount dest INNER JOIN t.sourceAccount source WHERE dest.accountNumber = :accountNumber OR source.accountNumber = :accountNumber ORDER BY t.date")
	    List<Transaction> retrivebyaccountnumber(@Param("accountNumber") String accountNumber);
}
