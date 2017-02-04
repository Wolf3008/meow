package com.mycompany.meowcrm.dao.client;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.client.ClientNotice;
import java.util.List;

public interface IClientNoticeDao extends ICrudRepo<ClientNotice, Long> {

    public List getClientNotice(long clId);
}
