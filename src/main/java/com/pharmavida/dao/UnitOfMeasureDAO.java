package com.pharmavida.dao;

import com.pharmavida.model.entity.UnitOfMeasure;

import java.util.List;

public interface UnitOfMeasureDAO {

    public void save(UnitOfMeasure uom);
    public void saveAll(List<UnitOfMeasure> UnitOfMeasures);
    public UnitOfMeasure findById(Long id);
    public UnitOfMeasure findByCode(String code);
    public List<UnitOfMeasure> findAll(List<Long> id);
    public List<UnitOfMeasure> findAll();
    public boolean existsById(Long id);
    public boolean existsByCode(String code);
    public int count();
    public void deleteById(Long id);
    public void delete(UnitOfMeasure uom);
    public void deleteAll(List<UnitOfMeasure> unitOfMeasures);
    public void deleteAll();

}
