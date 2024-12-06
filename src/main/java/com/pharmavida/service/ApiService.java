package com.pharmavida.service;

import com.pharmavida.model.entity.Medicamento;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

@Service
public class ApiService {

    private final RestTemplate restTemplate;

    public ApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Obtener todos los medicamentos
    public List<Medicamento> getAllMedicamentos() {
        String url = "https://alexsandrovs.pythonanywhere.com/api/v1/productos/";  // Nueva URL de la API

        // Usar exchange para obtener la respuesta como una lista de objetos Medicamento
        ResponseEntity<List<Medicamento>> response = restTemplate.exchange(
                url,
                org.springframework.http.HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Medicamento>>() {}
        );

        return response.getBody();
    }

    // Buscar medicamento por nombre
    public Medicamento getMedicamentoByName(String medicamentoName) {
        String url = "https://alexsandrovs.pythonanywhere.com/api/v1/productos/search?medicamentoName=" + medicamentoName;
        return restTemplate.getForObject(url, Medicamento.class);
    }
}
