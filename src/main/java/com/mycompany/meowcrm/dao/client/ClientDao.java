package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.client.ClientState;
import com.mycompany.meowcrm.model.client.ClientType;
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
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ClientDao implements IClientDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(Client entity) {
        entity.getContacts().stream().forEach((c) -> {
            c.setClient(entity);
        });
        return ((Client) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public Client getById(Long id) {
        return (Client) sessionFactory.getCurrentSession().load(Client.class, id);
    }

    @Override
    public void delete(Long id) {
        Session sess = sessionFactory.getCurrentSession();
        Client cl = getById(id);

        //delete all deals single tasks
        cl.getDeals().stream().forEach((d) -> {
            d.getTasks().stream().forEach((t) -> {
                if ((t.getClients().size() + t.getDeals().size()) > 1) {
                    t.getDeals().remove(d);
                } else {
                    sess.delete(t);
                }
            });
        });
        sess.flush();

        //delete all single tasks
        cl.getTasks().stream().forEach((t) -> {
            if ((t.getClients().size() + t.getDeals().size()) > 1) {
                t.getClients().remove(cl);
            } else {
                sess.delete(t);
            }
        });
        sess.flush();
        sess.delete(cl);
    }

    @Override
    public List getClientTypeList() {
        return (List<ClientType>) sessionFactory.getCurrentSession().createCriteria(ClientType.class).list();
    }

    @Override
    public List getClientStateList() {
        return sessionFactory.getCurrentSession().createCriteria(ClientState.class).list();
    }

    @Override
    public Map filter(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers) {
        Session sess = sessionFactory.getCurrentSession();

        DetachedCriteria crit = DetachedCriteria.forClass(Client.class);

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
            //filter = filter.trim();
            crit.createAlias("contacts", "con", JoinType.LEFT_OUTER_JOIN);

            Disjunction or = Restrictions.disjunction();
            or.add(Restrictions.like("comment", "%" + filter + "%"));
            or.add(Restrictions.like("name", "%" + filter + "%"));
            or.add(Restrictions.like("con.contact", "%" + filter + "%"));

            crit.add(or);
        }

        if (managers != null) {
            crit.createAlias("manager", "m")
                    .add(Restrictions.in("m.id", managers));
        }

        //
        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria criteria = sess.createCriteria(Client.class);
        criteria.add(Subqueries.propertyIn("id", crit));
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult((page - 1) * items);
        criteria.setMaxResults(items);

        Criteria countCrit = sess.createCriteria(Client.class);
        countCrit.add(Subqueries.propertyIn("id", crit));
        countCrit.setProjection(Projections.rowCount());

        Map res = new HashMap();
        res.put("rows", criteria.list());
        res.put("count", countCrit.uniqueResult());

        return res;
    }

    @Override
    public List filterByName(int items, String filter) {
        Session sess = sessionFactory.getCurrentSession();

        DetachedCriteria crit = DetachedCriteria.forClass(Client.class);

        //some filtering
        if (filter != null && !"".equals(filter)) {
            crit.add(Restrictions.like("name", "%" + filter + "%"));
        }

        //
        crit.setProjection(Projections.distinct(Projections.id()));

        Criteria criteria = sess.createCriteria(Client.class);
        criteria.add(Subqueries.propertyIn("id", crit));
        criteria.addOrder(Order.desc("id"));
        criteria.setMaxResults(items);

        return criteria.list();
    }

    @Override
    public List filterByPhone(String phone) {
        Session sess = sessionFactory.getCurrentSession();

        Criteria criteria = sess.createCriteria(Client.class)
                .createAlias("contacts", "cts")
                .add(Restrictions.eq("cts.contact", phone))
                .setResultTransformer(Criteria.ROOT_ENTITY);
        return criteria.list();
    }

}
