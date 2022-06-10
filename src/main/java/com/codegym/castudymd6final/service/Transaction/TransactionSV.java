package com.codegym.castudymd6final.service.Transaction;

import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import com.codegym.castudymd6final.repository.ITransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionSV implements ITransactionSV{
    @Autowired
    private ITransactionRepo transactionRepo;
    @Override
    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    @Override
    public Transaction save(Transaction transaction) {
        return transactionRepo.save(transaction);
    }

    @Override
    public void removeById(Long id) {
        transactionRepo.deleteById(id);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return transactionRepo.findById(id);
    }

    @Override
    public Iterable<TransactionInDay> getTransactionInDay() {
        return transactionRepo.getTransactionInDay();
    }

    @Override
    public Iterable<TransactionInDay> getTransactionInDayByIdWallet(Long id) {
        return transactionRepo.getTransactionInDayByIdWallet(id);
    }

    @Override
    public Iterable<SumInDay> getSumInDay(Long id) {
        return transactionRepo.getSumInDay(id);
    }

    @Override
    public Iterable<AllTransactionWallet> getAllTransactionByIdWallet(Long id) {
        return transactionRepo.getAllTransactionByIdWallet(id);
    }


}
