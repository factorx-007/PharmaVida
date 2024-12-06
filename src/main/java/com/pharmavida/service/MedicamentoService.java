package com.pharmavida.service;

import com.pharmavida.model.entity.Carrito;
import com.pharmavida.model.entity.Medicamento;
import com.pharmavida.repository.MedicamentoRepository;
import com.pharmavida.repository.CarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedicamentoService {

    @Autowired
    private ApiService apiService;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private CarritoRepository carritoRepository;

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

    // Método para crear un medicamento
    public Medicamento createMedication(Medicamento medicamento) {
        if (medicamento == null) {
            throw new IllegalArgumentException("El medicamento no puede ser nulo");
        }
        return medicamentoRepository.save(medicamento);
    }

    // Método para comprar medicamento
    public boolean comprarMedicamento(Long id) {
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(id);
        if (medicamentoOpt.isPresent()) {
            Medicamento medicamento = medicamentoOpt.get();
            if (medicamento.getStock() > 0) {
                medicamento.setStock(medicamento.getStock() - 1);  // Disminuir el stock
                medicamentoRepository.save(medicamento);
                return true;  // Compra exitosa
            } else {
                // Si no hay stock, lanzar excepción
                throw new IllegalStateException("No hay suficiente stock para realizar la compra.");
            }
        }
        return false;  // Si no se encuentra el medicamento
    }

    // Método para agregar al carrito
    public boolean agregarAlCarrito(Long id) {
        Optional<Medicamento> medicamentoOpt = medicamentoRepository.findById(id);
        if (medicamentoOpt.isPresent()) {
            Medicamento medicamento = medicamentoOpt.get();
            Optional<Carrito> carritoOpt = carritoRepository.findByMedicamento(medicamento);  // Buscar si ya está en el carrito

            if (carritoOpt.isPresent()) {
                Carrito carrito = carritoOpt.get();
                carrito.setCantidad(carrito.getCantidad() + 1);  // Aumentar la cantidad en el carrito
                carritoRepository.save(carrito);  // Guardar el carrito actualizado
            } else {
                // Si no está en el carrito, agregarlo con cantidad 1
                Carrito carrito = new Carrito();
                carrito.setMedicamento(medicamento);
                carrito.setCantidad(1);  // Agregar 1 unidad
                carritoRepository.save(carrito);
            }
            return true;  // Medicamento agregado al carrito
        }
        return false;  // Si no se encuentra el medicamento
    }
}
