package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.model.User;
import java.util.List;

public interface IUserService {

    public long addClient(User user);

    public long updateClient(User user);

    public void delClient(long id);

    public User getClient(long id);

    public List getAll();
}
