package com.bank.account.mapper.domen;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Table(name = "transaction")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "account_id")
    private Long accountId;
    @Column(name = "amount")
    private BigDecimal amount;
    @Column(name = "operation_type")
    private String operationType;
    @Column(name = "source_account")
    private String sourceAccount;
    @Column(name = "transaction_status")
    private String transactionStatus;
    @Column(name = "transaction_time")
    private LocalDateTime transactionTime;
    @ManyToOne
    @JoinColumn(name = "account_id", insertable = false, updatable = false)
    private Account account;
}
