package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.model.deal.DealType;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DealTypeDao implements IDealTypeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(DealType entity) {
        return ((DealType) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public DealType getById(Integer id) {
        return (DealType) sessionFactory.getCurrentSession().load(DealType.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<DealType> list() {
        return sessionFactory.getCurrentSession().createCriteria(DealType.class)
                .addOrder(Order.asc("id")).list();
    }

}
