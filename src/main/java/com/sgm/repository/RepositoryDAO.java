/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.repository;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ism
 */
public interface RepositoryDAO {

    <T> T save(T klass);

    <T> void update(T klass);

    <T> void delete(T klass);

    <T> T find(Class<T> klass, Object id);

    <T> List<T> findAll(Class<T> klass);

    <T> boolean exist(T klass);
    
    <T> T GetUniqueEntityByNamedQuery(String query, Object... params);
    
    <T> List GetEntitiesByNamedQuery(String query, Object... params);
    
    <T> List<T> findByQuery(String hql, Map<String, Object> entidade, Map<String, Object> namedParams);
    
    <T> List findByJPQuery(String hql,Map<String, Object> namedParams);
    
    <T> int rowsUpdateQuery(String hql,Map<String, Object> namedParams);
}
