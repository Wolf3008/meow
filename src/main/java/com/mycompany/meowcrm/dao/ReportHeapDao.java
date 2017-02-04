package com.mycompany.meowcrm.dao;

import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.deal.Deal;
import com.mycompany.meowcrm.model.task.Task;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ReportHeapDao implements IReportHeapDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Map getClientReportData1(Date from, Date to, Long[] managers) {
        Session sess = sessionFactory.getCurrentSession();
        DetachedCriteria crit = DetachedCriteria.forClass(Client.class);

        //filtering by date
        if (from != null && to == null) {
            crit.add(Restrictions.ge("dtIn", from));
        } else if (from == null && to != null) {
            crit.add(Restrictions.le("dtIn", to));
        } else if (from != null && to != null) {
            crit.add(Restrictions.between("dtIn", from, to));
        }

        if (managers != null) {
            crit.createAlias("manager", "m")
                    .add(Restrictions.in("m.id", managers));
        }

        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria typeCrit = sess.createCriteria(Client.class)
                .createAlias("type", "t")
                .add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("id"))
                        .add(Projections.groupProperty("t.name")));
        Criteria stateCrit = sess.createCriteria(Client.class)
                .createAlias("state", "s")
                .add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("id"))
                        .add(Projections.groupProperty("s.name")));
        Map res = new HashMap();
        res.put("types", typeCrit.list());
        res.put("states", stateCrit.list());
        return res;
        //SELECT type, count(*) from reports group by type
    }

    @Override
    public Map getTaskReportData1(Date from, Date to, Long[] managers) {
        Session sess = sessionFactory.getCurrentSession();
        DetachedCriteria crit = DetachedCriteria.forClass(Task.class);

        //filtering by date
        if (from != null && to == null) {
            crit.add(Restrictions.ge("dtIn", from));
        } else if (from == null && to != null) {
            crit.add(Restrictions.le("dtIn", to));
        } else if (from != null && to != null) {
            crit.add(Restrictions.between("dtIn", from, to));
        }

        if (managers != null) {
            crit.createAlias("manager", "m")
                    .add(Restrictions.in("m.id", managers));
        }

        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria typeCrit = sess.createCriteria(Task.class)
                .createAlias("type", "t")
                .add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("id"))
                        .add(Projections.groupProperty("t.name")));
        Criteria stateCrit = sess.createCriteria(Task.class)
                .createAlias("state", "s")
                .add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("id"))
                        .add(Projections.groupProperty("s.name")));
        Map res = new HashMap();
        res.put("types", typeCrit.list());
        res.put("states", stateCrit.list());
        return res;
    }

    @Override
    public Map getDealReportData1(Date from, Date to, Long[] managers) {
        Session sess = sessionFactory.getCurrentSession();
        DetachedCriteria crit = DetachedCriteria.forClass(Deal.class);

        //filtering by date
        if (from != null && to == null) {
            crit.add(Restrictions.ge("dtIn", from));
        } else if (from == null && to != null) {
            crit.add(Restrictions.le("dtIn", to));
        } else if (from != null && to != null) {
            crit.add(Restrictions.between("dtIn", from, to));
        }

        if (managers != null) {
            crit.createAlias("manager", "m")
                    .add(Restrictions.in("m.id", managers));
        }

        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria typeCrit = sess.createCriteria(Deal.class)
                .createAlias("type", "t")
                .add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("id"))
                        .add(Projections.groupProperty("t.name")));
        Criteria stateCrit = sess.createCriteria(Deal.class)
                .createAlias("state", "s")
                .add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.projectionList()
                        .add(Projections.count("id"))
                        .add(Projections.groupProperty("s.name")));
        Map res = new HashMap();
        res.put("types", typeCrit.list());
        res.put("states", stateCrit.list());
        return res;
    }

}
