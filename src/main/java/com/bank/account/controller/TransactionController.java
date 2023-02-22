package com.bank.account.controller;

import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.TransactionRespDto;
import com.bank.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("management/transaction")
    public ResponseEntity<? super List<TransactionRespDto>>findAll() {
        List<TransactionRespDto> respDtoList = transactionService.getAll();
        if (respDtoList.isEmpty()) {
            return new ResponseEntity<>("Customers not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(respDtoList);
    }
}
