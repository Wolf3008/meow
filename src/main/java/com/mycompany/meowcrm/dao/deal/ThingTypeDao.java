package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.model.deal.ThingType;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ThingTypeDao implements IThingTypeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(ThingType entity) {
        return ((ThingType) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public ThingType getById(Integer id) {
        return (ThingType) sessionFactory.getCurrentSession().load(ThingType.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<ThingType> list() {
        return sessionFactory.getCurrentSession().createCriteria(ThingType.class)
                .addOrder(Order.asc("id")).list();
    }

}
