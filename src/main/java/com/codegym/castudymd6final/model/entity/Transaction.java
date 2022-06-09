package com.codegym.castudymd6final.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    private String note;

    private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Wallet wallet;

    public Transaction() {
    }

    public Transaction(int amount, String note, Date date, Category category, Wallet wallet) {
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.category = category;
        this.wallet = wallet;
    }

    public Transaction(Long id, int amount, String note, Date date, Category category, Wallet wallet) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.category = category;
        this.wallet = wallet;
    }
}
