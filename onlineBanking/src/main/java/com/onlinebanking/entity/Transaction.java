package com.onlinebanking.entity;




import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    @ManyToOne
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // If you want to track which user initiated the transaction

    private double amount;

    private LocalDate date;
    private String msg;
	public Transaction(Account sourceAccount, Account destinationAccount, User user, double amount, LocalDate date,String msg) {
		super();
		this.sourceAccount = sourceAccount;
		this.destinationAccount = destinationAccount;
		this.user = user;
		this.amount = amount;
		this.date = date;
		this.msg=msg;
	}

    
}

