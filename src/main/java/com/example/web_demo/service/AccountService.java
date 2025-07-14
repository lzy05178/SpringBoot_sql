package com.example.web_demo.service;

import com.example.web_demo.entity.Account;
import com.example.web_demo.exception.DataAlreadyExistException;
import com.example.web_demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Iterable<Account> getAllAccounts() {
        return accountRepository.getAllAcounts();
    }
    public Account getAccountByName(String name) {
        return accountRepository.findById(name).get();
    }
    public void createAccount(Account account) {
        if (accountRepository.existsById(account.getName())){
            throw new DataAlreadyExistException("Account with name"+ account.getName() +" already exists.");
        }
        accountRepository.save(account);
    }

    public void updateAccount(String name, Account account) {
        if (!accountRepository.existsById(name)){
            throw new NoSuchElementException("Account with name"+ account.getName() +" does not exist.");
        }
        if(!name.equals(account.getName())){
            throw new IllegalArgumentException("Account with name"+ account.getName() +" already exists.");
        }

        accountRepository.save(account);
    }

    public void deleteAccount(String name) {
        accountRepository.deleteById(name);
    }

    public void deleteAllAccounts() {
        accountRepository.deleteAll();
    }
}
