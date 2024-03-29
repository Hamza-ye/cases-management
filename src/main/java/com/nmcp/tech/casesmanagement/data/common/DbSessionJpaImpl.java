package com.nmcp.tech.casesmanagement.data.common;

import fri.util.database.jpa.commons.DbSession;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * <i>JPA</i> implementation of <code>DbSession</code>.
 *
 * @author Fritz Ritzberger, 2013-08-19
 */
public class DbSessionJpaImpl implements DbSession {

    private EntityManager entityManager;

    public DbSessionJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Object get(Class<?> entityClass, Serializable id) {
        return entityManager.find(entityClass, id);    // TODO: better use getReference(entityClass, teamId) ?
    }

    @Override
    public Object save(Object node) {
        return entityManager.merge(node);
    }

    @Override
    public void flush() {
        entityManager.flush();
    }

    @Override
    public void refresh(Object node) {
        entityManager.refresh(node);
    }

    @Override
    public void delete(Object node) {
        entityManager.remove(node);
    }

    @Override
    public List<?> queryList(String queryText, Object[] parameters) {
        Query query = query(queryText, parameters);
        return query.getResultList();
    }

    @Override
    public int queryCount(String queryText, Object[] parameters) {
        @SuppressWarnings("rawtypes")
        List result = queryList(queryText, parameters);
        return ((Number) result.get(0)).intValue();
    }

//    @PersistenceContext
//    public void setEntityManager(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }

    @Override
    public void executeUpdate(String sqlCommand, Object[] parameters) {
        Query query = query(sqlCommand, parameters);
        query.executeUpdate();
    }

    private Query query(String queryText, Object[] parameters) {
        Query query = entityManager.createQuery(queryText);
        if (parameters != null) {
            int i = 1;
            for (Object parameter : parameters) {
                if (parameter == null)
                    throw new IllegalArgumentException("Binding parameter at position " + i + " can not be null: " + queryText);

                query.setParameter(i, parameter);
                i++;
            }
        }
        return query;
    }

    /**
     * Do not use. Convenience method for unit tests.
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }


}
