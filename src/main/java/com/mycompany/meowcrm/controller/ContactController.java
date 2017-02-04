package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.model.client.ContactType;
import com.mycompany.meowcrm.service.IContactTypeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/contact")
public class ContactController {

    @Autowired
    private IContactTypeService contactTypeService;

    @ResponseBody
    @RequestMapping(value = "/type", method = RequestMethod.PUT)
    public long addContactType(@RequestBody ContactType contactType) {
        return contactTypeService.addContactType(contactType);
    }

    @ResponseBody
    @RequestMapping(value = "/type/{id}", method = RequestMethod.POST)
    public long updateContactState(@RequestBody ContactType contactType) {
        return contactTypeService.updateContactState(contactType);
    }

    @ResponseBody
    @RequestMapping(value = "/type/{id}", method = RequestMethod.DELETE)
    public void delContactType(@PathVariable int id) {
        contactTypeService.delContactType(id);
    }

    @ResponseBody
    @RequestMapping(value = "/type/list", method = RequestMethod.GET)
    public List getStates() {
        return contactTypeService.getStates();
    }
}
