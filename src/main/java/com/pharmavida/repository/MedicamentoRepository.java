package com.pharmavida.repository;

import com.pharmavida.model.entity.Medicamento;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento, Long> {

}
