package com.mycompany.meowcrm.dao;

import com.mycompany.meowcrm.model.User;

public interface IUserDao extends ICrudRepo<User, Long> {

    public User getByLogin(String login);

    public Iterable<User> list();
}
