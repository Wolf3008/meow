
package com.mycompany.meowcrm.service;

import com.mycompany.meowcrm.dao.task.ITaskDao;
import com.mycompany.meowcrm.dao.task.ITaskStateDao;
import com.mycompany.meowcrm.dao.task.ITaskTypeDao;
import com.mycompany.meowcrm.model.task.Task;
import com.mycompany.meowcrm.model.task.TaskState;
import com.mycompany.meowcrm.model.task.TaskType;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TaskService implements ITaskService{
    
    @Autowired
    private ITaskDao taskDao;
    
    @Autowired
    private ITaskTypeDao taskTypeDao;
    
    @Autowired
    private ITaskStateDao taskStateDao;

    @Override
    public long addTask(Task task) {
        return taskDao.add(task);
    }

    @Override
    public long updateTask(Task task) {
        return taskDao.add(task);
    }

    @Override
    public void delTask(long id) {
        taskDao.delete(id);
    }

    @Override
    public Task getTask(long id) {
        return taskDao.getById(id);
    }

    @Override
    public Map filterClients(int page, int items, Integer[] type, Integer[] state, String filter, Long[] managers, Date from, Date to) {
        return taskDao.filter(page, items, type, state, filter, managers, from, to);
    }

    @Override
    public long addTaskType(TaskType taskType) {
        return taskTypeDao.add(taskType);
    }

    @Override
    public long updateTaskType(TaskType taskType) {
        return taskTypeDao.add(taskType);
    }

    @Override
    public void delTaskType(int id) {
        taskTypeDao.delete(id);
    }

    @Override
    public List getTypes() {
        return (List) taskTypeDao.list();
    }

    @Override
    public long addTaskState(TaskState taskState) {
        return taskStateDao.add(taskState);
    }

    @Override
    public long updateTaskState(TaskState taskState) {
        return taskStateDao.add(taskState);
    }

    @Override
    public void delTaskState(int id) {
        taskStateDao.delete(id);
    }

    @Override
    public List getStates() {
        return (List) taskStateDao.list();
    }
    
}
