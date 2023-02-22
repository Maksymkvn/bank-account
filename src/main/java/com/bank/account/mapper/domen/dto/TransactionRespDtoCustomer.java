package com.bank.account.mapper.domen.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionRespDtoCustomer {
    private Long id;
    private Double amount;
    private String operationType;
    private String sourceAccount;
    private String transactionStatus;
    private LocalDateTime transactionTime;
}
