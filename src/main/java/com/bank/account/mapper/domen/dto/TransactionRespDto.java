package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionRespDto {
    private Long id;
    private Long accountId;
    private Double amount;
    private String operationType;
    private String sourceAccount;
    private String transactionStatus;
    private LocalDateTime transactionTime;
    private Account account;
}
