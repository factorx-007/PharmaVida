package com.pharmavida.service;

import com.pharmavida.model.entity.Medicamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentoService {

    @Autowired
    private ApiService apiService;

    // Obtener todos los medicamentos desde el API
    public List<Medicamento> findAll() {
        List<Medicamento> medicamentos = apiService.getAllMedicamentos();
        if (medicamentos == null) {
            throw new IllegalStateException("La lista de medicamentos no puede ser nula");
        }
        return medicamentos;
    }

    // Obtener un medicamento por su nombre desde el API
    public Medicamento getMedicamentoByName(String medicamentoName) {
        Medicamento medicamento = apiService.getMedicamentoByName(medicamentoName);
        if (medicamento == null) {
            throw new IllegalStateException("El medicamento no puede ser nulo");
        }
        return medicamento;
    }






}
