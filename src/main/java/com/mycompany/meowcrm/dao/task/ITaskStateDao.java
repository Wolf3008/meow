package com.mycompany.meowcrm.dao.task;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.task.TaskState;

public interface ITaskStateDao extends ICrudRepo<TaskState, Integer> {

    public Iterable<TaskState> list();
}
