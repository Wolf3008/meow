package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.model.deal.DealState;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DealStateDao implements IDealStateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(DealState entity) {
        return ((DealState) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public DealState getById(Integer id) {
        return (DealState) sessionFactory.getCurrentSession().load(DealState.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<DealState> list() {
        return sessionFactory.getCurrentSession().createCriteria(DealState.class)
                .addOrder(Order.asc("id")).list();
    }

}
