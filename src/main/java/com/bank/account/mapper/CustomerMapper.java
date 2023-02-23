package com.bank.account.mapper;


import com.bank.account.mapper.domen.Customer;
import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@RequiredArgsConstructor
public class CustomerMapper {

    public CustomerRespDto customerToCustomerRespDto(Customer customer) {
        return Optional.ofNullable(customer)
                .map(c -> CustomerRespDto.builder()
                        .id(c.getId())
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .build())
                .orElse(null);
    }

    public Optional<Customer> customerReqDtoToCustomer(CustomerReqDto customerReqDto) {
        return Optional.ofNullable(customerReqDto)
                .map(c->Customer.builder()
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .build());
    }
}