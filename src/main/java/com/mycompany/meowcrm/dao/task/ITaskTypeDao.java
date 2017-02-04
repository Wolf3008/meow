package com.mycompany.meowcrm.dao.task;

import com.mycompany.meowcrm.dao.ICrudRepo;
import com.mycompany.meowcrm.model.task.TaskType;

public interface ITaskTypeDao extends ICrudRepo<TaskType, Integer> {

    public Iterable<TaskType> list();
}
