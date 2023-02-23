package com.bank.account.mapper.domen;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Table(name = "account")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "customer_id",insertable=false, updatable=false)
    private Long customerId;
    @Column(name = "balance")
    private BigDecimal balance;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonIgnore
    @OneToMany(mappedBy = "account", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Transaction> transactions;
}
