package com.burcin.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.burcin.wallet.dto.TransferRequest;
import com.burcin.wallet.entity.Transactions;
import com.burcin.wallet.repository.TransactionRepository;
import com.burcin.wallet.service.WalletService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {

    @Autowired
    private TransactionRepository transactionRepository;
    private final WalletService walletService;

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@Valid @RequestBody TransferRequest request){
        walletService.transferMoney(
            request.getSenderUserId(),
            request.getReceiverUserId(),
            request.getAmount()
        );

        return ResponseEntity.ok("Transfer successful");
    }
    @GetMapping("/history/{userId}")
    public ResponseEntity<List<Transactions>> getHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(transactionRepository.findBySenderIdOrReceiverIdOrderByCreatedAtDesc(userId, userId));
    }
}
