package com.codegym.castudymd6final.model.entity;

import lombok.Data;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AddMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int money;

    private Date date;

    @ManyToOne
    private Wallet wallet;

    public AddMoney() {
    }

    public AddMoney(Long id, int money, Date date, Wallet wallet) {
        this.id = id;
        this.money = money;
        this.date = date;
        this.wallet = wallet;
    }

    public AddMoney(int money, Date date, Wallet wallet) {
        this.money = money;
        this.date = date;
        this.wallet = wallet;
    }
}
