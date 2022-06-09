package com.codegym.castudymd6final.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "wallets")
@Data
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String icon;

    private String name;

    private int total;

    private int balance;

    private Date date;

    private String note;

    @ManyToOne
    private MoneyType moneyType;

    @ManyToOne
    private User user;

    public Wallet() {
    }

    public Wallet(Long id, String icon, String name, int total, Date date, String note, MoneyType moneyType, User user) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.date = date;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }

    public Wallet(String icon, String name, int total, Date date, String note, MoneyType moneyType, User user) {
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.date = date;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }

    public Wallet(Long id, String icon, String name, int total, int balance, Date date, String note, MoneyType moneyType, User user) {
        this.id = id;
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.balance = balance;
        this.date = date;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }

    public Wallet(String icon, String name, int total, MoneyType moneyType, int balance, Date date, String note, User user) {
        this.icon = icon;
        this.name = name;
        this.total = total;
        this.balance = balance;
        this.date = date;
        this.note = note;
        this.moneyType = moneyType;
        this.user = user;
    }

}
