package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
public class CustomerRespDtoForBank {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private boolean active;
    private Account account;
    private List<Transaction> transactions;
}
