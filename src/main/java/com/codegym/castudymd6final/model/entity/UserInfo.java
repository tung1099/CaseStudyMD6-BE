package com.codegym.castudymd6final.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String avatar;

    private String phoneNumber;

    private String birthDay;

    private String address;

    @OneToOne
    private User user;

    public UserInfo(String name, String avatar, String phoneNumber, String birthDay, String address, User user) {
        this.name = name;
        this.avatar = avatar;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.address = address;
        this.user = user;
    }

    public UserInfo(String name, String phoneNumber, String birthDay, String address, User user) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
        this.address = address;
        this.user = user;
    }
}
