package com.codegym.castudymd6final.service.inout;

import com.codegym.castudymd6final.model.entity.InOut;
import com.codegym.castudymd6final.service.IGeneralService;

public interface IInOutSV extends IGeneralService<InOut> {
    int getInFlow(Long idWallet, int month, int year);

    int getOutFlow(Long idWallet, int month, int year);
}
