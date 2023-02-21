package com.bank.account.service;

import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface AccountService {

    Optional<AccountRespDto> getById(Long id);

    Optional<AccountRespDto> create(AccountReqDto accountReqDto);

    Optional<AccountRespDto> update(Long id, AccountReqDto accountReqDto);
}
