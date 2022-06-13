package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.entity.*;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionUser;
import com.codegym.castudymd6final.service.Transaction.ITransactionSV;
import com.codegym.castudymd6final.service.category.ICategorySV;
import com.codegym.castudymd6final.service.user.IUserService;
import com.codegym.castudymd6final.service.wallet.IWalletSV;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin("*")
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private ITransactionSV transactionService;
    @Autowired
    private IUserService userService;

    @Autowired
    private ICategorySV categorySv;

    @Autowired
    private IWalletSV walletSv;


    @PostMapping("/create/{idUser}")
    public ResponseEntity<Transaction> saveTransaction(@PathVariable Long idUser, @RequestBody Transaction transaction) throws ParseException {
        User user = userService.findById(idUser).get();
        Long id = transaction.getWallet().getId();
        Wallet wallet =  walletService.findById(id).get();
        int walletMoney = wallet.getBalance();
        int money = transaction.getAmount();
        wallet.setBalance(walletMoney - money);
        Transaction transaction1 = new Transaction(transaction.getAmount(), transaction.getNote(), transaction.getDate(), transaction.getCategory(), transaction.getWallet(), user);
        return new ResponseEntity<>(transactionService.save(transaction1), HttpStatus.CREATED);
    }

    @PutMapping("/editTransaction/{id}/{idUser}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @PathVariable Long idUser, @RequestBody Transaction transaction){
        User user = userService.findById(idUser).get();
        Transaction transaction1 = transactionService.findById(id).get();
        Long idWallet = transaction1.getWallet().getId();
        Wallet wallet = walletService.findById(idWallet).get();
        int oldAmountTransaction = transaction1.getAmount();
        wallet.setBalance(wallet.getBalance() + oldAmountTransaction);
        Long newIdWallet = transaction.getWallet().getId();
        Wallet wallet1 = walletService.findById(newIdWallet).get();
        wallet1.setBalance(wallet1.getBalance() - transaction.getAmount());
        Optional<Transaction> transactionOptional = transactionService.findById(id);
        transaction.setId(transactionOptional.get().getId());
        transaction.setUser(user);
        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/deleteTransaction/{id}")
    public ResponseEntity<Transaction> deleteTransaction(@PathVariable Long id){
        Optional<Transaction> optionalTransaction =transactionService.findById(id);
        if (!optionalTransaction.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Transaction transaction = transactionService.findById(id).get();
        Long id1 = transaction.getWallet().getId();
        Wallet wallet =  walletService.findById(id1).get();
        int walletMoney = wallet.getBalance();
        int money = transaction.getAmount();
        wallet.setBalance(walletMoney + money);
        transactionService.removeById(id);
        return new ResponseEntity<>(optionalTransaction.get(), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findTransactionById/{id}")
    public ResponseEntity<Transaction> findTransaction(@PathVariable Long id){
        Transaction transaction = transactionService.findById(id).get();
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/transactionInDay/{idUser}")
    public ResponseEntity<Iterable<Transaction>> getTransactionInDay (@PathVariable Long idUser){
        Iterable<Transaction> transactionInDays = transactionService.getTransactionInDay(idUser);
        return new ResponseEntity<>(transactionInDays, HttpStatus.OK);
    }

    @GetMapping("/transactionInDayByIdWallet/{id}")
    public ResponseEntity<Iterable<Transaction>> getTransactionInDayByIdWallet (@PathVariable Long id){
        Iterable<Transaction> transactionInDays = transactionService.getTransactionInDayByIdWallet(id);
        return new ResponseEntity<>(transactionInDays, HttpStatus.OK);
    }

    @GetMapping("/allTransactionByIdWallet/{id}")
    public ResponseEntity<Iterable<Transaction>> getAllTransactionByIdWallet (@PathVariable Long id){
        Iterable<Transaction> transactionInDays = transactionService.getAllTransactionByIdWallet(id);
        return new ResponseEntity<>(transactionInDays, HttpStatus.OK);
    }

    @GetMapping("/sumTransactionInDay/{id}")
    public ResponseEntity<Iterable<SumInDay>> getSumInDay(@PathVariable Long id){
        Iterable<SumInDay> sum = transactionService.getSumInDay(id);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }
    @Autowired
    private ICategorySV categoryService;

    @Autowired
    private IWalletSV walletService;

    @GetMapping("/listWallet")
    public ResponseEntity<Iterable<Wallet>> showAllWallet(){
        return new ResponseEntity<>(walletService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/listTransaction")
    public ResponseEntity<Iterable<Transaction>> showAllTransaction(){
        return new ResponseEntity<>(transactionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/listTransaction/{idUser}")
    public ResponseEntity<List<Transaction>> showAllTransactionByIdUser(@PathVariable Long idUser){
        return new ResponseEntity<>(transactionService.getListTransactionUser(idUser), HttpStatus.OK);
    }

    @PostMapping("/transactionInTime/{idUser}")
    public ResponseEntity<Iterable<Transaction>> getTransactionInTime (@PathVariable Long idUser,
                                                                       @RequestBody DateDTO date){
        Iterable<Transaction> transactionInTimes = transactionService.getListTransactionInTime(date.getDate1(), date.getDate2(), idUser);
        return new ResponseEntity<>(transactionInTimes, HttpStatus.OK);
    }

    @PostMapping("/transactionInTimeByIdWallet/{id}")
    public ResponseEntity<Iterable<Transaction>> getTransactionInTimeByIdWallet (@PathVariable Long id, @RequestBody DateDTO date){
        Iterable<Transaction> transactionInTimes = transactionService.getListTransactionInTimeByIdWallet(date.getDate1(), date.getDate2(), id);
        return new ResponseEntity<>(transactionInTimes, HttpStatus.OK);
    }
}
