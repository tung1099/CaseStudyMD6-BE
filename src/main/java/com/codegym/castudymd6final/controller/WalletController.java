package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Controller
@RequestMapping("wallet")
@CrossOrigin("*")
public class WalletController {
    @Autowired
    private IWalletSV walletService;

    @GetMapping("/list")
    public ResponseEntity<List<Wallet>> showAllWallet(){
        return new ResponseEntity<>(walletService.findAll(), HttpStatus.OK);
    }
}
