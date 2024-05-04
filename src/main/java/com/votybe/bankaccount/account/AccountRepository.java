package com.votybe.bankaccount.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findAllByUserId(Integer userId);

    @Query("SELECT a FROM Account a WHERE a.user.id = :userId AND a.id = :accountId")
    Account findSpecificAccountByUserId(Integer userId, Integer accountId);
}
