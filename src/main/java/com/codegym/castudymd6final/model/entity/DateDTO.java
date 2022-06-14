package com.codegym.castudymd6final.model.entity;

import javax.persistence.Entity;
import java.util.Date;

public class DateDTO {
    Long idWallet;
    Date date1;
    Date date2;

    public DateDTO(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    public DateDTO(Long idWallet, Date date1, Date date2) {
        this.idWallet = idWallet;
        this.date1 = date1;
        this.date2 = date2;
    }

    public DateDTO() {
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public Long getIdWallet() {
        return idWallet;
    }

    public void setIdWallet(Long idWallet) {
        this.idWallet = idWallet;
    }
}
