package com.projebank.projebanka.controller;

import com.projebank.projebanka.dto.AccountDto;
import com.projebank.projebanka.dto.CreateAccountRequest;
import com.projebank.projebanka.dto.UpdateAccountRequest;
import com.projebank.projebanka.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){

        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable String id){

        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> creaateAccount(@RequestBody CreateAccountRequest createAccountRequest){
        return ResponseEntity.ok(accountService.createAccount(createAccountRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountDto> updateAccount(@PathVariable String id, @RequestBody UpdateAccountRequest updateAccountRequest){
        return ResponseEntity.ok(accountService.updateAccount(id, updateAccountRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable String id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/withdraw/{id}/{amount}")
    public ResponseEntity<AccountDto> withdrawMoney(@PathVariable String id, @PathVariable Double amount){

        return ResponseEntity.ok(accountService.withdrawMoney(id, amount));
    }

    @PutMapping("/add/{id}/{amount}")
    public ResponseEntity<AccountDto> addMoney(@PathVariable String id, @PathVariable Double amount){

        return ResponseEntity.ok(accountService.addMoney(id, amount));
    }



}
