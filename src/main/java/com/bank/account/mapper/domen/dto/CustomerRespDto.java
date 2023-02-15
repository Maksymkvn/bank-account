package com.bank.account.mapper.domen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class CustomerRespDto {
    String id;
    String firstName;
    String lastName;
    LocalDateTime dateOfBirth;
    boolean active;
}
