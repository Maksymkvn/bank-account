package com.bank.account.service;

import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.TransactionReqDto;
import com.bank.account.mapper.domen.dto.TransactionRespDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TransactionService {
    Optional<TransactionRespDto> getById(Long id);
    List<TransactionRespDto> getAll();

    Optional<TransactionRespDto> create(TransactionReqDto transactionalReqDto);
}
