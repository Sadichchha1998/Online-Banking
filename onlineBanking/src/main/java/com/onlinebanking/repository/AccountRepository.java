
package com.onlinebanking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinebanking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
  public Optional<Account> findByAccountNumber(String accountNumber);
}
