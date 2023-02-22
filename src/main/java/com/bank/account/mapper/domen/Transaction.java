package com.bank.account.mapper.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

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
    @Column(name = "amount")
    private Double amount;
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
 //   @JoinColumn(name = "account_id")
    private Account account;
}
