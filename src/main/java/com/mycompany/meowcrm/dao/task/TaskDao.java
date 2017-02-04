package com.mycompany.meowcrm.dao.task;

import com.mycompany.meowcrm.model.task.Task;
import java.util.Date;
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
public class TaskDao implements ITaskDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Task entity) {
        return ((Task) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public Task getById(Long id) {
        return (Task) sessionFactory.getCurrentSession().load(Task.class, id);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Map filter(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers, Date from, Date to) {
        Session sess = sessionFactory.getCurrentSession();

        DetachedCriteria crit = DetachedCriteria.forClass(Task.class);

        //some filtering
        if (type != null) {
            crit.createAlias("type", "t", JoinType.LEFT_OUTER_JOIN);
            crit.add(Restrictions.in("t.id", type));
        }

        if (state != null) {
            crit.createAlias("state", "s", JoinType.LEFT_OUTER_JOIN);
            crit.add(Restrictions.in("s.id", state));
        }

        if (filter != null && !"".equals(filter)) {
            crit.add(Restrictions.like("text", "%" + filter + "%"));
        }

        if (managers != null) {
            crit.createAlias("responsibleUser", "m")
                    .add(Restrictions.in("m.id", managers));
        }

        //filtering by date
        if (from != null && to == null) {
            crit.add(Restrictions.ge("date", from));
        } else if (from == null && to != null) {
            crit.add(Restrictions.le("date", to));
        } else if (from != null && to != null) {
            crit.add(Restrictions.between("date", from, to));
        }

        //
        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria criteria = sess.createCriteria(Task.class);
        criteria.add(Subqueries.propertyIn("id", crit));
        criteria.addOrder(Order.asc("date"));
        criteria.setFirstResult((page - 1) * items);
        criteria.setMaxResults(items);

        Criteria countCrit = sess.createCriteria(Task.class);
        countCrit.add(Subqueries.propertyIn("id", crit));
        countCrit.setProjection(Projections.rowCount());

        Map res = new HashMap();
        res.put("rows", criteria.list());
        res.put("count", countCrit.uniqueResult());

        return res;
    }

}
