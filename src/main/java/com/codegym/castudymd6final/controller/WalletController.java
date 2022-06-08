package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.entity.Category;
import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private IWalletSV walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> findAllWallet() {
        List<Wallet> wallets = walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
}
