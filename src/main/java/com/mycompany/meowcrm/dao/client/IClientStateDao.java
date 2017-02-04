package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.client.ClientState;

public interface IClientStateDao extends ICrudRepo<ClientState, Integer> {

    public Iterable<ClientState> list();
}
