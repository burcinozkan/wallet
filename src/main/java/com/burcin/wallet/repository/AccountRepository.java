package com.burcin.wallet.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.burcin.wallet.entity.Account;

public interface  AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(Long userId);
    
}
