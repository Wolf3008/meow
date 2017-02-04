package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.dao.IUserDao;
import com.mycompany.meowcrm.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService implements IUserService {
    
    @Autowired
    private IUserDao userDao;
    
    @Autowired
    private ShaPasswordEncoder shaPasswordEncoder;

    @Override
    public long addClient(User user) {
        if(user.getNewPass()!=null){
            user.setPass(shaPasswordEncoder.encodePassword(user.getNewPass(), null));
        }else{
            User usr = userDao.getById(user.getId());
            user.setPass(usr.getPass());
        }
        return userDao.add(user);
    }

    @Override
    public long updateClient(User user) {
        if(user.getNewPass()!=null){
            user.setPass(shaPasswordEncoder.encodePassword(user.getNewPass(), null));
        }else{
            User usr = userDao.getById(user.getId());
            user.setPass(usr.getPass());
        }
        return userDao.add(user);
    }

    @Override
    public void delClient(long id) {
        userDao.delete(id);
    }

    @Override
    public User getClient(long id) {
        return userDao.getById(id);
    }

    @Override
    public List getAll() {
        return (List) userDao.list();
    }

}
