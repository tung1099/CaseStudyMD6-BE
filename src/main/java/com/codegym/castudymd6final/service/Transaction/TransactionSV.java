package com.codegym.castudymd6final.service.Transaction;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TransactionSV implements ITransactionSV{
    @Override
    public List findAll() {
        return null;
    }

    @Override
    public Object save(Object o) {
        return null;
    }

    @Override
    public void removeById(Long id) {

    }

    @Override
    public Optional findById(Long id) {
        return Optional.empty();
    }
}
