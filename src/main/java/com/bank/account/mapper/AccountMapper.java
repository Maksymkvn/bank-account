package com.bank.account.mapper;

import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class AccountMapper {
    private final CustomerRepository customerRepository;
    private final TransactionMapper transactionMapper;
    public AccountRespDto accountToAccountRespDto(Account account) {
        return Optional.ofNullable(account)
                .map(a -> AccountRespDto.builder()
                        .accountId(a.getId())
                        .balance(a.getBalance())
                        .customerId(a.getCustomer().getId())
                        .build())
                .orElse(null);
    }
    public Optional<Account> accountReqDtoToAccount(AccountReqDto accountReqDto){
        return Optional.ofNullable(accountReqDto)
                .map(a->Account.builder()
                        .customer(customerRepository.findById(a.getCustomerId()).get())
                        .balance(0.0)
                        .build());
    }
    public Account accountRespDtoToAccount(AccountRespDto accountRespDto){
        return Optional.ofNullable(accountRespDto)
                .map(a->Account.builder()
                        .id(a.getAccountId())
                        .balance(a.getBalance())
                        .build())
                .orElse(null);
    }

}
