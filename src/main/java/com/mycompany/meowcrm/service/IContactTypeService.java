package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.model.client.ContactType;
import java.util.List;

public interface IContactTypeService {

    public long addContactType(ContactType contactType);

    public long updateContactState(ContactType contactType);

    public void delContactType(int id);

    public List getStates();
}
