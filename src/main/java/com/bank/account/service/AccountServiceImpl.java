package com.bank.account.service;

import com.bank.account.mapper.AccountMapper;
import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.dto.*;
import com.bank.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountMapper accountMapper;


    @Override
    public Optional<AccountRespDto> getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountRespDto);
    }

    @Transactional
    public AccountRespDtoForBank create(AccountReqDto accountReqDto) {
        Optional<CustomerRespDto> customerRespDtoById = customerService.getByIdForAccount(accountReqDto.getCustomerId());

        if (customerRespDtoById.get().getAccountRespDto() == null && accountReqDto.getInitialCredit() == 0) {
            Optional<Account> account = accountMapper.accountReqDtoToAccount(accountReqDto)
                    .map(accountRepository::save);
            return createAccountRespDtoForBank(account.get().getId(), accountReqDto.getInitialCredit());

        } else if (customerRespDtoById.get().getAccountRespDto() == null && accountReqDto.getInitialCredit() != 0) {
            Optional<AccountRespDto> accountRespDto = accountMapper.accountReqDtoToAccount(accountReqDto)
                    .map(a -> {
                        a.setBalance(accountReqDto.getInitialCredit());
                        return a;
                    })
                    .map(accountRepository::save)
                    .map(accountMapper::accountToAccountRespDto);

            TransactionReqDto newTransactionReqDto = TransactionReqDto.builder()
                    .accountId(accountRespDto.get().getAccountId())
                    .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                    .amount(accountReqDto.getInitialCredit())
                    .build();
            transactionService.create(newTransactionReqDto);

            return createAccountRespDtoForBank(accountRespDto.get().getAccountId(), accountReqDto.getInitialCredit());


        } else if (customerRespDtoById.get().getAccountRespDto() != null && accountReqDto.getInitialCredit() != 0) {

            Optional<AccountRespDto> updateAccountRespDto =
                    updateBalance(customerRespDtoById.get().getAccountRespDto().getAccountId(), accountReqDto);

            TransactionReqDto newTransactionReqDto = TransactionReqDto.builder()
                    .accountId(updateAccountRespDto.get().getAccountId())
                    .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                    .amount(accountReqDto.getInitialCredit())
                    .build();
            transactionService.create(newTransactionReqDto);
            return createAccountRespDtoForBank(updateAccountRespDto.get().getAccountId(), accountReqDto.getInitialCredit());
        } else if (customerRespDtoById.get().getAccountRespDto() != null && accountReqDto.getInitialCredit() == 0) {
            return createAccountRespDtoForBank(customerRespDtoById.get().getAccountRespDto().getAccountId(),
                    accountReqDto.getInitialCredit());
        }
        return null;
    }

    public AccountRespDtoForBank createAccountRespDtoForBank(Long accountId, Double amount) {
        return AccountRespDtoForBank.builder()
                .accountId(accountId)
                .amount(amount)
                .build();
    }

    @Override
    @Transactional
    public Optional<AccountRespDto> updateBalance(Long accountId, AccountReqDto accountReqDto) {
        Double accountBalance = customerService.getByIdForAccount(accountReqDto.getCustomerId()).get().getAccountRespDto().getBalance();
        return accountRepository.findById(accountId)
                .map(c -> {
                    c.setBalance(accountReqDto.getInitialCredit() + accountBalance);
                    return c;
                })
                .map(accountMapper::accountToAccountRespDto);
    }
}
