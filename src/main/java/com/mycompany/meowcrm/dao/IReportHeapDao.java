package com.mycompany.meowcrm.dao;

import java.util.Date;
import java.util.Map;

public interface IReportHeapDao {

    public Map getClientReportData1(Date from, Date to, Long[] managers);

    public Map getTaskReportData1(Date from, Date to, Long[] managers);

    public Map getDealReportData1(Date from, Date to, Long[] managers);
}
