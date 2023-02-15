package com.bank.account.service;

import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.apache.el.stream.Stream;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public List<CustomerRespDto> getAll() {

        return null;
    }

    @Override
    public Optional<CustomerRespDto> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerRespDto> create(CustomerReqDto customerReqDto) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerRespDto> update(String id, CustomerReqDto customerReqDto) {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerRespDto> delete(String id) {
        return Optional.empty();
    }
}
