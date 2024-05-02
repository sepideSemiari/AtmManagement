package org.example.model.entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account extends BaseEntity {
    private String NumberAccount;
    private Double balance;
    private CardStatus cardStatus;
    private Timestamp issueDate;
    private Timestamp expireDate;


}
