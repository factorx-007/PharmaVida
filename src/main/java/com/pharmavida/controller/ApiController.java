package com.pharmavida.controller;

import com.pharmavida.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ApiController {
    private final ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    /* Endpoint para mostrar los clientes
    @GetMapping("/clientes")
    public List<Map<String, Object>> fetchClientes() {
        return apiService.getClientes();
    }

    // Endpoint para mostrar los productos
    @GetMapping("/productos")
    public List<Map<String, Object>> fetchProductos() {
        return apiService.getProductos();
    }*/
}