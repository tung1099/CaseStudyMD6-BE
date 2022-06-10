package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {

    @Query(nativeQuery = true, value = "select transactions.id as id, categories.name as category, transactions.amount as amount, w.name as wallet, transactions.note as note\n" +
            "            from transactions join categories on transactions.category_id = categories.id\n" +
            "              join wallets w on transactions.wallet_id = w.id\n" +
            "            where transactions.date = current_date;")
    Iterable<TransactionInDay> getTransactionInDay();

    @Query(nativeQuery = true, value ="select transactions.id as id, categories.name as category, transactions.amount as amount, w.name as wallet, transactions.note as note\n" +
            "                      from transactions join categories on transactions.category_id = categories.id\n" +
            "                         join wallets w on transactions.wallet_id = w.id\n" +
            "                       where transactions.date = current_date and w.id = ?;")
    Iterable<TransactionInDay> getTransactionInDayByIdWallet(Long id);

    @Query(nativeQuery = true, value = "select sum(amount) as total from transactions join wallets w on w.id = transactions.wallet_id where transactions.date = current_date and w.id = ?;\n")
    Iterable<SumInDay> getSumInDay(Long id);

    @Query(nativeQuery = true, value ="select transactions.id as id, categories.name as category, transactions.amount as amount, w.name as wallet, transactions.note as note\n" +
            "            , transactions.date as date from transactions join categories on transactions.category_id = categories.id\n" +
            "                                  join wallets w on transactions.wallet_id = w.id\n" +
            "                                  where w.id = ?;")
    Iterable<AllTransactionWallet> getAllTransactionByIdWallet(Long id);
}
