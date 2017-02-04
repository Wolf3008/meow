package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.model.deal.Deal;
import com.mycompany.meowcrm.model.deal.DealState;
import com.mycompany.meowcrm.model.deal.DealType;
import com.mycompany.meowcrm.model.deal.ThingType;
import java.util.List;
import java.util.Map;

public interface IDealService {

    public long addDeal(Deal deal);

    public long update(Deal deal);

    public void del(long id);

    public Deal get(long id);

    public Map filterDeals(
            int page,
            int items,
            Integer[] type,
            Integer[] state,
            String filter,
            Long[] managers);

    public long addDealType(DealType dealType);

    public long updateDealType(DealType dealType);

    public void delDealType(int id);

    public List getDealTypes();

    public long addDealState(DealState dealState);

    public long updateDealState(DealState dealState);

    public void delDealState(int id);

    public List getDealStates();

    public long addThingType(ThingType thingType);

    public long updateThingType(ThingType thingType);

    public void delThingType(int id);

    public List getThingTypes(Integer dealType);
}
