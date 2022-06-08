package com.codegym.castudymd6final.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "money_type")
public class MoneyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public MoneyType() {
    }

    public MoneyType(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public MoneyType(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
