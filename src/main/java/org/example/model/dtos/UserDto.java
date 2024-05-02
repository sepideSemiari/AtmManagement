package org.example.model.dtos;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.entity.Role;

import java.sql.Timestamp;

@Getter
@Setter
@ToString

public class UserDto {
    private long id;
    private Timestamp registerDate;
    private String firstName;
    private String lastName;
    private String nationalCode;
    private String password;
    private String cardNumber;
    private Role role;
    private String address;

}
