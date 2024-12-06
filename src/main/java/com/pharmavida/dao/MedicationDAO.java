package com.pharmavida.dao;

import com.pharmavida.model.entity.Medication;

import java.util.List;

public interface MedicationDAO {

    public void save(Medication m);
    public void saveAll(List<Medication> medications);
    public Medication findById(Long id);
    public Medication findByName(String name);
    public List<Medication> findAll(List<Long> ids);
    public List<Medication> findAll();
    public boolean existsById(Long id);
    public boolean existsByName(String name);
    public int count();
    public void deleteById(Long id);
    public void delete(Medication m);
    public void deleteAll(List<Medication> medications);
    public void deleteAll();

    // query methods
    public List<Medication> queryMedications(String name, String categoryCode);

}
