package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.entity.UserInfo;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionUser;
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

    @Query(nativeQuery = true, value = "select sum(amount) as total from transactions join wallets w on w.id = transactions.wallet_id where transactions.date = current_date and w.id = ?;\n")
    Iterable<SumInDay> getSumInDay(Long id);

    @Query(nativeQuery = true, value ="select * from transactions where wallet_id = ?;")
    Iterable<Transaction> getAllTransactionByIdWallet(Long id);

    @Query( nativeQuery = true, value = "select * from transactions where user_id = ?1" )
    List<Transaction> getListTransactionUser(Long id);

    @Query(value = "select money_type_id from wallets where id = ?1", nativeQuery = true)
    Long findMoney(Long id);

}
