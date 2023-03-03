package com.bank.account.mapper.domen.dto;

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
    private Long customerId;
    private List<TransactionRespDtoCustomer> transactions;
}
