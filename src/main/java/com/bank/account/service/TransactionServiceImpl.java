package com.bank.account.service;

import com.bank.account.mapper.TransactionMapper;
import com.bank.account.mapper.domen.dto.TransactionReqDto;
import com.bank.account.mapper.domen.dto.TransactionRespDto;
import com.bank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    @Transactional
    public Optional<TransactionRespDto> create(TransactionReqDto transactionalReqDto) {
        return transactionMapper.transactionReqDtoToTransaction(transactionalReqDto)
                .map(transactionRepository::save)
                .map(transactionMapper::transactionalToTransactionalRespDto);
    }
}
