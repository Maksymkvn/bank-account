package com.bank.account.mapper.domen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class CustomerRespDto {
    Long id;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    boolean active;
}
