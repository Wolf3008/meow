package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.dao.ICdrDao;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cdr")
public class CdrController {

    @Autowired
    private ICdrDao cdrDao;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Map list(
            @RequestParam(required = true) int page,
            @RequestParam(required = true) int items,
            @RequestParam(required = false) String[] numbers,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date to) {
        return cdrDao.filter(page, items, numbers, from, to);
    }
}
