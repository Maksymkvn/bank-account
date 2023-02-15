package com.bank.account.mapper.domen;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Builder
public class Account {
    String id;
    String customerId;
    Double balance;
    List<Transaction> transaction;
}
