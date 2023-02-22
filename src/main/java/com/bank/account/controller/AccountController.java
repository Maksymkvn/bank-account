package com.bank.account.controller;

import com.bank.account.mapper.domen.dto.AccountReqDto;
import com.bank.account.mapper.domen.dto.AccountRespDto;
import com.bank.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/connect")
    public ResponseEntity<? super AccountRespDto> connectAccount(@RequestBody(required = false)AccountReqDto accountReqDto){
        Optional<AccountRespDto> accountRespDto = accountService.create(accountReqDto);
 //       if (accountRespDto.isPresent()) {
            return ResponseEntity.status(201).body(accountRespDto);
//        }
//        return new ResponseEntity<>("Invalid request", HttpStatus.NOT_FOUND);
    }
}
