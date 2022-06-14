package com.codegym.castudymd6final.service.Transaction;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.entity.UserInfo;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionUser;
import com.codegym.castudymd6final.service.IGeneralService;

import java.util.Date;
import java.util.List;

public interface ITransactionSV extends IGeneralService<Transaction> {

    Iterable<Transaction> getTransactionInDay( Long id);

    Iterable<Transaction> getTransactionInDayByIdWallet(Long id);

    Iterable<SumInDay> getSumInDay(Long id);

    Iterable<Transaction> getAllTransactionByIdWallet(Long id);

    List<Transaction> getListTransactionUser(Long id);

    Long findMoney(Long id);

    Iterable<Transaction> getListTransactionInTime(Date date1, Date date2, Long id);

    Iterable<Transaction> getListTransactionInTimeByIdWallet(Date date1, Date date2, Long idWallet);}
