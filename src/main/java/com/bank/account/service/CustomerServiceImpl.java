package com.bank.account.service;

import com.bank.account.mapper.CustomerMapper;
import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public List<CustomerRespDto> getAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(customerMapper::customerToRespDto)
                .toList();
    }

    @Override
    public Optional<CustomerRespDto> getById(String id) {
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
    public Optional<CustomerRespDto> update(String id, CustomerReqDto customerReqDto) {
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
    public Optional<CustomerRespDto> delete(String id) {
        Optional<CustomerRespDto> customerRespDto = customerRepository.findById(id)
                .map(customerMapper::customerToRespDto);
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        return customerRespDto;
    }
}
