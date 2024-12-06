package com.pharmavida.repository;

import com.pharmavida.model.entity.Carrito;
import com.pharmavida.model.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    Optional<Carrito> findByMedicamento(Medicamento medicamento);
    // Métodos personalizados si son necesarios
}

