package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Builder
public class CustomerRespDto {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean active;
}
