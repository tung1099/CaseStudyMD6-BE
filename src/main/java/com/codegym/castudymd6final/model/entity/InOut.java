package com.codegym.castudymd6final.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class InOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int year;

    private int month;

    private int inFlow;

    private int outFlow;

    public InOut() {
    }

    public InOut(int inFlow, int outFlow) {
        this.inFlow = inFlow;
        this.outFlow = outFlow;
    }

    public InOut(Long id, int inFlow, int outFlow) {
        this.id = id;
        this.inFlow = inFlow;
        this.outFlow = outFlow;
    }

    public InOut(int month, int year, int inFlow, int outFlow) {
        this.month = month;
        this.year = year;
        this.inFlow = inFlow;
        this.outFlow = outFlow;
    }
}
