package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.client.ClientNotice;
import com.mycompany.meowcrm.model.client.ClientState;
import com.mycompany.meowcrm.model.client.ClientType;
import java.util.List;
import java.util.Map;

public interface IClientService {

    public long addClient(Client client);

    public long updateClient(Client client);

    public void delClient(long id);

    public Client getClient(long id);

    public Map filterClients(
            int page,
            int items,
            Integer[] type,
            Integer[] state,
            String filter,
            Long[] managers);

    public List filterClientsByName(int items, String filter);

    public long addClientType(ClientType clientType);

    public void delClientType(int id);

    public long updateClientType(ClientType clientType);

    public List getTypes();

    public long addClientState(ClientState clientState);

    public void delClientState(int id);

    public long updateClientState(ClientState clientState);

    public List getStates();

    public long addClientNotice(ClientNotice clientState);

    public void delClientNotice(long id);

    public long updateClientNotice(ClientNotice clientState);

    public List getNotice(long clId);
}
