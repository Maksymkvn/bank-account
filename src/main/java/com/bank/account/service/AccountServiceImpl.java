package com.bank.account.service;

import com.bank.account.mapper.AccountMapper;
import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public Optional<AccountRespDto> getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountRespDto);
    }

    @Override
    @Transactional
    public Optional<AccountRespDto> create(AccountReqDto accountReqDto) {
        return accountMapper.accountReqDtoToAccount(accountReqDto)
                .map(accountRepository::save)
                .map(accountMapper::accountToAccountRespDto);
    }

    @Override
    public Optional<AccountRespDto> update(Long id, AccountReqDto accountReqDto) {
        return Optional.empty();
    }
}
