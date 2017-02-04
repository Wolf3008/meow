package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.client.Client;
import java.util.List;
import java.util.Map;

public interface IClientDao extends ICrudRepo<Client, Long> {

    public List getClientTypeList();

    public List getClientStateList();

    public Map filter(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers);

    public List filterByName(int items, String filter);

    public List filterByPhone(String phone);
}
