package com.bank.account.service;

import com.bank.account.mapper.CustomerMapper;
import com.bank.account.mapper.domen.dto.*;
import com.bank.account.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .map(customerMapper::customerToCustomerRespDto)
                .toList();
    }

    @Override
    public Optional<CustomerRespDto> getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerRespDto);
    }

    @Override
    public Optional<CustomerRespDto> create(CustomerReqDto customerReqDto) {
        return customerMapper.customerReqDtoToCustomer(customerReqDto)
                .map(customerRepository::save)
                .map(customerMapper::customerToCustomerRespDto);
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
                .map(customerMapper::customerToCustomerRespDto);
    }

    @Override
    public Optional<CustomerRespDto> delete(Long id) {
        Optional<CustomerRespDto> customerRespDto = customerRepository.findById(id)
                .map(customerMapper::customerToCustomerRespDto);
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        }
        return customerRespDto;
    }

//    @Override
//    public List<CustomerRespDtoForBank> getAllBank() {
//        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
//                .toList().stream()
//                .map(customerMapper::customerToRespDtoForBank)
//                .toList();
//    }
//
//    @Override
//    public Optional<CustomerRespDtoForBank> getByIdBank(Long id) {
//        return customerRepository.findById(id)
//                .map(customerMapper::customerToRespDtoForBank);
//    }
//
//    @Override
//    public Optional<CustomerRespDtoForCustomer> getByIdCustomer(Long id) {
//        return customerRepository.findById(id)
//                .map(customerMapper::customerRespDtoForCustomer);
//    }
}
