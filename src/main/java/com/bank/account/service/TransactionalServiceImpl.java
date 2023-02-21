package com.bank.account.service;

import com.bank.account.mapper.TransactionalMapper;
import com.bank.account.mapper.domen.dto.TransactionalReqDto;
import com.bank.account.mapper.domen.dto.TransactionalRespDto;
import com.bank.account.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TransactionalServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionalMapper transactionalMapper;

    Optional<TransactionalRespDto> getById(Long id){
        return transactionRepository.findById(id)
                .map(transactionalMapper::transactionalToTransactionalRespDto);
    }

    Optional<TransactionalRespDto> create(TransactionalReqDto transactionalReqDto){
        return transactionalMapper.transactionReqDtoToTransaction(transactionalReqDto)
                .map(transactionRepository::save)
                .map(transactionalMapper::transactionalToTransactionalRespDto);
    }

//    Optional<TransactionalRespDto> update(Long id, TransactionalReqDto transactionalReqDto){
//
//    }

}
