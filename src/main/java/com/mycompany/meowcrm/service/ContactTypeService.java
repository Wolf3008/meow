package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.dao.client.IContactTypeDao;
import com.mycompany.meowcrm.model.client.ContactType;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ContactTypeService implements IContactTypeService {

    @Autowired
    private IContactTypeDao contactTypeDao;

    @Override
    public long addContactType(ContactType contactType) {
        return contactTypeDao.add(contactType);
    }

    @Override
    public long updateContactState(ContactType contactType) {
        return contactTypeDao.add(contactType);
    }

    @Override
    public void delContactType(int id) {
        contactTypeDao.delete(id);
    }

    @Override
    public List getStates() {
        return (List) contactTypeDao.list();
    }

}
