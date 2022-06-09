package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IWalletRepo extends JpaRepository<Wallet, Long> {
    @Query(nativeQuery = true, value = "select* from wallets where user_id = ?;")
    List<Wallet> getWalletByUserId(Long userId);
}
