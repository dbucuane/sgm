/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ism
 */
@Repository
public class RepositoryImpl implements RepositoryDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public <T> T save(T klass) {
        em.persist(klass);
        return klass;
    }

    @Override
    public <T> void update(T klass) {
        em.merge(klass);
    }

    @Override
    public <T> void delete(T klass) {
        em.remove(em.merge(klass));
    }

    @Override
    public <T> T find(Class<T> klass, Object id) {
        return em.find(klass, id);
    }

    @Override
    public <T> List<T> findAll(Class<T> klass) {
        javax.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(klass));
        return em.createQuery(cq).getResultList();
    }

    @Override
    public <T> boolean exist(T klass) {
        return em.contains(klass);
    }

    @Override
    public <T> T GetUniqueEntityByNamedQuery(String query, Object... params) {
        Query q = em.createNamedQuery(query);

        int i = 0;

        for (Parameter param : q.getParameters()) {
            q.setParameter(param, params[i]);
            i++;
        }

        List<T> results = q.getResultList();

        T foundentity = null;

        if (!results.isEmpty()) {
            foundentity = results.get(0);
        }

        return foundentity;
    }

    @Override
    public <T> List GetEntitiesByNamedQuery(String query, Object... params) {
        Query q = em.createNamedQuery(query);

        int i = 0;

        for (Parameter param : q.getParameters()) {
            q.setParameter(param, params[0]);
            i++;
        }

        List<T> results = q.getResultList();

        if (!results.isEmpty()) {
            return results;
        }

        return null;
    }

    @Override
    public <T> List<T> findByQuery(String hql, Map<String, Object> entidade, Map<String, Object> namedParams) {
        Query query = em.createQuery(hql, null);
        if (entidade != null) {
            Map.Entry mapEntry;
//            for (Iterator it = entidade.entrySet().iterator(); it
//                    .hasNext(); query.addEntity(
//                            (String) mapEntry.getKey(), (Class) mapEntry.getValue())) {
//                mapEntry = (Map.Entry) it.next();
//            }
        }
        if (namedParams != null) {
            Map.Entry mapEntry;
            for (Iterator it = namedParams.entrySet().iterator(); it
                    .hasNext(); query.setParameter(
                            (String) mapEntry.getKey(), mapEntry.getValue())) {
                mapEntry = (Map.Entry) it.next();
            }
        }
        List returnList = query.getResultList();

        return returnList;
    }

    @Override
    public <T> List findByJPQuery(String hql, Map<String, Object> namedParams) {
        Query query = em.createQuery(hql);
        if (namedParams != null) {
            Entry mapEntry;
            for (Iterator it = namedParams.entrySet().iterator(); it
                    .hasNext(); query.setParameter(
                            (String) mapEntry.getKey(), mapEntry.getValue())) {
                mapEntry = (Entry) it.next();
            }
        }
        List returnList = query.getResultList();

        return returnList;
    }

    @Override
    public <T> int rowsUpdateQuery(String hql, Map<String, Object> namedParams) {
        
        Query query = em.createQuery(hql);
        if (namedParams != null) {
            Entry mapEntry;
            for (Iterator it = namedParams.entrySet().iterator(); it
                    .hasNext(); query.setParameter(
                            (String) mapEntry.getKey(), mapEntry.getValue())) {
                mapEntry = (Entry) it.next();
            }

        }
        int result = query.executeUpdate();
        return result;

    }

}

    
