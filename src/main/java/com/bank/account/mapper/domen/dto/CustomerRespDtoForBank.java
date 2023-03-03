package com.bank.account.mapper.domen.dto;

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
    private Long accountId;
    private Double balance;
    private List<TransactionRespDtoCustomer> transactions;
}
