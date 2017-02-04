package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.deal.DealType;

public interface IDealTypeDao extends ICrudRepo<DealType, Integer> {

    public Iterable<DealType> list();
}
