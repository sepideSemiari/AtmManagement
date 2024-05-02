package org.example.model.service;


import org.example.model.dtos.UserDto;
import org.example.model.entity.User;
import org.example.model.exception.UserException;
import org.example.model.repository.UserRepo;
import org.example.util.Regex;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepo repo;

    public UserService(UserRepo repo) {
        this.repo = repo;
    }

    public void create() {
        repo.createTable();
    }

    public void update(UserDto userDto) {
        validateUserDto(userDto);
        User user = UserDtoTouser(userDto);
        updateValidation(user);
        repo.update(user);
    }

    private void updateValidation(User user) {
        baseValidation(user);
        existsUser(user.getId());
    }

    private void existsUser(long id) {
        if (repo.findById(id) == null) {
            throw new UserException("user not found with this id:" + id);
        }
    }

    private void baseValidation(User user) {
        if (!(user.getFirstName().matches(Regex.name) &&
                user.getLastName().matches(Regex.lastName) &&
                user.getNationalCode().matches(Regex.National_Code))) {
            throw new UserException("Invalid input");
        }
    }

    private User UserDtoTouser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setRegisterDate(userDto.getRegisterDate());
        user.setNationalCode(userDto.getNationalCode());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setCardNumber(userDto.getCardNumber());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        return user;

    }

    private void validateUserDto(UserDto dto) {
        if (dto == null) {
            throw new IllegalArgumentException("UserDto cannot be null");
        }
    }

    public void delete(long id) {
        existsUser(id);
        repo.delete(id);
    }

    public UserDto findById(long id) {
        existsUser(id);
        return userToUserDto(repo.findById(id));
    }


    private UserDto userToUserDto(User user) {
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setRegisterDate(user.getRegisterDate());
        dto.setNationalCode(user.getNationalCode());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setCardNumber(user.getCardNumber());
        dto.setRole(user.getRole());
        dto.setPassword(user.getPassword());
        return dto;
    }

    public List<UserDto> findAll() {
        return repo.findAll().stream().map(this::userToUserDto).collect(Collectors.toList());
    }

    public void changePass(String numberCard, String nationalCode, String password) {
        repo.changePassword(numberCard, nationalCode, password);
    }
}
