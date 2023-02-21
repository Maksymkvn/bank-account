package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.Customer;
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
