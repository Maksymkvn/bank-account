package com.bank.account.service;

import com.bank.account.mapper.AccountMapper;
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
    private final AccountMapper accountMapper;

    @Override
    public List<CustomerRespDtoForCustController> getAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .toList().stream()
                .map(customerMapper::customerToCustomerRespCustomerController)
                .toList();
    }

    @Override
    public Optional<CustomerRespDtoForCustController> getById(Long id) {
        return customerRepository.findById(id)
                .map(customerMapper::customerToCustomerRespCustomerController);
    }

    @Override
    public Optional<CustomerRespDtoForCustController> create(CustomerReqDto customerReqDto) {
        return customerMapper.customerReqDtoToCustomer(customerReqDto)
                .map(customerRepository::save)
                .map(customerMapper::customerToCustomerRespCustomerController);
    }

    @Override
    @Transactional
    public Optional<CustomerRespDtoForCustController> update(Long id, CustomerReqDto customerReqDto) {
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
                .map(customerMapper::customerToCustomerRespCustomerController);
    }

    @Override
    public Optional<CustomerRespDtoForCustController> delete(Long id) {
        Optional<CustomerRespDtoForCustController> customerRespDto = customerRepository.findById(id)
                .map(customerMapper::customerToCustomerRespCustomerController);
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

    public Optional<CustomerRespDto> getByIdForAccount(Long id){
        return customerRepository.findById(id)
                .map(customerMapper::customerToRespDto);
    }

}
