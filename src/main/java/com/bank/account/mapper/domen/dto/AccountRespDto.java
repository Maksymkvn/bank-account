package com.bank.account.mapper.domen.dto;

import com.bank.account.mapper.domen.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
public class AccountRespDto {
    private Long id;
    private Long customerId;
    private BigDecimal balance;
    private List<TransactionRespDto> transactions;
}
