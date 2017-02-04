package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.model.task.Task;
import com.mycompany.meowcrm.model.task.TaskState;
import com.mycompany.meowcrm.model.task.TaskType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ITaskService {

    public long addTask(Task task);

    public long updateTask(Task task);

    public void delTask(long id);

    public Task getTask(long id);

    public Map filterClients(
            int page,
            int items,
            Integer[] type,
            Integer[] state,
            String filter,
            Long[] managers,
            Date from,
            Date to);

    public long addTaskType(TaskType taskType);

    public long updateTaskType(TaskType taskType);

    public void delTaskType(int id);

    public List getTypes();

    public long addTaskState(TaskState taskState);

    public long updateTaskState(TaskState taskState);

    public void delTaskState(int id);

    public List getStates();
}
