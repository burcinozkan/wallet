package com.burcin.wallet.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.burcin.wallet.enums.TransactionStatus;
import com.burcin.wallet.enums.TransactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transactions")
@Getter @Setter
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_account_id")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver_account_id")
    private Account receiver;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status; // PENDING, SUCCESS, FAILED

    @Enumerated(EnumType.STRING)
    private TransactionType type; // TRANSFER, TOP_UP

    private LocalDateTime createdAt = LocalDateTime.now();
}
