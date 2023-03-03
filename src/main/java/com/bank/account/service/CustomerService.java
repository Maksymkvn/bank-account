package com.bank.account.service;

import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForBank;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForCustController;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CustomerService {
    List<CustomerRespDtoForCustController> getAll();

    Optional<CustomerRespDtoForCustController> getById(Long id);

    Optional<CustomerRespDtoForCustController> create(CustomerReqDto customerReqDto);

    Optional<CustomerRespDtoForCustController> update(Long id, CustomerReqDto customerReqDto);

    Optional<CustomerRespDtoForCustController> delete(Long id);
    List<CustomerRespDtoForBank> getAllBank();
    Optional<CustomerRespDtoForBank> getByIdBank(Long id);
    Optional<CustomerRespDto> getByIdForAccount(Long id);
}
