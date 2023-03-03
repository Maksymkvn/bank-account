package com.bank.account.controller;

import com.bank.account.mapper.domen.dto.CustomerReqDto;
import com.bank.account.mapper.domen.dto.CustomerRespDto;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForBank;
import com.bank.account.mapper.domen.dto.CustomerRespDtoForCustController;
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
    public ResponseEntity<? super List<CustomerRespDtoForCustController>> findAllCustomer() {
        List<CustomerRespDtoForCustController> respDtoList = customerService.getAll();
        if (!respDtoList.isEmpty()) {
            return ResponseEntity.status(200).body(respDtoList);
        }
        return new ResponseEntity<>("Customers not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("management/customer/{id}")
    public ResponseEntity<? super CustomerRespDtoForCustController> findCustomerById(@PathVariable("id") Long id) {
        Optional<CustomerRespDtoForCustController> customerById = customerService.getById(id);
        if (!customerById.isPresent()) {
            return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(customerById);
    }

    @PostMapping("management/customer")
    public ResponseEntity<? super CustomerRespDtoForCustController> createCustomer(@RequestBody(required = false)CustomerReqDto customerReqDto){
        Optional<CustomerRespDtoForCustController> createCustomerRespDto = customerService.create(customerReqDto);
        if (!createCustomerRespDto.isPresent()) {
            return new ResponseEntity<>("Invalid request. Customer not create", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(201).body(createCustomerRespDto);
    }

    @PutMapping("management/customer/{id}")
    public ResponseEntity<? super CustomerRespDtoForCustController> update(@PathVariable("id") Long id,
                                                      @RequestBody(required = false) CustomerReqDto customerReqDto) {
        Optional<CustomerRespDtoForCustController> updateCustomerRespDto = customerService.update(id, customerReqDto);
        if (!updateCustomerRespDto.isPresent()) {
            return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(updateCustomerRespDto);
    }

    @DeleteMapping("management/customer/{id}")
    public ResponseEntity<? super CustomerRespDtoForCustController> delete(@PathVariable("id") Long id) {
        Optional<CustomerRespDtoForCustController> deleteCustomerRespDto = customerService.delete(id);
        if (!deleteCustomerRespDto.isPresent()) {
            return new ResponseEntity<>("Customer not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(deleteCustomerRespDto);
    }
    @GetMapping("management/customer/bank")
    public ResponseEntity<? super List<CustomerRespDtoForBank>> findAllCustomerForBank() {
        List<CustomerRespDtoForBank> respDtoList = customerService.getAllBank();
        if (!respDtoList.isEmpty()) {
            return ResponseEntity.status(200).body(respDtoList);
        }
        return new ResponseEntity<>("not found ", HttpStatus.NOT_FOUND);
    }

    @GetMapping("management/customer/bank/{id}")
    public ResponseEntity<? super CustomerRespDtoForBank> findCustomerByIdForBank(@PathVariable("id") Long id) {
        Optional<CustomerRespDtoForBank> customerById = customerService.getByIdBank(id);
        if (!customerById.isPresent()) {
            return new ResponseEntity<>("not found ", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.status(200).body(customerById);
    }

}
