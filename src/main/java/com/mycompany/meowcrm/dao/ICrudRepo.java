package com.mycompany.meowcrm.dao;

import java.io.Serializable;

/**
 *
 * @author wolf
 * @param <T> - entity type
 * @param <ID> - entity id type
 */
public interface ICrudRepo<T, ID extends Serializable> {

    public ID add(T entity);

    public T getById(ID id);

    public void delete(ID id);
}
