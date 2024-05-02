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

    public String getCardNumber() {

            Random random = new Random();
            StringBuilder accountNumber = new StringBuilder();
            int firstDigit = random.nextInt(9) + 1;
            accountNumber.append(firstDigit);
            for (int i = 1; i < 16; i++) {
                int digit = random.nextInt(10);
                accountNumber.append(digit);
            }
            return accountNumber.toString();
        }

    }

