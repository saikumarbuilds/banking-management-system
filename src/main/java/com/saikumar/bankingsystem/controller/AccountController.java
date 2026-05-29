package com.saikumar.bankingsystem.controller;
import com.saikumar.bankingsystem.entity.Account;
import com.saikumar.bankingsystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService service;

    // Create Account
    @PostMapping("/add")
    public Account createAccount(@RequestBody Account account) {

        return service.createAccount(account);
    }

    // Get All Accounts
    @GetMapping("/fetch")
    public List<Account> getAllAccounts() {

        return service.getAllAccounts();
    }

    // Get Account By ID
    @GetMapping("fetch/{id}")
    public Account getAccountById(@PathVariable int id) {

        return service.getAccountById(id);
    }

    // Deposit Money
    @PutMapping("/deposit/{id}/{amount}")
    public Account depositMoney(@PathVariable int id,
                                @PathVariable double amount) {

        return service.depositMoney(id, amount);
    }

    // Withdraw Money
    @PutMapping("/withdraw/{id}/{amount}")
    public Account withdrawMoney(@PathVariable int id,
                                 @PathVariable double amount) {

        return service.withdrawMoney(id, amount);
    }

    // Delete Account
    @DeleteMapping("delete/{id}")
    public String deleteAccount(@PathVariable int id) {

        return service.deleteAccount(id);
    }
    //Transfer Money
    @PutMapping("/transfer/{fromId}/{toId}/{amount}")
    public String transferMoney(@PathVariable int fromId,
                                @PathVariable int toId,
                                @PathVariable double amount) {

        return service.transferMoney(fromId, toId, amount);
    }
}
