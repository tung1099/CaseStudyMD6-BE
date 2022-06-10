package com.codegym.castudymd6final.controller;

import com.codegym.castudymd6final.model.entity.Category;
import com.codegym.castudymd6final.model.entity.Transaction;
import com.codegym.castudymd6final.model.entity.Wallet;
import com.codegym.castudymd6final.model.transactionInDay.AllTransactionWallet;
import com.codegym.castudymd6final.model.transactionInDay.SumInDay;
import com.codegym.castudymd6final.model.transactionInDay.TransactionInDay;
import com.codegym.castudymd6final.service.Transaction.ITransactionSV;
import com.codegym.castudymd6final.service.category.ICategorySV;
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




    @PostMapping("/create")
    public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) throws ParseException {
        Long id = transaction.getWallet().getId();
        Wallet wallet =  walletService.findById(id).get();
        int walletMoney = wallet.getBalance();
        int money = transaction.getAmount();
        wallet.setBalance(walletMoney - money);
//        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        Date date = new Date(String.valueOf(transaction.getDate()));
//        Date todayWithZeroTime = formatter.parse(formatter.format(date));
//        Transaction transaction1 = new Transaction(transaction.getAmount(), transaction.getNote(), date, transaction.getCategory(), transaction.getWallet());
        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.CREATED);
    }

    @PutMapping("/editTransaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction){
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

    @GetMapping("/transactionInDay")
    public ResponseEntity<Iterable<TransactionInDay>> getTransactionInDay (){
        Iterable<TransactionInDay> transactionInDays = transactionService.getTransactionInDay();
        return new ResponseEntity<>(transactionInDays, HttpStatus.OK);
    }

    @GetMapping("/transactionInDayByIdWallet/{id}")
    public ResponseEntity<Iterable<TransactionInDay>> getTransactionInDayByIdWallet (@PathVariable Long id){
        Iterable<TransactionInDay> transactionInDays = transactionService.getTransactionInDayByIdWallet(id);
        return new ResponseEntity<>(transactionInDays, HttpStatus.OK);
    }

    @GetMapping("/allTransactionByIdWallet/{id}")
    public ResponseEntity<Iterable<AllTransactionWallet>> getAllTransactionByIdWallet (@PathVariable Long id){
        Iterable<AllTransactionWallet> transactionInDays = transactionService.getAllTransactionByIdWallet(id);
        return new ResponseEntity<>(transactionInDays, HttpStatus.OK);
    }

    @GetMapping("/sumTransactionInDay/{id}")
    public ResponseEntity<Iterable<SumInDay>> getSumInDay(@PathVariable Long id){
        Iterable<SumInDay> sum = transactionService.getSumInDay(id);
        return new ResponseEntity<>(sum, HttpStatus.OK);
    }
    @Autowired
    private ICategorySV categoryService;

    @GetMapping("/listCate")
    public ResponseEntity<List<Category>> showAllCategory(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

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
}
