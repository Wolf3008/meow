package com.mycompany.meowcrm.service;

import java.util.Date;
import java.util.Map;

public interface ICdrService {

    public Map filter(int page, int size, String[] numbers, Date from, Date to);

    public String getFileNameById(long cdrId);
}
