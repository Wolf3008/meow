package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.dao.ICdrDao;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CdrService implements ICdrService {

    @Autowired
    private ICdrDao cdrDao;

    @Override
    public Map filter(int page, int size, String[] numbers, Date from, Date to) {
        return cdrDao.filter(page, size, numbers, from, to);
    }

    @Override
    public String getFileNameById(long cdrId) {
        return cdrDao.getFileName(cdrId);
    }

}
