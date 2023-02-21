package com.bank.account.mapper;

import com.bank.account.mapper.domen.Customer;
import com.bank.account.mapper.domen.Transaction;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.TransactionalReqDto;
import com.bank.account.mapper.domen.dto.TransactionalRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionalMapper {

    public TransactionalRespDto transactionalToTransactionalRespDto(Transaction transaction) {
        return Optional.ofNullable(transaction)
                .stream()
                .findAny()
                .map(t -> TransactionalRespDto.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .operationType(t.getOperationType())
                        .transactionStatus(t.getTransactionStatus())
                        .transactionTime(t.getTransactionTime())
                        .sourceAccount(t.getSourceAccount())
                        .build())
                .orElse(null);
    }

    public Optional<Transaction> transactionReqDtoToTransaction(TransactionalReqDto transactionalReqDto){
        return Optional.ofNullable(transactionalReqDto)
                .stream()
                .findAny()
                .map(t -> Transaction.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                        .account(t.getAccount())
                        .build());
    }

}
