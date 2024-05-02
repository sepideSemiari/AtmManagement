package org.example.model.entity;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Customer extends User {
    private List<Account> accounts;

}
