package com.bank.account.mapper.domen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class Transaction {
    Double amount;
    String id;
    String accountId;
    String operationType;
    String sourceAccount;
    String transactionStatus;
    LocalDateTime transactionTime;
}
