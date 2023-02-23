package com.bank.account.mapper;

import com.bank.account.mapper.domen.Transaction;
import com.bank.account.mapper.domen.dto.TransactionRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    public List<TransactionRespDto> transactionListToListRespDto(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> TransactionRespDto.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .operationType(t.getOperationType())
                        .sourceAccount(t.getSourceAccount())
                        .transactionTime(t.getTransactionTime())
                        .transactionStatus(t.getTransactionStatus())
                        .build()
                ).toList();
    }
}
