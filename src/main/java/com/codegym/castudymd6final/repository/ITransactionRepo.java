package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITransactionRepo extends JpaRepository<Transaction, Long> {
}
