package com.burcin.wallet.controller;

import com.burcin.wallet.entity.User;
import com.burcin.wallet.entity.Account; // Account entity'ni import et
import com.burcin.wallet.repository.UserRepository;
import com.burcin.wallet.repository.AccountRepository; // AccountRepository'i import et
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional; // Önemli!
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    @Transactional // Hem user hem account aynı anda kaydedilsin, biri hata verirse geri alınsın
    public User createUser(@RequestBody User user) {
        // 1. Önce kullanıcıyı kaydet
        User savedUser = userRepository.save(user);

        // 2. Bu kullanıcı için otomatik boş bir hesap oluştur
        Account account = new Account();
        account.setUser(savedUser);
        account.setBalance(BigDecimal.ZERO); // Yeni kullanıcı 0 TL ile başlasın
        account.setCurrency("TRY");
        
        // 3. Hesabı kaydet
        accountRepository.save(account);

        return savedUser;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}