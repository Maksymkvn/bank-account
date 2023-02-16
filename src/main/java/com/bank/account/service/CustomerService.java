package com.bank.account.service;

import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    List<CustomerRespDto> getAll();

    Optional<CustomerRespDto> getById(Long id);

    Optional<CustomerRespDto> create(CustomerReqDto customerReqDto);

    Optional<CustomerRespDto> update(Long id, CustomerReqDto customerReqDto);

    Optional<CustomerRespDto> delete(Long id);
}
