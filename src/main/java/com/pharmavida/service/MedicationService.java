package com.pharmavida.service;

import com.pharmavida.model.entity.Medication;

import java.util.List;

public interface MedicationService {
    Medication getMedicationById(Long id);
    List<Medication> getAllMedications();
    void createMedication(Medication medication);
    void updateMedication(Medication medication);
    void deleteMedication(Long id);
    Medication getMedicationByName(String name);
    List<Medication> searchMedications(String name, String categoryCode);
}
