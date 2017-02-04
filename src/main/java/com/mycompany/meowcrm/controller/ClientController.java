package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.model.client.Client;
import com.mycompany.meowcrm.model.client.ClientNotice;
import com.mycompany.meowcrm.model.client.ClientState;
import com.mycompany.meowcrm.model.client.ClientType;
import com.mycompany.meowcrm.service.IClientService;
import com.mycompany.meowcrm.util.ISessionUser;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/client")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @Autowired
    private ISessionUser sessionUser;

    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT)
    public long addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public long updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delClient(@PathVariable long id) {
        clientService.delClient(id);
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Client getClient(@PathVariable long id) {
        return clientService.getClient(id);
    }

    @ResponseBody
    @RequestMapping(value = "/filter", method = RequestMethod.GET)
    public Map filterClients(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int items,
            @RequestParam(required = false) Integer[] type,
            @RequestParam(required = false) Integer[] state,
            @RequestParam(required = false) String filter,
            @RequestParam(required = false) Long[] managers) {
        return clientService.filterClients(page, items, type, state, filter, managers);
    }

    @ResponseBody
    @RequestMapping(value = "/byname", method = RequestMethod.GET)
    public List filterClientsByName(
            @RequestParam(required = true) int items,
            @RequestParam(required = false) String filter) {
        return clientService.filterClientsByName(items, filter);
    }

    @ResponseBody
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public long addClientType(@RequestBody ClientType clientType) {
        return clientService.addClientType(clientType);
    }

    @ResponseBody
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public void delClientType(@PathVariable int id) {
        clientService.delClientType(id);
    }

    @ResponseBody
    @RequestMapping(value = "/type/{id}", method = RequestMethod.POST)
    public long updateClientType(@RequestBody ClientType clientType) {
        return clientService.updateClientType(clientType);
    }

    @ResponseBody
    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public List getTypes() {
        return clientService.getTypes();
    }

    @ResponseBody
    @RequestMapping(value = "/state", method = RequestMethod.PUT)
    public long addClientState(@RequestBody ClientState clientState) {
        return clientService.addClientState(clientState);
    }

    @ResponseBody
    @RequestMapping(value = "/state/{id}", method = RequestMethod.DELETE)
    public void delClientState(@PathVariable int id) {
        clientService.delClientState(id);
    }

    @ResponseBody
    @RequestMapping(value = "/state/{id}", method = RequestMethod.POST)
    public long updateClientState(@RequestBody ClientState clientState) {
        return clientService.updateClientState(clientState);
    }

    @ResponseBody
    @RequestMapping(value = "/state/list", method = RequestMethod.GET)
    public List getStates() {
        return clientService.getStates();
    }

    //client notices
    @ResponseBody
    @RequestMapping(value = "{clId}/notice", method = RequestMethod.PUT)
    public long addClientNotice(@RequestBody ClientNotice clientState) {
        return clientService.addClientNotice(clientState);
    }

    @ResponseBody
    @RequestMapping(value = "/notice/{id}", method = RequestMethod.DELETE)
    public void delClientNotice(@PathVariable long id) {
        clientService.delClientNotice(id);
    }

    @ResponseBody
    @RequestMapping(value = "/notice/{id}", method = RequestMethod.POST)
    public long updateClientNotice(@RequestBody ClientNotice clientState) {
        return clientService.updateClientNotice(clientState);
    }

    @ResponseBody
    @RequestMapping(value = "{clId}/notice/list", method = RequestMethod.GET)
    public List getNotice(@PathVariable long clId) {
        return clientService.getNotice(clId);
    }
}
