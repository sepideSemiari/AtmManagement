package org.example.model.entity;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private String Country;
    private String city;
    private String street;
    private String houseNumber;
    private String unite;
}