package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.client.ClientType;

public interface IClientTypeDao extends ICrudRepo<ClientType, Integer> {

    public Iterable<ClientType> list();
}
