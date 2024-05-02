package org.example.controller;


import org.example.model.dtos.UserDto;
import org.example.model.entity.Role;
import org.example.model.repository.UserRepo;
import org.example.model.service.AuthService;

public class AuthController {
    private final AuthService service;

    public AuthController() {
        this.service = new AuthService(new UserRepo());
    }

    public void register(UserDto dto) {
        service.register(dto);
    }

    public Role login(String national_code, String password) {
        return service.login(national_code, password);
    }

    public void forgotPass(String numberCard, String nationalCode, String password) {
        service.forgotPass(numberCard, nationalCode, password);
    }
}
