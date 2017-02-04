package com.mycompany.meowcrm.util;

import com.mycompany.meowcrm.dao.IUserDao;
import com.mycompany.meowcrm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public class SessionUser implements ISessionUser {

    @Autowired
    private IUserDao userDao;

    private User user = null;

    @Override
    public User getSessionUser() {
        if (user != null) {
            return user;
        }
        user = userDao.getByLogin(SecurityContextHolder.getContext().getAuthentication().getName());
        return user;
    }

}
