package com.mycompany.meowcrm.dao.task;

import com.mycompany.meowcrm.model.task.TaskState;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskStateDao implements ITaskStateDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(TaskState entity) {
        return ((TaskState) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public TaskState getById(Integer id) {
        return (TaskState) sessionFactory.getCurrentSession().load(TaskState.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<TaskState> list() {
        return sessionFactory.getCurrentSession().createCriteria(TaskState.class)
                .addOrder(Order.asc("id")).list();
    }
}
