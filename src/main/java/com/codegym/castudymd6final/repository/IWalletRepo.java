package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Repository
public interface IWalletRepo extends JpaRepository<Wallet, Long> {
    @Query(nativeQuery = true, value = "select* from wallets where user_id = ?;")
    List<Wallet> getWalletByUserId(Long userId);

    @Modifying
    @Query(value = "call deleteWallet(?1)", nativeQuery = true)
    void deleteWallet(Long wallet_id);
}
