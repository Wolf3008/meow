package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.model.client.ClientType;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientTypeDao implements IClientTypeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(ClientType entity) {
        return ((ClientType) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public ClientType getById(Integer id) {
        return (ClientType) sessionFactory.getCurrentSession().load(ClientType.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<ClientType> list() {
        return sessionFactory.getCurrentSession().createCriteria(ClientType.class)
                .addOrder(Order.asc("id")).list();
    }

}
