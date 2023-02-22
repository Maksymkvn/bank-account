package com.bank.account.controller;

import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForBank;
import com.bank.account.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("management/customer")
    public ResponseEntity<? super List<CustomerRespDto>> findAllCustomer() {
        List<CustomerRespDto> respDtoList = customerService.getAll();
        if (respDtoList.isEmpty()) {
            return new ResponseEntity<>("Customers not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(respDtoList);
    }

    @GetMapping("management/customer/{id}")
    public ResponseEntity<? super CustomerRespDto> findCustomerById(@PathVariable("id") Long id) {
        Optional<CustomerRespDto> customerById = customerService.getById(id);
        if (customerById.isPresent()) {
            return ResponseEntity.status(200).body(customerById);
        }
        return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
    }

    @PostMapping("management/customer")
    public ResponseEntity<? super CustomerRespDto> createCustomer(@RequestBody(required = false)CustomerReqDto customerReqDto){
        Optional<CustomerRespDto> createCustomerRespDto = customerService.create(customerReqDto);
        if (createCustomerRespDto.isPresent()) {
            return ResponseEntity.status(201).body(createCustomerRespDto);
        }
        return new ResponseEntity<>("Invalid request. Customer not create", HttpStatus.NOT_FOUND);
    }

    @PutMapping("management/customer/{id}")
    public ResponseEntity<? super CustomerRespDto> update(@PathVariable("id") Long id,
                                                      @RequestBody(required = false) CustomerReqDto customerReqDto) {
        Optional<CustomerRespDto> updateCustomerRespDto = customerService.update(id, customerReqDto);
        if (updateCustomerRespDto.isPresent()) {
            return ResponseEntity.status(200).body(updateCustomerRespDto);
        }
        return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("management/customer/{id}")
    public ResponseEntity<? super CustomerRespDto> delete(@PathVariable("id") Long id) {
        Optional<CustomerRespDto> deleteCustomerRespDto = customerService.delete(id);
        if (deleteCustomerRespDto.isPresent()) {
            return ResponseEntity.status(200).body(deleteCustomerRespDto);
        }
        return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
    }
    @GetMapping("management/customer/bank")
    public ResponseEntity<? super List<CustomerRespDtoForBank>> findAllCustomerForBank() {
        List<CustomerRespDtoForBank> respDtoList = customerService.getAllBank();
        if (respDtoList.isEmpty()) {
            return new ResponseEntity<>("not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(respDtoList);
    }

    @GetMapping("management/customer/bank/{id}")
    public ResponseEntity<? super CustomerRespDtoForBank> findCustomerByIdForBank(@PathVariable("id") Long id) {
        Optional<CustomerRespDtoForBank> customerById = customerService.getByIdBank(id);
        if (customerById.isPresent()) {
            return ResponseEntity.status(200).body(customerById);
        }
        return new ResponseEntity<>("not found ", HttpStatus.NOT_FOUND);
    }

}
