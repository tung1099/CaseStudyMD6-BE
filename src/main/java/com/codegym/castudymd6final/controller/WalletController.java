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

import com.codegym.castudymd6final.model.dto.WalletForm;
import com.codegym.castudymd6final.model.entity.MoneyType;
import com.codegym.castudymd6final.model.entity.User;
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
    private IWalletSV walletService;

    @GetMapping
    public ResponseEntity<List<Wallet>> findAllWallet() {
        List<Wallet> wallets = walletService.findAll();
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }
    @Autowired
    private WalletSV walletSV;

    @Autowired
    Environment evn;

    @Autowired
    private MoneyTypeSV moneyTypeSV;

    @Autowired
    private IUserService userService;

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

//    @PostMapping("/{idUser}/create")
//    public ResponseEntity<Wallet> saveWallet(@ModelAttribute WalletForm walletForm,  @PathVariable Long idUser){
//        User user = userRepository.findById(idUser).get();
//        walletForm.setBalance(walletForm.getTotal());
//        MultipartFile multipartFile = walletForm.getIcon();
//        String fileName = multipartFile.getOriginalFilename();
//        String fileUpload = evn.getProperty("upload.path");
//        try {
//            FileCopyUtils.copy(fileName.getBytes(), new File(fileUpload + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Wallet wallet = new Wallet(fileName, walletForm.getName(), walletForm.getTotal(), walletForm.getBalance(), new Date(), walletForm.getNote(), walletForm.getMoneyType(),user );
//        walletSV.save(wallet);
//        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
//    }

    @PostMapping("/create/{idUser}")
    public ResponseEntity<Wallet> saveWallet(@ModelAttribute WalletForm walletForm, @PathVariable Long idUser){
        User user = userService.findById(idUser).get();
        walletForm.setBalance(walletForm.getTotal());
        MultipartFile multipartFile = walletForm.getIcon();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = evn.getProperty("upload.path");
        try {
            FileCopyUtils.copy(fileName.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Wallet wallet = new Wallet(fileName, walletForm.getName(), walletForm.getTotal(), walletForm.getMoneyType(), walletForm.getBalance(), new Date(), walletForm.getNote(),user );
        walletSV.save(wallet);
        return new ResponseEntity<>(wallet, HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Wallet> editWallet(@PathVariable Long id, @ModelAttribute WalletForm walletForm) {
        Optional<Wallet> walletOptional = walletSV.findById(id);
        walletForm.setId(walletOptional.get().getId());
        MultipartFile multipartFile = walletForm.getIcon();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = evn.getProperty("upload.path");
        Wallet existWallet = new Wallet(id, fileName, walletForm.getName(), walletForm.getTotal(), walletForm.getBalance(), new Date(), walletForm.getNote(), walletForm.getMoneyType(),walletForm.getUser());
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (existWallet.getIcon().equals("fileName.jpg")){
            existWallet.setIcon(walletOptional.get().getIcon());
        }
        walletSV.save(existWallet);
        return new ResponseEntity<>(existWallet, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Wallet> deleteWallet(@PathVariable Long id) {
        Optional<Wallet> walletOptional = walletSV.findById(id);
        if (!walletOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        walletSV.removeById(id);
        return new ResponseEntity<>(walletOptional.get(), HttpStatus.NO_CONTENT);
    }

}
