package com.nmcp.tech.casesmanagement.services;

import com.nmcp.tech.casesmanagement.data.common.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;

/**
 * Created by Hamza on 2019-02-15.
 */
public interface CrudService<T extends AbstractEntity> {

    JpaRepository<T, Long> getRepository();

    default T save(/*User currentUser,*/ T entity) {
        return getRepository().saveAndFlush(entity);
    }

    default void delete(/*User currentUser,*/ T entity) {
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        getRepository().delete(entity);
    }

    default void delete(/*User currentUser,*/ long id) {
        delete(/*currentUser,*/ load(id));
    }

    default long count() {
        return getRepository().count();
    }

    default T load(long id) {
        T entity = getRepository().findById(id).orElse(null);
        if (entity == null) {
            throw new EntityNotFoundException();
        }
        return entity;
    }


//    T createNew(User currentUser);
}
