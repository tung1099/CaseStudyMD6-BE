package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.entity.UserInfo;
import com.codegym.castudymd6final.model.transactionInDay.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "select * from transactions where date = current_date and user_id = ?;")
    Iterable<Transaction> getTransactionInDay(Long idUser);

    @Query(nativeQuery = true, value ="select * from transactions where date = current_date and wallet_id = ?;")
    Iterable<Transaction> getTransactionInDayByIdWallet(Long id);

    @Query(nativeQuery = true, value = "select sum(amount) as total, mt.name as type from transactions join wallets w on w.id = transactions.wallet_id join money_type mt on mt.id = w.money_type_id where transactions.date = current_date and w.id = ?;\n")
    Iterable<SumInDay> getSumInDay(Long id);

    @Query(nativeQuery = true, value = "select sum(amount) as total, mt.name as type from transactions join wallets w on w.id = transactions.wallet_id join money_type mt on mt.id = w.money_type_id where w.id = ?;\n")
    Iterable<SumInDay> getSumTransactionWallet(Long id);

    @Query(nativeQuery = true, value ="select * from transactions where wallet_id = ?;")
    Iterable<Transaction> getAllTransactionByIdWallet(Long id);

    @Query( nativeQuery = true, value = "select * from transactions where user_id = ?1" )
    List<Transaction> getListTransactionUser(Long id);

    @Query(value = "select money_type_id from wallets where id = ?1", nativeQuery = true)
    Long findMoney(Long id);

    @Query(nativeQuery = true, value = "select sum(money) as money from add_money where month(current_time)  and wallet_id = ?;")
    Sum getSumMoney(Long id);

}
