package com.bank.account.controller;


import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.service.AccountService;
import com.bank.account.service.CustomerService;
import com.bank.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ManagementController {
    private final AccountService accountService = null;
    private final CustomerService customerService = null;
    private final TransactionService transactionService = null;


    @

            PostMapping("bank/management")
    public ResponseEntity<? super AccountRespDto> createAccount(@RequestBody(required = false) AccountReqDto accountReqDto) {
        Optional<CustomerRespDto> customerById = customerService.getById(accountReqDto.getCustomerId());
        if (customerById.get().getAccount() == null) {
            Optional<AccountRespDto> accountRespDto = accountService.create(accountReqDto);
            return ResponseEntity.status(200).body(accountRespDto);
        } else if (customerById.get().getAccount() != null && accountReqDto.getInitialCredit() == 0) {
            Optional<AccountRespDto> byId = accountService.getById(customerById.get().getAccount().getId());
            return
        }

    }


}
