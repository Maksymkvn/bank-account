package com.bank.account.service;


import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.mapper.domen.dto.AccountRespDtoForBank;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface AccountService {

    Optional<AccountRespDto> getById(Long id);

    AccountRespDtoForBank create(AccountReqDto accountReqDto);

    Optional<AccountRespDto> updateBalance(Long id, AccountReqDto accountReqDto);

    AccountRespDtoForBank createAccountRespDtoForBank(Long accountId, Double amount);
}
