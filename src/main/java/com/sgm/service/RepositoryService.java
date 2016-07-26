/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @author ism
 */
public interface RepositoryService {

    <T> T create(T klass);

    <T> void edit(T klass);

    <T> void delete(T klass);

    <T> T find(Class<T> klass, Object id);
    
    <T> boolean exist(T klass);

    <T> List<T> findAll(Class<T> klass);
    
    <T> T GetUniqueEntityByNamedQuery(String query, Object... params);
    
    <T> List GetEntitiesByNamedQuery(String query, Object... params);
    
    <T> List<T> findByJPQuery(String hql,Map<String, Object> namedParams);
    
    <T> int rowsUpdateQuery(String hql,Map<String, Object> namedParams);
}
