package com.bank.account.service;

import com.bank.account.mapper.AccountMapper;
import com.bank.account.mapper.TransactionMapper;
import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.Transaction;
import com.bank.account.mapper.domen.dto.*;
import com.bank.account.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final TransactionService transactionService;
    private final AccountMapper accountMapper;
    private final TransactionMapper transactionMapper;


    @Override
    public Optional<AccountRespDto> getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountRespDto);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<AccountRespDto> create(AccountReqDto accountReqDto) {
        Optional<CustomerRespDto> customerRespDtoById = customerService.getById(accountReqDto.getCustomerId());
        if (customerRespDtoById.get().getAccount() == null & accountReqDto.getInitialCredit() == 0) {
            return accountMapper.accountReqDtoToAccount(accountReqDto)
                    .map(accountRepository::save)
                    .map(accountMapper::accountToAccountRespDto);
        } else if (customerRespDtoById.get().getAccount() == null & accountReqDto.getInitialCredit() != 0) {

//            TransactionReqDto newTransactionReqDto = TransactionReqDto.builder()
//                    .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
//                    .amount(accountReqDto.getInitialCredit())
//                    .build();
//            transactionService.create(newTransactionReqDto);
//
//            Transaction transaction = transactionMapper.transactionReqDtoToTransaction(newTransactionReqDto).get();
//            List<Transaction> addToTransaction = new ArrayList<>();
//            addToTransaction.add(transaction);

            Optional<AccountRespDto> accountRespDto = accountMapper.accountReqDtoToAccount(accountReqDto)
                    .map(a -> {
                        a.setBalance(accountReqDto.getInitialCredit());
 //                       a.setTransactions(addToTransaction);
                        return a;
                    })
                    .map(accountRepository::save)
                    .map(accountMapper::accountToAccountRespDto);

 //               createTransaction(accountRespDto.get(), accountReqDto.getInitialCredit());
//            Account account = accountMapper.accountRespDtoToAccount(accountRespDto.get());
            TransactionReqDto newTransactionReqDto = TransactionReqDto.builder()
                    .accountId(accountRespDto.get().getAccountId())
                    .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                    .amount(accountReqDto.getInitialCredit())
                    .build();
            transactionService.create(newTransactionReqDto);
            return accountRespDto;

        } else if (customerRespDtoById.get().getAccount() != null & accountReqDto.getInitialCredit() != 0) {
            Optional<AccountRespDto> updateAccountRespDto = updateBalance(customerRespDtoById.get().getAccount().getId(), accountReqDto);

            TransactionReqDto newTransactionReqDto = TransactionReqDto.builder()
                    .accountId(updateAccountRespDto.get().getAccountId())
                    .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                    .amount(accountReqDto.getInitialCredit())
                    .build();
            transactionService.create(newTransactionReqDto);
            return updateAccountRespDto;
        }
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<AccountRespDto> updateBalance(Long id, AccountReqDto accountReqDto) {
        Double accountBalance = customerService.getById(accountReqDto.getCustomerId()).get().getAccount().getBalance();
        return accountRepository.findById(id)
                .map(c -> {
                    c.setBalance(accountReqDto.getInitialCredit() + accountBalance);
                    return c;
                })
                .map(accountMapper::accountToAccountRespDto);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Optional<TransactionRespDto> createTransaction(AccountRespDto accountRespDto, Double amount){
                    TransactionReqDto newTransactionReqDto = TransactionReqDto.builder()
                    .account(accountMapper.accountRespDtoToAccount(accountRespDto))
                    .transactionTime(LocalDateTime.now(ZoneId.systemDefault()))
                    .amount(amount)
                    .build();
          return  transactionService.create(newTransactionReqDto);
    }
    public List<Transaction> addToList(Transaction transaction){
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(transaction);
        return transactionList;
    }
}
