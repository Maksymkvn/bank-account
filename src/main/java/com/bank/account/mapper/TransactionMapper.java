package com.bank.account.mapper;

import com.bank.account.mapper.domen.Transaction;
import com.bank.account.mapper.domen.dto.TransactionReqDto;
import com.bank.account.mapper.domen.dto.TransactionRespDto;
import com.bank.account.mapper.domen.dto.TransactionRespDtoCustomer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TransactionMapper {

    public TransactionRespDto transactionalToTransactionalRespDto(Transaction transaction) {
        return Optional.ofNullable(transaction)
                .map(t -> TransactionRespDto.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .operationType(t.getOperationType())
                        .transactionStatus(t.getTransactionStatus())
                        .transactionTime(t.getTransactionTime())
                        .sourceAccount(t.getSourceAccount())
//                        .account(t.getAccount())
                        .build())
                .orElse(null);
    }

    public Optional<Transaction> transactionReqDtoToTransaction(TransactionReqDto transactionalReqDto) {
        return Optional.ofNullable(transactionalReqDto)
                .map(t -> Transaction.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                        .accountId(t.getAccountId())
                        .build());
    }

    public List<TransactionRespDtoCustomer> transactionalToTransactionalRespDtoForCustomer(List<Transaction> transactions) {
        return transactions.stream()
                .map(t -> TransactionRespDtoCustomer.builder()
                        .id(t.getId())
                        .amount(t.getAmount())
                        .operationType(t.getOperationType())
                        .transactionTime(t.getTransactionTime())
                        .transactionStatus(t.getTransactionStatus())
                        .sourceAccount(t.getSourceAccount())
                        .build())
                .toList();
    }
}
