package com.codegym.castudymd6final.service.addMoney;

import com.codegym.castudymd6final.model.entity.AddMoney;
import com.codegym.castudymd6final.repository.IAddMoneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AddMoneySV implements IAddMoneySV {
    @Autowired
    private IAddMoneyRepo addMoneyRepo;
    @Override
    public List<AddMoney> findAll() {
        return addMoneyRepo.findAll() ;
    }

    @Override
    public AddMoney save(AddMoney addMoney) {
        return addMoneyRepo.save(addMoney);
    }

    @Override
    public void removeById(Long id) {
        addMoneyRepo.deleteById(id);
    }

    @Override
    public Optional<AddMoney> findById(Long id) {
        return addMoneyRepo.findById(id);
    }

    @Override
    public List<AddMoney> getAddMoneyByWallet(Long idWallet) {
        return addMoneyRepo.getAddMoneyByWallet(idWallet);
    }
}
