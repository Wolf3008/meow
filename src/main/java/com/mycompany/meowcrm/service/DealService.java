package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.dao.deal.IDealDao;
import com.mycompany.meowcrm.dao.deal.IDealStateDao;
import com.mycompany.meowcrm.dao.deal.IDealTypeDao;
import com.mycompany.meowcrm.dao.deal.IThingTypeDao;
import com.mycompany.meowcrm.model.deal.Deal;
import com.mycompany.meowcrm.model.deal.DealState;
import com.mycompany.meowcrm.model.deal.DealType;
import com.mycompany.meowcrm.model.deal.ThingType;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class DealService implements IDealService {

    @Autowired
    private IDealDao dealDao;

    @Autowired
    private IDealTypeDao dealTypeDao;

    @Autowired
    private IDealStateDao dealStateDao;

    @Autowired
    private IThingTypeDao thingTypeDao;

    @Override
    public long addDeal(Deal deal) {
        return dealDao.add(deal);
    }

    @Override
    public long update(Deal deal) {
        return dealDao.add(deal);
    }

    @Override
    public void del(long id) {
        dealDao.delete(id);
    }

    @Override
    public Deal get(long id) {
        return dealDao.getById(id);
    }

    @Override
    public Map filterDeals(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers) {
        return dealDao.filter(page, items, filter, type, state, managers);
    }

    @Override
    public long addDealType(DealType dealType) {
        return dealTypeDao.add(dealType);
    }

    @Override
    public long updateDealType(DealType dealType) {
        return dealTypeDao.add(dealType);
    }

    @Override
    public void delDealType(int id) {
        dealTypeDao.delete(id);
    }

    @Override
    public List getDealTypes() {
        return (List) dealTypeDao.list();
    }

    @Override
    public long addDealState(DealState dealState) {
        return dealStateDao.add(dealState);
    }

    @Override
    public long updateDealState(DealState dealState) {
        return dealStateDao.add(dealState);
    }

    @Override
    public void delDealState(int id) {
        dealStateDao.delete(id);
    }

    @Override
    public List getDealStates() {
        return (List) dealStateDao.list();
    }

    @Override
    public long addThingType(ThingType thingType) {
        return thingTypeDao.add(thingType);
    }

    @Override
    public long updateThingType(ThingType thingType) {
        return thingTypeDao.add(thingType);
    }

    @Override
    public void delThingType(int id) {
        thingTypeDao.delete(id);
    }

    @Override
    public List getThingTypes(Integer dealType) {
        return (List) thingTypeDao.list();
    }

}
