package com.bank.account.mapper.domen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Customer {
    String id;
    String firstName;
    String lastName;
    LocalDateTime dateOfBirth;
    boolean active;
}
