package com.mycompany.meowcrm.controller;

import com.mycompany.meowcrm.dao.IReportHeapDao;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/report"})
public class ReportController {

    @Autowired
    private IReportHeapDao reportHeapDao;

    @RequestMapping(value = {"/client"}, method = RequestMethod.GET)
    public Map getClientData(
            @RequestParam(required = false) Long[] managers,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date to) {
        return reportHeapDao.getClientReportData1(from, to, managers);
    }

    @RequestMapping(value = {"/task"}, method = RequestMethod.GET)
    public Map getTaskData(
            @RequestParam(required = false) Long[] managers,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date to) {
        return reportHeapDao.getTaskReportData1(from, to, managers);
    }

    @RequestMapping(value = {"/deal"}, method = RequestMethod.GET)
    public Map getSealData(
            @RequestParam(required = false) Long[] managers,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date from,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date to) {
        return reportHeapDao.getDealReportData1(from, to, managers);
    }
}
