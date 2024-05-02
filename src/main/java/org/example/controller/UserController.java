package org.example.controller;


import org.example.model.dtos.UserDto;
import org.example.model.repository.UserRepo;
import org.example.model.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController() {
        userService = new UserService(new UserRepo());
    }

    public void create() {
        userService.create();
    }

    public void update(UserDto dto) {
        userService.update(dto);
    }

    public void delete(long id) {
        userService.delete(id);
    }

    public UserDto findById(long id) {
        return userService.findById(id);
    }

    public List<UserDto> finaAll() {
        return userService.findAll();
    }

    public void changePass(String numberCard, String nationalCode, String password) {
        userService.changePass(numberCard, nationalCode, password);
    }
}
