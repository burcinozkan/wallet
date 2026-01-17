package com.burcin.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.burcin.wallet.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    List<Transactions> findBySenderIdOrReceiverIdOrderByCreatedAtDesc(Long senderId, Long receiverId);
    
}
