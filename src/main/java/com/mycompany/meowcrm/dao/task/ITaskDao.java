package com.mycompany.meowcrm.dao.task;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.task.Task;
import java.util.Date;
import java.util.Map;

public interface ITaskDao extends ICrudRepo<Task, Long> {

    public Map filter(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers, Date from, Date to);
}
