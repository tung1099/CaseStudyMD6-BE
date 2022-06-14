package com.codegym.castudymd6final.service.addMoney;

import com.codegym.castudymd6final.model.entity.AddMoney;
import com.codegym.castudymd6final.service.IGeneralService;

import java.util.List;

public interface IAddMoneySV extends IGeneralService<AddMoney> {
    List<AddMoney> getAddMoneyByWallet(Long idWallet);
}
