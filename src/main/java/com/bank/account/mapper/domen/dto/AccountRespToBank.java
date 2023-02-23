package com.bank.account.mapper.domen.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class AccountRespToBank {
    private Long accountId;
    private BigDecimal amount;
}
