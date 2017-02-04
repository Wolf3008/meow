package com.mycompany.meowcrm.dao;

import com.mycompany.meowcrm.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao implements IUserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long add(User entity) {
        return ((User) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public User getById(Long id) {
        return (User) sessionFactory.getCurrentSession().load(User.class, id);
    }

    @Override
    public void delete(Long id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<User> list() {
        return sessionFactory.getCurrentSession().createCriteria(User.class).list();
    }

    @Override
    public User getByLogin(String login) {
        User user = (User) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("login", login))
                .uniqueResult();
        return user;
    }

}
