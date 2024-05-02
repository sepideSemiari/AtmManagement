package org.example.model.entity;


import lombok.*;

import java.util.Random;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends Person {

    private String password;
    private Role role;
    private String cardNumber;
    private String address;


    }

