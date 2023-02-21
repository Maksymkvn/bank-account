package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.Customer;
import com.bank.account.mapper.domen.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class AccountRespDto {
    private Long accountId;
    private Double balance;
    private Customer customer;
    private List<Transaction> transactions;
}
