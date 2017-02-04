package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.client.ContactType;

public interface IContactTypeDao extends ICrudRepo<ContactType, Integer> {

    public Iterable<ContactType> list();
}
