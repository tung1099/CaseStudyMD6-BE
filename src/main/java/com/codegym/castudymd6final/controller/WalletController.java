package com.codegym.castudymd6final.controller;


import com.codegym.castudymd6final.model.entity.*;
import com.codegym.castudymd6final.service.icon.IIconSV;
import com.codegym.castudymd6final.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.codegym.castudymd6final.model.dto.WalletForm;
import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.repository.IUserRepository;
import com.codegym.castudymd6final.service.moneyType.MoneyTypeSV;
import com.codegym.castudymd6final.service.user.IUserService;
import com.codegym.castudymd6final.service.wallet.WalletSV;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletSV walletSV;

    @Autowired
    Environment evn;

    @Autowired
    private MoneyTypeSV moneyTypeSV;

    @Autowired
    private IIconSV iconSV;

    @Autowired
    private IUserService userService;

    @GetMapping("/icon")
    public ResponseEntity<List<Icon>> findAllIcon(){
        List<Icon> icons = iconSV.findAll();
        return new ResponseEntity<>(icons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findWalletById(@PathVariable Long id){
        Optional<Wallet> walletOptional = walletSV.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/moneytype")
    public ResponseEntity<List<MoneyType>> fillAllType(){
        List<MoneyType> moneyTypes = moneyTypeSV.findAll();
        return new ResponseEntity<>(moneyTypes, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Wallet>> findAll(){
        List<Wallet> wallets = walletSV.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/getWalletByUserId/{id}")
    public ResponseEntity<List<Wallet>> findAllWalletByUserId(@PathVariable Long id){
        List<Wallet> wallets = walletSV.getWalletByUserId(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }


    @PostMapping("/createWallet/{idUser}")
    public ResponseEntity<Wallet> create(@ModelAttribute Wallet wallet, @PathVariable Long idUser) {
        wallet.setBalance(wallet.getTotal());
        wallet.setDate(new Date());
        wallet.setIcon(new Icon(1L,"https://static.moneylover.me/img/icon/icon.png"));
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(wallet.getIcon(), wallet.getName(),wallet.getMoneyType(), wallet.getTotal(), wallet.getBalance(), wallet.getDate(), wallet.getNote(), user );
        return new ResponseEntity<>(walletSV.save(wallet1), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long id) {
        Optional<Wallet> walletOptional = walletSV.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletSV.deleteWallet(id);
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.NO_CONTENT);
    }

}
