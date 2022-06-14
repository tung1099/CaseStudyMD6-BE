package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.entity.ShareWallet;
import com.codegym.castudymd6final.model.entity.User;
import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.service.shareWallet.IShareWalletService;
import com.codegym.castudymd6final.service.user.UserService;
import com.codegym.castudymd6final.service.wallet.WalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/share")
@CrossOrigin("*")
public class ShareWalletController {
    @Autowired
    private IShareWalletService shareWalletService;

    @Autowired
    private UserService userService;

    @Autowired
    private WalletSV walletSV;

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<ShareWallet>> findALlSharedWallet(@PathVariable Long userId) {
        List<ShareWallet> shareWallets = new ArrayList<>();
        List<Long> list = shareWalletService.findListWalletShare(userId);
        for (int i = 0 ; i < list.size(); i++) {
            shareWallets.add(shareWalletService.findById(list.get(i)).get());
        }
        return new ResponseEntity<>(shareWallets, HttpStatus.OK);
    }

    @PostMapping("create/{walletId}")
    public ResponseEntity<ShareWallet> createShareWallet(@PathVariable Long walletId, @RequestParam(name = "username") String username) {
        List<User> users = userService.findAll();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                User user1= userService.findByUserName(username);
                Wallet wallet = walletSV.findById(walletId).get();
                if (wallet.getUser().getId() == user1.getId()) {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
                ShareWallet shareWallet = new ShareWallet(user1, wallet);
                shareWalletService.save(shareWallet);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }
        return null;
    }

    @DeleteMapping("/delete/{shareId}")
    public ResponseEntity<ShareWallet> delete(@PathVariable Long shareId) {
        this.shareWalletService.removeById(shareId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
