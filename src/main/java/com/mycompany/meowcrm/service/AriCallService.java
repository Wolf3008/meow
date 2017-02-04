package com.mycompany.meowcrm.service;

import ch.loway.oss.ari4java.ARI;
import ch.loway.oss.ari4java.generated.Channel;
import ch.loway.oss.ari4java.tools.RestException;
import com.mycompany.meowcrm.dao.client.IClientDao;
import com.mycompany.meowcrm.util.ISessionUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AriCallService implements ICallService {

    @Autowired
    private ARI ari;

    @Autowired
    private ISessionUser sessionUser;

    @Autowired
    private IClientDao clientDao;

    @Override
    public void call(String number, String cantext) {
        try {
            //ari.channels().originate("sip/"+sessionUser.getSessionUser().getExtNum(), number, "outbound-allroutes", 0, null, null, "CRM:call", 0);
            String cont = cantext != null ? cantext : "outbound-allroutes";
            ari.channels().originate("sip/" + sessionUser.getSessionUser().getExtNum(), //endpoint
                    number, //exten
                    cont, //context
                    0, //priority
                    null, //label
                    null, //app
                    null, //appArgs
                    "CRM:" + number, //callerId
                    0, //timeout
                    null, //variables
                    null, //chanelId
                    null, //otherChannelId
                    null, //originator
                    "");    //formats
        } catch (RestException ex) {
            Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List usersOnline() {
        try {
            List<Channel> acs = ari.channels().list();
            Map<String, Channel> m = new HashMap<>();
            String chId = null;
            for (Channel ac : acs) {
                m.put(ac.getId(), ac);
                if (ac.getCaller().getNumber().equals(sessionUser.getSessionUser().getExtNum())) {
                    chId = ac.getId();
                }
            }
            if (chId != null && m.get(chId).getConnected() != null) {
                Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, "\nConnected = {0}", m.get(chId).getConnected());
                return clientDao.filterByPhone(m.get(chId).getConnected().getNumber());
            }
        } catch (RestException ex) {
            Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getChanelIdByNumber(String number) {
        try {
            List<Channel> acs = ari.channels().list();
            String chId = null;
            for (Channel ac : acs) {
                if (ac.getCaller().getNumber().equals(number)) {
                    chId = ac.getId();
                }
            }
            return chId;
        } catch (RestException ex) {
            Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getConnectedChanelIdByNumber(String number) {
        try {
            String chId = getChanelIdByNumber(number);
            if (chId != null) {
                String connectedNumber = ari.channels().get(chId).getConnected().getNumber();
                return getChanelIdByNumber(connectedNumber);
            }
        } catch (RestException ex) {
            Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }

    @Override
    public void hangUp() {
        try {
            String chId = getChanelIdByNumber(sessionUser.getSessionUser().getExtNum());
            if (chId != null) {
                ari.channels().hangup(chId, "no_answer");
            }
        } catch (RestException ex) {
            Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void redirectCurr(String number) {
        try {
            String chId = getConnectedChanelIdByNumber(sessionUser.getSessionUser().getExtNum());
            if (chId != null) {
                ari.channels().redirect(chId, "sip/" + number);
            }
        } catch (RestException ex) {
            Logger.getLogger(AriCallService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
