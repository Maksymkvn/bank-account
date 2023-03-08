package com.bank.account.mapper;

import com.bank.account.mapper.domen.Customer;
import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForBank;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForCustController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CustomerMapper {
    private final TransactionMapper transactionMapper;
    private final AccountMapperForCustomer accountMapperForCustomer;

    public CustomerRespDto customerToRespDto(Customer customer) {
        return Optional.ofNullable(customer)
                .map(c -> CustomerRespDto.builder()
                        .id(c.getId())
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .accountRespDto(accountMapperForCustomer.customerToRespDto(c.getAccount()))
                        .build())
                .orElse(null);
    }


    public Optional<Customer> customerReqDtoToCustomer(CustomerReqDto customerReqDto) {
        return Optional.ofNullable(customerReqDto)
                .map(c -> Customer.builder()
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .build());
    }

    public CustomerRespDtoForCustController customerToCustomerRespCustomerController(Customer customer) {
        return Optional.ofNullable(customer)
                .map(c -> CustomerRespDtoForCustController.builder()
                        .id(c.getId())
                        .firstName(c.getFirstName())
                        .lastName(c.getLastName())
                        .dateOfBirth(c.getDateOfBirth())
                        .active(c.isActive())
                        .build()
                ).orElse(null);
    }

    public CustomerRespDtoForBank customerToRespDtoForBank(Customer customer) {
        if (customer.getAccount() != null) {
            return Optional.ofNullable(customer)
                    .map(c -> CustomerRespDtoForBank.builder()
                            .id(c.getId())
                            .firstName(c.getFirstName())
                            .lastName(c.getLastName())
                            .dateOfBirth(c.getDateOfBirth())
                            .active(c.isActive())
                            .accountId(c.getAccount().getId())
                            .balance(c.getAccount().getBalance())
                            .transactions(transactionMapper.transactionalToTransactionalRespDtoForCustomer(c.getAccount().getTransactions()))
                            .build())
                    .orElse(null);
        } else {
            return Optional.ofNullable(customer)
                    .map(c -> CustomerRespDtoForBank.builder()
                            .id(c.getId())
                            .firstName(c.getFirstName())
                            .lastName(c.getLastName())
                            .dateOfBirth(c.getDateOfBirth())
                            .active(c.isActive())
                            .build())
                    .orElse(null);
        }
    }
}