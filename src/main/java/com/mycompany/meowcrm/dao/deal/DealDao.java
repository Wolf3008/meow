package com.mycompany.meowcrm.dao.deal;

import com.mycompany.meowcrm.model.deal.Deal;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DealDao implements IDealDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Deal entity) {
        return ((Deal) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public Deal getById(Long id) {
        return (Deal) sessionFactory.getCurrentSession().load(Deal.class, id);
    }

    @Override
    public void delete(Long id) {
        Deal deal = getById(id);
        Session sess = sessionFactory.getCurrentSession();
        deal.getTasks().stream().forEach((t) -> {
            if ((t.getClients().size() + t.getDeals().size()) > 1) {
                t.getDeals().remove(deal);
            } else {
                sess.delete(t);
            }
        });
        sess.flush();
        sess.delete(deal);
    }

    @Override
    public Map filter(int page, int items, String filter, Integer[] types, Integer[] states, Long[] managers) {
        DetachedCriteria crit = DetachedCriteria.forClass(Deal.class);

        //some filtering
        if (filter != null) {
            crit.add(Restrictions
                    .disjunction()
                    .add(Restrictions.like("name", filter + "%"))
                    .add(Restrictions.like("comment", filter + "%"))
            );
        }

        if (types != null) {
            crit.createAlias("type", "t", JoinType.LEFT_OUTER_JOIN);
            crit.add(Restrictions.in("t.id", types));
        }

        if (states != null) {
            crit.createAlias("state", "s", JoinType.LEFT_OUTER_JOIN);
            crit.add(Restrictions.in("s.id", states));
        }

        if (managers != null) {
            crit.createAlias("manager", "m", JoinType.LEFT_OUTER_JOIN);
            crit.add(Restrictions.in("m.id", managers));
        }

        crit.setProjection(Projections.distinct(Projections.id()));

        Session sess = sessionFactory.getCurrentSession();

        Criteria criteria = sess.createCriteria(Deal.class);
        criteria.add(Subqueries.propertyIn("id", crit));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult((page - 1) * items);
        criteria.setMaxResults(items);

        Criteria countCrit = sess.createCriteria(Deal.class);
        countCrit.add(Subqueries.propertyIn("id", crit));
        countCrit.setProjection(Projections.rowCount());

        Map res = new HashMap();
        res.put("rows", criteria.list());
        res.put("count", countCrit.uniqueResult());

        return res;
    }

}
