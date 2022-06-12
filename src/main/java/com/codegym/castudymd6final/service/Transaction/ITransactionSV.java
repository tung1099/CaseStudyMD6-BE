package com.codegym.castudymd6final.service.Transaction;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.entity.UserInfo;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionUser;
import com.codegym.castudymd6final.service.IGeneralService;

import java.util.List;

public interface ITransactionSV extends IGeneralService<Transaction> {

    Iterable<TransactionInDay> getTransactionInDay( Long id);

    Iterable<TransactionInDay> getTransactionInDayByIdWallet(Long id);

    Iterable<SumInDay> getSumInDay(Long id);

    Iterable<AllTransactionWallet> getAllTransactionByIdWallet(Long id);

    List<Transaction> getListTransactionUser(Long id);


}
