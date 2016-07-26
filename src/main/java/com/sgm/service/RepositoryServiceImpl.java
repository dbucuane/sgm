/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sgm.service;



import com.sgm.repository.RepositoryDAO;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ism
 */
@Service
@Transactional
public class RepositoryServiceImpl implements RepositoryService {

    @Autowired
    private RepositoryDAO repositoryDAO;

    @Override
    public <T> T create(T klass) {
        if (klass != null) {
            repositoryDAO.save(klass);
        }
        return klass;
    }

    @Override
    public <T> void edit(T klass) {
        repositoryDAO.update(klass);
    }

    @Override
    public <T> void delete(T klass) {
        repositoryDAO.delete(klass);
    }

    @Override
    public <T> T find(Class<T> klass, Object id) {
        return repositoryDAO.find(klass, id);
    }

    @Override
    public <T> boolean exist(T klass) {
        return repositoryDAO.exist(klass);
    }
    
    @Override
    public <T> List<T> findAll(Class<T> klass) {
        return repositoryDAO.findAll(klass);
    }

    @Override
    public <T> T GetUniqueEntityByNamedQuery(String query, Object... params) {
        return repositoryDAO.GetUniqueEntityByNamedQuery(query, params);
    }

    @Override
    public <T> List GetEntitiesByNamedQuery(String query, Object... params) {
        return repositoryDAO.GetEntitiesByNamedQuery(query, params);
    }

    @Override
    public <T> List findByJPQuery(String hql, Map<String, Object> namedParams) {
        return repositoryDAO.findByJPQuery(hql, namedParams);
    }

    @Override
    public <T> int rowsUpdateQuery(String hql, Map<String, Object> namedParams) {
        return repositoryDAO.rowsUpdateQuery(hql, namedParams);
    }

    
}
