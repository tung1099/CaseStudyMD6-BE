package com.codegym.castudymd6final.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class InOut {

    private int inFlow;

    private int outFlow;

    public InOut() {
    }

    public InOut(int inFlow, int outFlow) {
        this.inFlow = inFlow;
        this.outFlow = outFlow;
    }

    public int getInFlow() {
        return inFlow;
    }

    public void setInFlow(int inFlow) {
        this.inFlow = inFlow;
    }

    public int getOutFlow() {
        return outFlow;
    }

    public void setOutFlow(int outFlow) {
        this.outFlow = outFlow;
    }
}
