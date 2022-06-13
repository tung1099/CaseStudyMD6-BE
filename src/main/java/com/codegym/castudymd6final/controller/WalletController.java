package com.codegym.castudymd6final.controller;


import com.codegym.castudymd6final.model.dto.SumMoney;
import com.codegym.castudymd6final.model.entity.*;
import com.codegym.castudymd6final.service.addMoney.IAddMoneySV;
import com.codegym.castudymd6final.service.iconUser.IIconSV;
import com.codegym.castudymd6final.service.inout.IInOutSV;
import com.codegym.castudymd6final.service.sumMoney.ISumMoneySV;
import com.codegym.castudymd6final.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.service.moneyType.MoneyTypeSV;
import com.codegym.castudymd6final.service.user.IUserService;
import com.codegym.castudymd6final.service.wallet.WalletSV;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

    @Autowired
    private ISumMoneySV sumMoneySV;

    @Autowired
    private IAddMoneySV addMoneySV;

    @Autowired
    private IInOutSV inOutSV;

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
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(wallet.getName(), wallet.getIcon(), wallet.getTotal(),wallet.getMoneyType(), wallet.getNote(), user );
        wallet1.setDate(new Date());
        wallet1.setBalance(wallet.getTotal());
        AddMoney addMoney = new AddMoney(wallet1.getTotal(), wallet1.getDate(), wallet1);
        addMoneySV.save(addMoney);
        if (wallet1.getIcon() == null) {
            wallet1.setIcon(new Icon(1L, "https://static.moneylover.me/img/icon/icon.png"));
        }
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

    @PutMapping("/editWallet/{id}/{idUser}")
    public ResponseEntity<Wallet> editWallet(@RequestBody Wallet wallet, @PathVariable Long id, @PathVariable Long idUser){
        Optional<Wallet> walletOptional = walletSV.findById(id);
        if (!walletOptional.isPresent()) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userService.findById(idUser).get();
        Wallet wallet1 = new Wallet(id, wallet.getName(), wallet.getDate(), wallet.getIcon(), wallet.getMoneyType(), wallet.getTotal(), wallet.getBalance(), wallet.getNote(), user);
        return new ResponseEntity<>(walletSV.save(wallet1), HttpStatus.OK);
    }

    @GetMapping("/sumMoney/{idUser}")
    public ResponseEntity<List<SumMoney>> getSumMoney(@PathVariable Long idUser){
        List<SumMoney> sumMonies = sumMoneySV.getSumMoney(idUser);
        return new ResponseEntity<>(sumMonies, HttpStatus.OK);
    }

    @PostMapping("/inOut/{idWallet}/{month}")
    public ResponseEntity<InOut> getInOut(@PathVariable Long idWallet,
                                          @PathVariable int month) {
        int inFlow = inOutSV.getInFlow(idWallet, month);
        int outFlow = inOutSV.getOutFlow(idWallet, month);
        InOut inOut = new InOut(month,inFlow, outFlow);
        return new ResponseEntity<>(inOutSV.save(inOut), HttpStatus.CREATED);
    }




}
