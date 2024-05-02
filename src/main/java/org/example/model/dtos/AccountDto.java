package org.example.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.entity.CardStatus;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
public class AccountDto {
    private long id;
    private String NumberAccount;
    private Timestamp expiredDate;
    private double balance;
    private CardStatus cardStatus;
    private Timestamp issueDate;

}
