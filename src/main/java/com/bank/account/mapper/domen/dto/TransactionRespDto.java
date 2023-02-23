package com.bank.account.mapper.domen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class TransactionRespDto {
    private Long id;
    private BigDecimal amount;
    private String operationType;
    private String sourceAccount;
    private String transactionStatus;
    private LocalDateTime transactionTime;
}
