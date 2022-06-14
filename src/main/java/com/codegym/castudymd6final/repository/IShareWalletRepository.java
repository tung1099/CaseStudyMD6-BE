package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.ShareWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IShareWalletRepository extends JpaRepository<ShareWallet, Long> {
    @Query(value = "select wallet_id from share_wallet where user_id = ?1", nativeQuery = true)
    List<Long> findListWalletShare(Long id);

}
