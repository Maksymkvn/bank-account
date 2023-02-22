package com.bank.account.mapper;

import com.bank.account.mapper.domen.Customer;
import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForBank;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final TransactionMapper transactionMapper;

    public CustomerRespDto customerToRespDto(Customer customer) {
        return Optional.ofNullable(customer)
                .stream()
                .findAny()
                .map(c -> CustomerRespDto.builder()
                        .id(c.getId())
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .account(c.getAccount())
                        .build())
                .orElse(null);
    }

    public Optional<Customer> customerReqDtoToCustomer(CustomerReqDto customerReqDto) {
        return Optional.ofNullable(customerReqDto)
                .stream()
                .findAny()
                .map(c -> Customer.builder()
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .build());
    }

    public CustomerRespDtoForBank customerToRespDtoForBank(Customer customer) {
        return Optional.ofNullable(customer)
                .stream()
                .findAny()
                .map(c -> CustomerRespDtoForBank.builder()
                        .id(c.getId())
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .accountId(customer.getAccount().getId())
                        .transactions(transactionMapper.transactionalToTransactionalRespDtoForCustomer(c.getAccount().getTransactions()))
                        .build())
                .orElse(null);


    }
}