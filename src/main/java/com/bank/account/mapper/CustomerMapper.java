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

}
