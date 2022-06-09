package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.entity.AddMoney;
import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.service.addMoney.IAddMoneySV;
import com.codegym.castudymd6final.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/addMoney")
public class AddMoneyController {
    @Autowired
    private IAddMoneySV addMoneyService;

    @Autowired
    private IWalletSV walletService;

    @GetMapping("/wallet")
    public ResponseEntity<List<Wallet>> showAllWallet() {
        List<Wallet> wallets = walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<AddMoney>> addMoney() {
        List<AddMoney> addMoney = addMoneyService.findAll();
        return new ResponseEntity<>(addMoney, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<AddMoney> saveAddMoney(@RequestBody AddMoney addMoney) {
        Long id = addMoney.getWallet().getId();
        Wallet wallet = walletService.findById(id).get();
        int walletMoney = wallet.getTotal();
        int balanceMoney = wallet.getBalance();
        int money = addMoney.getMoney();
        wallet.setTotal(walletMoney + money);
        wallet.setBalance(balanceMoney + money);
        AddMoney addMoney1 = new AddMoney(money, new Date(), wallet);
        return new ResponseEntity<>(addMoneyService.save(addMoney1), HttpStatus.CREATED);
    }
}

