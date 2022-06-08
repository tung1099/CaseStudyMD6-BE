package com.codegym.castudymd6final.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String amount;

    private String note;

    private Date date;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Wallet wallet;

    public Transaction() {
    }

    public Transaction(String amount, String note, Date date, Category category, Wallet wallet) {
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.category = category;
        this.wallet = wallet;
    }

    public Transaction(Long id, String amount, String note, Date date, Category category, Wallet wallet) {
        this.id = id;
        this.amount = amount;
        this.note = note;
        this.date = date;
        this.category = category;
        this.wallet = wallet;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
