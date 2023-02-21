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
    public AccountRespDto accountToAccountRespDto(Account account) {
        return Optional.ofNullable(account)
                .stream()
                .findAny()
                .map(a -> AccountRespDto.builder()
                        .accountId(a.getId())
                        .build())
                .orElse(null);
    }
    public Optional<Account> accountReqDtoToAccount(AccountReqDto accountReqDto){
        return Optional.ofNullable(accountReqDto)
                .stream().
                findAny()
                .map(a->Account.builder()
                        .customer(customerRepository.findById(a.getCustomerId()).get())
                        .build());
    }
    public Optional<Account> accountRespDtoToAccount(AccountRespDto accountRespDto){
        return Optional.ofNullable(accountRespDto)
                .stream().
                findAny()
                .map(a->Account.builder()
                        .id(a.getAccountId())
                        .balance(a.getBalance())
                        .customer(a.getCustomer())
                        .transactions(a.getTransactions())
                        .build());
    }
}
