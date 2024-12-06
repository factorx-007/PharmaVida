package com.pharmavida.controller;

import com.pharmavida.paypal.PayPalAuth;
import com.pharmavida.paypal.PayPalOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/paypal")
public class PayPalController {

    /*@PostMapping("/create-order")
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody Map<String, Object> orderData) {
        try {
            // Llama a la API de PayPal para crear un pedido (como en el paso anterior)
            String accessToken = PayPalAuth.getAccessToken();  // Obtén el token de acceso
            String orderResponse = PayPalOrder.createOrder(accessToken, orderData);  // Crea el pedido y obtiene la respuesta

            // Parsear la respuesta de PayPal para obtener el enlace de aprobación
            JSONObject responseJson = new JSONObject(orderResponse);
            String approvalLink = responseJson.getJSONArray("links")
                    .toList()
                    .stream()
                    .filter(link -> ((Map) link).get("rel").equals("approve"))
                    .map(link -> ((Map) link).get("href").toString())
                    .findFirst()
                    .orElse("");

            // Retorna el enlace de aprobación al frontend
            Map<String, Object> response = new HashMap<>();
            response.put("links", approvalLink);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error", "Error al crear el pedido"));
        }

    }*/
}
