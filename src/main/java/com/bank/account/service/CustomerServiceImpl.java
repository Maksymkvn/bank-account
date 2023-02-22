package com.bank.account.service;

import com.bank.account.mapper.AccountMapper;
import com.bank.account.mapper.CustomerMapper;
import com.bank.account.mapper.domen.dto.*;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final CustomerMapper customerMapper;
    private final AccountMapper accountMapper;

    @Override
    public List<CustomerRespDto> getAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(customerMapper::customerToRespDto)
                .toList();
    }

    @Override
    public Optional<CustomerRespDto> getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToRespDto);
    }

    @Override
    public Optional<CustomerRespDto> create(CustomerReqDto customerReqDto) {
        return customerMapper.customerReqDtoToCustomer(customerReqDto)
                .map(customerRepository::save)
                .map(customerMapper::customerToRespDto);
    }

    @Override
    @Transactional
    public Optional<CustomerRespDto> update(Long id, CustomerReqDto customerReqDto) {
        return customerRepository.findById(id)
                .map(c -> {
                    if (customerReqDto != null) {
                        c.setFirstName(customerReqDto.getFirstName());
                        c.setLastName(customerReqDto.getLastName());
                        c.setDateOfBirth(customerReqDto.getDateOfBirth());
                        c.setActive(customerReqDto.isActive());
                        return c;
                    }
                    return null;
                })
                .map(customerMapper::customerToRespDto);
    }

    @Override
    public Optional<CustomerRespDto> delete(Long id) {
        Optional<CustomerRespDto> customerRespDto = customerRepository.findById(id)
                .map(customerMapper::customerToRespDto);
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        return customerRespDto;
    }

    public Optional<CustomerRespDto> connectAccount(Long id, AccountReqDto accountReqDto) {
        return customerRepository.findById(id)
                .map(c -> {
                    c.setAccount(accountMapper.accountReqDtoToAccount(accountReqDto).get());
                    return c;
                })
                .map(customerMapper::customerToRespDto);
    }

    @Override
    public List<CustomerRespDtoForBank> getAllBank() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(customerMapper::customerToRespDtoForBank)
                .toList();
    }

    @Override
    public Optional<CustomerRespDtoForBank> getByIdBank(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToRespDtoForBank);
    }

}
