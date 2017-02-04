package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.model.client.ContactType;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ContactTypeDao implements IContactTypeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(ContactType entity) {
        return ((ContactType) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public ContactType getById(Integer id) {
        return (ContactType) sessionFactory.getCurrentSession().load(ContactType.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<ContactType> list() {
        return sessionFactory.getCurrentSession().createCriteria(ContactType.class)
                .addOrder(Order.asc("id")).list();
    }

}
