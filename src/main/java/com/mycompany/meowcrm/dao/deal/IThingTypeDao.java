package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.deal.ThingType;

public interface IThingTypeDao extends ICrudRepo<ThingType, Integer> {

    public Iterable<ThingType> list();
}
