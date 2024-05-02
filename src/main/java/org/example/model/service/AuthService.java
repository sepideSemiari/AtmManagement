package org.example.model.service;


import org.example.model.dtos.UserDto;
import org.example.model.entity.Role;
import org.example.model.entity.User;
import org.example.model.exception.UserException;
import org.example.model.repository.UserRepo;
import org.example.util.Regex;

public class AuthService {
    private final UserRepo repo;

    public AuthService(UserRepo repo) {
        this.repo = repo;
    }

    public void register(UserDto dto) {
        validateUserDto(dto);
        User user = userDtoToUser(dto);
        baseValidation(user);
        repo.save(user);
    }

    private void baseValidation(User user) {
        if (!(user.getFirstName().matches(Regex.name) &&
                user.getLastName().matches(Regex.lastName) &&
                user.getNationalCode().matches(Regex.National_Code))) {
            throw new UserException("Invalid input");
        }
    }

    private User userDtoToUser(UserDto dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setRegisterDate(dto.getRegisterDate());
        user.setNationalCode(dto.getNationalCode());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setCardNumber(dto.getCardNumber());
        user.setRole(dto.getRole());
        user.setPassword(dto.getPassword());
        return user;
    }

    private void validateUserDto(UserDto dto) {
        if (dto == null) {
            throw new UserException("userDto cannot be null");
        }
    }

    public Role login(String national_code, String password) {
        return repo.login(national_code, password);
    }

    public void forgotPass(String numberCard, String nationalCode, String newPassword) {
        repo.changePassword(numberCard, nationalCode, newPassword);
    }

}
