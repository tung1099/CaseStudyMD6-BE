package com.codegym.castudymd6final.model.entity;



import javax.persistence.*;

@Entity
public class AddMoney {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    private Wallet wallet;

    public AddMoney() {
    }

    public AddMoney(String name, String description, Wallet wallet) {
        this.name = name;
        this.description = description;
        this.wallet = wallet;
    }

    public AddMoney(Long id, String name, String description, Wallet wallet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
