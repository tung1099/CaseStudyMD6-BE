package com.codegym.castudymd6final.repository;

import com.codegym.castudymd6final.model.entity.AddMoney;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddMoneyRepo extends JpaRepository<AddMoney, Long> {
}
