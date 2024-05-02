package org.example.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends BaseEntity {
    private String nationalCode;
    private String firstName;
    private String LastName;

}