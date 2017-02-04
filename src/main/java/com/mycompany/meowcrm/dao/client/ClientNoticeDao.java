package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.model.client.ClientNotice;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientNoticeDao implements IClientNoticeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List getClientNotice(long clId) {
        DetachedCriteria crit = DetachedCriteria.forClass(ClientNotice.class)
                .createAlias("client", "cl")
                .add(Restrictions.eq("cl.id", clId))
                .setProjection(Projections.distinct(Projections.id()));
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClientNotice.class);
        criteria.add(Subqueries.propertyIn("id", crit))
                .addOrder(Order.desc("dtIn"));
        return criteria.list();
    }

    @Override
    public Long add(ClientNotice entity) {
        return ((ClientNotice) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public ClientNotice getById(Long id) {
        return (ClientNotice) sessionFactory.getCurrentSession().load(ClientNotice.class, id);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

}
