package org.example.model.service;


import org.example.model.dtos.AccountDto;
import org.example.model.entity.Account;
import org.example.model.exception.AccountException;
import org.example.model.repository.AccountRepo;
import org.example.util.Regex;

import java.lang.runtime.TemplateRuntime;
import java.util.List;
import java.util.stream.Collectors;

public class AccountService {
    private final AccountRepo repo;

    public AccountService(AccountRepo repo) {
        this.repo = repo;

    }

    public void create() {
        repo.createTable();
    }

    public void save(AccountDto dto) {
        validationUserDto(dto);
        Account account = accountDtoToAccount(dto);
        baseValidation(account);
        repo.save(account);
    }

    private void baseValidation(Account account) {
        if (!account.getNumberAccount().matches(Regex.NumberAccount)) {
            throw new AccountException("invalid numberAccount");
        }
    }

    private Account accountDtoToAccount(AccountDto dto) {
        Account account = new Account();
        account.setId(dto.getId());
        account.setNumberAccount(dto.getNumberAccount());
        account.setBalance(dto.getBalance());
        account.setCardStatus(dto.getCardStatus());
        account.setIssueDate(dto.getIssueDate());
        account.setExpireDate(dto.getExpiredDate());
        return account;
    }

    private void validationUserDto(AccountDto dto) {
        if (dto == null) {
            throw new AccountException("userDto can't be null");
        }
    }

    public void update(AccountDto dto) {
        Account account = accountDtoToAccount(dto);
        repo.update(account);
    }

    public void delete(long id) {
        repo.delete(id);
    }

    public AccountDto findById(long id) {
        return accountToAccountDto(repo.findById(id));
    }

    private AccountDto accountToAccountDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setId(account.getId());
        dto.setNumberAccount(account.getNumberAccount());
        dto.setBalance(account.getBalance());
        dto.setCardStatus(account.getCardStatus());
        dto.setIssueDate(account.getIssueDate());
        dto.setExpiredDate(account.getExpireDate());
        return dto;
    }

    public List<AccountDto> findAll() {
        return repo.findAll().stream().map(this::accountToAccountDto).collect(Collectors.toList());
    }

    public Double balance(String accountNumber) {

        return (repo.getBalance(accountNumber));
    }
    public  List<String> transaction(){
        return repo.Transaction();
    }
}
