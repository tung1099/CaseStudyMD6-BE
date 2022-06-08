package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.dto.WalletForm;
import com.codegym.castudymd6final.model.entity.MoneyType;
import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.service.moneyType.MoneyTypeSV;
import com.codegym.castudymd6final.service.wallet.WalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Wallet>> findAll(){
        List<Wallet> wallets = walletSV.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping("/getWalletByUserId/{id}")
    public ResponseEntity<List<Wallet>> findAllWalletByUserId(@PathVariable Long id){
        List<Wallet> wallets = walletSV.getWalletByUserId(id);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Wallet> saveWallet(@ModelAttribute WalletForm walletForm){
        MultipartFile multipartFile = walletForm.getIcon();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = evn.getProperty("upload.path");
        try {
            FileCopyUtils.copy(fileName.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Wallet wallet = new Wallet(fileName, walletForm.getName(), walletForm.getTotal(), walletForm.getDate(), walletForm.getNote(), walletForm.getMoneyType());
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }








}
