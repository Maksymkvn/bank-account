package com.bank.account.mapper.domen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AccountRespDtoForBank {
    private Long accountId;
    private Double amount;
}
