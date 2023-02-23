package com.bank.account.mapper;

import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.management.OperatingSystemMXBean;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AccountMapper {

    private final TransactionMapper transactionMapper;

    public AccountRespDto accountToAccountRespDto(Account account) {
        return Optional.ofNullable(account)
                .map(a -> AccountRespDto.builder()
                        .id(a.getId())
                        .customerId(a.getCustomerId())
                        .balance(a.getBalance())
                        .transactions(transactionMapper.transactionListToListRespDto(a.getTransactions()))
                        .build())
                .orElse(null);
    }

    public Optional<Account> accountReqDtoToAccount(AccountReqDto accountReqDto) {
        return Optional.ofNullable(accountReqDto)
                .map(a -> Account.builder()
                        .customerId(a.getCustomerId())
                        .balance(a.getAmount())
                        .build());

    }
}
