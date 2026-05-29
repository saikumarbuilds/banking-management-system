package com.saikumar.bankingsystem.repository;

import com.saikumar.bankingsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}
