package com.pharmavida.dao;

import com.pharmavida.model.entity.SalesItem;

import java.util.List;

public interface SalesItemDAO {

    public void save(SalesItem s);
    public void saveAll(List<SalesItem> salesItems);
    public SalesItem findById(Long id);
    public List<SalesItem> findAll(List<Long> id);
    public List<SalesItem> findAll();
    public boolean existsById(Long id);
    public int count();
    public void deleteById(Long id);
    public void delete(SalesItem s);
    public void deleteAll(List<SalesItem> salesItems);
    public void deleteAll();


}
