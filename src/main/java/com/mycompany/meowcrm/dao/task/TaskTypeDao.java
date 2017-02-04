package com.mycompany.meowcrm.dao.task;

import com.mycompany.meowcrm.model.task.TaskType;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TaskTypeDao implements ITaskTypeDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Integer add(TaskType entity) {
        return ((TaskType) sessionFactory.getCurrentSession().merge(entity)).getId();
    }

    @Override
    public TaskType getById(Integer id) {
        return (TaskType) sessionFactory.getCurrentSession().load(TaskType.class, id);
    }

    @Override
    public void delete(Integer id) {
        sessionFactory.getCurrentSession().delete(getById(id));
    }

    @Override
    public Iterable<TaskType> list() {
        return sessionFactory.getCurrentSession().createCriteria(TaskType.class)
                .addOrder(Order.asc("id")).list();
    }

}
