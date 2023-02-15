package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.dto.AccountRespDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class CustomerRespDtoAccountInfo {
    String id;
    String firstName;
    String lastName;
    LocalDateTime dateOfBirth;
    boolean active;
    AccountRespDto account;
}
