package com.example.web_demo.controller;

import com.example.web_demo.entity.Account;
import com.example.web_demo.exception.DataAlreadyExistException;
import com.example.web_demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController//处理页面对象
public class AccountController {
    @Autowired
    private  AccountService accountService;

    @GetMapping("/api/accounts")  // 获取所有账户。
    public ResponseEntity<Iterable<Account>> getAccountByName() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/api/accounts/{name}")//根据名称查询单个账户
    public ResponseEntity<Account> getAccountByName(@PathVariable String name) {
        try{
            Account account = accountService.getAccountByName(name);
            return ResponseEntity.ok(account);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("api/accounts")
    public ResponseEntity<Account> createAccount(@RequestBody  Account account) {
        try{
            accountService.createAccount(account);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{name}")
                    .buildAndExpand(account.getName())
                    .toUri();
            return ResponseEntity.created(uri).body(account);
        }catch (DataAlreadyExistException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PutMapping("api/accounts/{name}")
    public ResponseEntity<Account> updateAccount(@PathVariable String name, @RequestBody Account account) {
        try{
            accountService.updateAccount(name, account);
            return ResponseEntity.ok(account);
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().build();
        }


    }

    @DeleteMapping("api/accounts/{name}")
    public void deleteAccount(@PathVariable String name) {
        accountService.deleteAccount(name);
    }

    @DeleteMapping("api/accounts")
    public void deleteAllAccounts() {
        accountService.deleteAllAccounts();
    }
}
