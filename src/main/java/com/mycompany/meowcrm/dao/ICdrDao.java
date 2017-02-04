package com.mycompany.meowcrm.dao;

import java.util.Date;
import java.util.Map;

public interface ICdrDao {

    public Map filter(int page, int size, String[] numbers, Date from, Date to);

    public String getFileName(long cdrId);
}
