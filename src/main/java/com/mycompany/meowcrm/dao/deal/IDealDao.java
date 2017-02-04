package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.deal.Deal;
import java.util.Map;

public interface IDealDao extends ICrudRepo<Deal, Long> {

    public Map filter(int page, int items, String filter, Integer[] types, Integer[] states, Long[] managers);
}
