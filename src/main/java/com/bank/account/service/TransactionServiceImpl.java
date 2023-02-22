package com.bank.account.service;

import com.bank.account.mapper.TransactionMapper;
import com.bank.account.mapper.domen.Transaction;
import com.bank.account.mapper.domen.dto.TransactionReqDto;
import com.bank.account.mapper.domen.dto.TransactionRespDto;
import com.bank.account.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;

    public Optional<TransactionRespDto> getById(Long id) {
        return transactionRepository.findById(id)
                .map(transactionMapper::transactionalToTransactionalRespDto);
    }

    public List<TransactionRespDto> getAll() {
        return StreamSupport.stream(transactionRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(transactionMapper::transactionalToTransactionalRespDto)
                .toList();
    }

    @Transactional
    public Optional<TransactionRespDto> create(TransactionReqDto transactionalReqDto) {
        return transactionMapper.transactionReqDtoToTransaction(transactionalReqDto)
                .map(transactionRepository::save)
                .map(transactionMapper::transactionalToTransactionalRespDto);
    }
}
