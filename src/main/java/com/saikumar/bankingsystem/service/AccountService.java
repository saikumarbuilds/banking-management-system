package com.saikumar.bankingsystem.service;

import com.saikumar.bankingsystem.entity.Account;
import com.saikumar.bankingsystem.exception.AccountNotFoundException;
import com.saikumar.bankingsystem.exception.InsufficientBalanceException;
import com.saikumar.bankingsystem.repository.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    // Create Account
    public Account createAccount(Account account) {

        return repository.save(account);
    }

    // Get All Accounts
    public List<Account> getAllAccounts() {

        return repository.findAll();
    }

    // Get Account By ID
    public Account getAccountById(int id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account ID " + id + " not found"));
    }

    // Deposit Money
    public Account depositMoney(int id, double amount) {

        Account account = repository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account ID " + id + " not found"));

        account.setBalance(account.getBalance() + amount);

        return repository.save(account);
    }

    // Withdraw Money
    public Account withdrawMoney(int id, double amount) {

        Account account = repository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account ID " + id + " not found"));

        account.setBalance(account.getBalance() - amount);

        return repository.save(account);
    }

    // Delete Account
    public String deleteAccount(int id) {

        Account account = repository.findById(id)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account ID " + id + " not found"));

        repository.delete(account);

        return "Account Deleted Successfully";
    }
    //Fund Transfer
    @Transactional
    public String transferMoney(int fromId, int toId, double amount) {

        Account sender = repository.findById(fromId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Sender Account ID " + fromId + " not found"));

        Account receiver = repository.findById(toId)
                .orElseThrow(() ->
                        new AccountNotFoundException("Receiver Account ID " + toId + " not found"));

        if(sender.getBalance() < amount) {
            throw new InsufficientBalanceException("Insufficient Balance");
        }

        sender.setBalance(sender.getBalance() - amount);

        receiver.setBalance(receiver.getBalance() + amount);

        repository.save(sender);
        repository.save(receiver);

        return "Fund Transfer Successful";
    }
}
