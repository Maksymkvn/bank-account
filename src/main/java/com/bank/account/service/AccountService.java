package com.bank.account.service;


import com.bank.account.mapper.domen.Transaction;
import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

    Optional<AccountRespDto> getById(Long id);

    Optional<AccountRespDto> create(AccountReqDto accountReqDto);

    Optional<AccountRespDto> updateBalance(Long id, AccountReqDto accountReqDto);
    List<Transaction> addToList(Transaction transaction);
}
