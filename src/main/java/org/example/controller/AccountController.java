package org.example.controller;


import org.example.model.dtos.AccountDto;
import org.example.model.repository.AccountRepo;
import org.example.model.service.AccountService;

import java.util.List;

public class AccountController {
    private final AccountService accountService;

    public AccountController() {
        accountService = new AccountService(new AccountRepo());
    }

    public void create() {
        accountService.create();
    }

    public void update(AccountDto dto) {
        accountService.update(dto);
    }

    public void delete(long id) {
        accountService.delete(id);
    }

    public AccountDto findById(long id) {
        return accountService.findById(id);
    }

    public List<AccountDto> finaAll() {
        return accountService.findAll();
    }

    public Double balance(String accountNumber) {
        return accountService.balance(accountNumber);
    }
    public List<String>transaction(){
        return accountService.transaction();
    }

}
