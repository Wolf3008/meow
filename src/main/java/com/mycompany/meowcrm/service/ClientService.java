package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.dao.client.IClientDao;
import com.mycompany.meowcrm.dao.client.IClientNoticeDao;
import com.mycompany.meowcrm.dao.client.IClientStateDao;
import com.mycompany.meowcrm.dao.client.IClientTypeDao;
import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.client.ClientNotice;
import com.mycompany.meowcrm.model.client.ClientState;
import com.mycompany.meowcrm.model.client.ClientType;
import com.mycompany.meowcrm.util.ISessionUser;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientDao clientDao;

    @Autowired
    private IClientTypeDao clientTypeDao;

    @Autowired
    private IClientStateDao clientStateDao;

    @Autowired
    private IClientNoticeDao clientNoticeDao;

    @Autowired
    private ISessionUser sessionUser;

    @Override
    public long addClient(Client client) {
        return clientDao.add(client);
    }

    @Override
    public long updateClient(Client client) {
        return clientDao.add(client);
    }

    @Override
    public void delClient(long id) {
        clientDao.delete(id);
    }

    @Override
    public Client getClient(long id) {
        return clientDao.getById(id);
    }

    @Override
    public Map filterClients(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers) {
        return clientDao.filter(page, items, type, state, filter, managers);
    }

    @Override
    public List filterClientsByName(int items, String filter) {
        return clientDao.filterByName(items, filter);
    }

    @Override
    public long addClientType(ClientType clientType) {
        return clientTypeDao.add(clientType);
    }

    @Override
    public void delClientType(int id) {
        clientTypeDao.delete(id);
    }

    @Override
    public long updateClientType(ClientType clientType) {
        return clientTypeDao.add(clientType);
    }

    @Override
    public List getTypes() {
        return (List) clientTypeDao.list();
    }

    @Override
    public long addClientState(ClientState clientState) {
        return clientStateDao.add(clientState);
    }

    @Override
    public void delClientState(int id) {
        clientStateDao.delete(id);
    }

    @Override
    public long updateClientState(ClientState clientState) {
        return clientStateDao.add(clientState);
    }

    @Override
    public List getStates() {
        return (List) clientStateDao.list();
    }

    @Override
    public long addClientNotice(ClientNotice clientState) {
        clientState.setManager(sessionUser.getSessionUser());
        return clientNoticeDao.add(clientState);
    }

    @Override
    public void delClientNotice(long id) {
        clientNoticeDao.delete(id);
    }

    @Override
    public long updateClientNotice(ClientNotice clientState) {
        return clientNoticeDao.add(clientState);
    }

    @Override
    public List getNotice(long clId) {
        return (List) clientNoticeDao.getClientNotice(clId);
    }

}
