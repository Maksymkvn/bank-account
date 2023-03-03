package com.bank.account.controller;

import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.mapper.domen.dto.AccountRespDtoForBank;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/connect")
    public ResponseEntity<? super AccountRespDtoForBank> connectAccount(@RequestBody(required = false) AccountReqDto accountReqDto) {
        AccountRespDtoForBank accountRespDto = accountService.create(accountReqDto);
//        if (!accountRespDto.isPresent()) {
//            return new ResponseEntity<>("Invalid request", HttpStatus.NOT_FOUND);
//        }
        return ResponseEntity.status(201).body(accountRespDto);
    }

    @GetMapping("management/account/{id}")
    public ResponseEntity<? super AccountRespDto> findCustomerById(@PathVariable("id") Long id) {
        Optional<AccountRespDto> accountById = accountService.getById(id);
        if (!accountById.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(accountById);
    }
}
