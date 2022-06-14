package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.AddMoney;
import com.codegym.castudymd6final.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAddMoneyRepo extends JpaRepository<AddMoney, Long> {
    @Query(nativeQuery = true, value = "select * from add_money where wallet_id = ?;")
    List<AddMoney> getAddMoneyByWallet(Long idWallet);
}
