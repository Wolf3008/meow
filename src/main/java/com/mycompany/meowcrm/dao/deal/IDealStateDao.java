package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.deal.DealState;

public interface IDealStateDao extends ICrudRepo<DealState, Integer> {

    Iterable<DealState> list();
}
