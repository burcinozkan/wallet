package com.burcin.wallet.service;

import java.math.BigDecimal;


import com.burcin.wallet.entity.Transactions;

import org.springframework.stereotype.Service;
import com.burcin.wallet.entity.Account;
import com.burcin.wallet.enums.TransactionStatus;
import com.burcin.wallet.enums.TransactionType;
import com.burcin.wallet.exception.WalletException;
import com.burcin.wallet.repository.AccountRepository;
import com.burcin.wallet.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public void transferMoney(Long senderId, Long receiverId, BigDecimal amount) {
        // find sender and check balance
        Account senderAccount = accountRepository.findById(senderId)
        .orElseThrow(() -> new RuntimeException("Sender account not found"));

        if (senderAccount.getBalance().compareTo(amount) < 0){
            throw new WalletException("Insufficient balance" + senderAccount.getBalance());
        }

        // find receiver

        Account receiverAccount = accountRepository.findById(receiverId)
        .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        // deduct from sender
        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        accountRepository.save(senderAccount);

        // add to receiver
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));
        accountRepository.save(receiverAccount);

        // log transaction
        Transactions transaction = new Transactions();
        transaction.setSender(senderAccount);
        transaction.setReceiver(receiverAccount);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.TRANSFER);
        transaction.setStatus(TransactionStatus.SUCCESS);

        transactionRepository.save(transaction);
    }
    
}
