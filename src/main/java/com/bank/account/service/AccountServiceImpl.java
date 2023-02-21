package com.bank.account.service;

import com.bank.account.mapper.AccountMapper;
import com.bank.account.mapper.CustomerMapper;
import com.bank.account.mapper.domen.Account;
import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.repository.AccountRepository;
import com.bank.account.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerService customerService;
    private final AccountMapper accountMapper;


    @Override
    public Optional<AccountRespDto> getById(Long id) {
        return accountRepository.findById(id)
                .map(accountMapper::accountToAccountRespDto);
    }

    @Override
    public Optional<AccountRespDto> create(AccountReqDto accountReqDto) {
        Optional<CustomerRespDto> customerRespDtoById = customerService.getById(accountReqDto.getCustomerId());
        if (customerRespDtoById.get().getAccount() == null) {
            Optional<AccountRespDto> accountRespDto = accountMapper.accountReqDtoToAccount(accountReqDto)
                    .map(accountRepository::save)
                    .map(accountMapper::accountToAccountRespDto);
  //          Optional<CustomerRespDto> update = customerService.update(customerRespDtoById, accountMapper.accountRespDtoToAccount(accountRespDto.get());
            return  accountRespDto;
        }
            return null;
    }

    @Override
    public Optional<AccountRespDto> update(Long id, AccountReqDto accountReqDto) {
        return accountRepository.findById(id)
                .map(c -> {
                    if (accountReqDto != null) {
  //                      c.setBalance(accountReqDto.getBalance());
                        return c;
                    }
                    return null;
                })
                .map(accountMapper::accountToAccountRespDto);
    }
}
