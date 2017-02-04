package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.service.ICallService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/call")
public class CallController {

    @Autowired
    private ICallService callService;

    @RequestMapping(value = "/to", method = RequestMethod.PUT)
    public void callTo(
            @RequestParam(required = true) String number,
            @RequestParam(required = false) String context) {
        callService.call(number, context);
    }

    @RequestMapping(value = "/online", method = RequestMethod.GET)
    public List online() {
        return callService.usersOnline();
    }

    @RequestMapping(value = "/hangup", method = RequestMethod.DELETE)
    public void hangup() {
        callService.hangUp();
    }

    @RequestMapping(value = "/redirect", method = RequestMethod.POST)
    public void redirectCur(@RequestParam(required = true) String number) {
        callService.redirectCurr(number);
    }
}
