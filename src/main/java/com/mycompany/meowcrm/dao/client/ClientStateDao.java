package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.model.client.ClientState;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientStateDao implements IClientStateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(ClientState entity) {
        return ((ClientState) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public ClientState getById(Integer id) {
        return (ClientState) sessionFactory.getCurrentSession().load(ClientState.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<ClientState> list() {
        return sessionFactory.getCurrentSession().createCriteria(ClientState.class)
                .addOrder(Order.asc("id")).list();
    }

}
