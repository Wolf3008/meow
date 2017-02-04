package com.mycompany.meowcrm.dao;

import com.mycompany.meowcrm.model.Cdr;
import com.mycompany.meowcrm.model.CdrRec;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CdrDao implements ICdrDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Map filter(int page, int size, String[] numbers, Date from, Date to) {
        DetachedCriteria crit = DetachedCriteria.forClass(Cdr.class);

        //some filters
        if (numbers != null) {
            Disjunction srcOrDst = Restrictions.disjunction();
            srcOrDst.add(Restrictions.in("src", numbers))
                    .add(Restrictions.in("dst", numbers));
            crit.add(srcOrDst);
        }

        //filtering by date
        if (from != null && to == null) {
            crit.add(Restrictions.ge("calldate", from));
        } else if (from == null && to != null) {
            crit.add(Restrictions.le("calldate", to));
        } else if (from != null && to != null) {
            crit.add(Restrictions.between("calldate", from, to));
        }

        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cdr.class);
        criteria.add(Subqueries.propertyIn("id", crit))
                .addOrder(Order.desc("calldate"))
                .setFirstResult((page - 1) * size)
                .setMaxResults(size);

        Criteria countCriteria = sessionFactory.getCurrentSession().createCriteria(Cdr.class);
        countCriteria.add(Subqueries.propertyIn("id", crit))
                .setProjection(Projections.rowCount());

        Map resMap = new HashMap();
        resMap.put("rows", criteria.list());
        resMap.put("count", countCriteria.uniqueResult());
        return resMap;
    }

    @Override
    public String getFileName(long cdrId) {
        String name = "null.mp3";
        Session sess = sessionFactory.getCurrentSession();
        Cdr cdr = (Cdr) sess.load(Cdr.class, cdrId);

        DetachedCriteria crit = DetachedCriteria.forClass(CdrRec.class)
                .add(Restrictions.eq("uniqueId", cdr.getUniqueid()))
                .add(Restrictions.eq("src", cdr.getSrc()))
                .add(Restrictions.eq("dst", cdr.getDst()))
                .setProjection(Projections.distinct(Projections.id()));

        List<CdrRec> recs = sess.createCriteria(CdrRec.class)
                .add(Subqueries.propertyIn("id", crit))
                .list();
        if (!recs.isEmpty()) {
            name = recs.get(0).getFileName();
        }
        return name;
    }

}
