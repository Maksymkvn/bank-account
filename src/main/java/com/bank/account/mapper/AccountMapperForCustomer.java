package com.bank.account.mapper;

import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountMapperForCustomer {

    private final TransactionMapper transactionMapper;

    public AccountRespDto customerToRespDto(Account account) {
        return Optional.ofNullable(account)
                .map(a-> AccountRespDto.builder()
                        .accountId(a.getId())
                        .balance(a.getBalance())
                        .customerId(a.getCustomer().getId())
                        .transactions(transactionMapper.transactionalToTransactionalRespDtoForCustomer(a.getTransactions()))
                        .build())
                .orElse(null);
    }
}
