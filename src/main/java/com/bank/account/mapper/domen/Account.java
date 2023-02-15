package com.bank.account.mapper.domen;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    String id;
    String customerId;
    Double balance;
    List<Transaction> transaction;
}
