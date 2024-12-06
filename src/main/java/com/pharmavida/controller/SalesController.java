package com.pharmavida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sales")
public class SalesController {

    @GetMapping
    public String showSales(Model model,
                            @RequestHeader(value = "X-Requested-With", required = false) String requestedWith,
                            @RequestParam(required = false) String id,
                            @RequestParam(required = false) String nombre,
                            @RequestParam(required = false) String precio,
                            @RequestParam(required = false) String stock) {

        // Agregar parámetros al modelo
        model.addAttribute("id", id);
        model.addAttribute("nombre", nombre);
        model.addAttribute("precio", precio);
        model.addAttribute("stock", stock);

        // Verificar si es una solicitud AJAX o normal
        if ("XMLHttpRequest".equals(requestedWith)) {
            model.addAttribute("fragment", "/fragments/sales-frag");
            model.addAttribute("fragment_id", "sales-frag");
            return "/fragments/sales-frag"; // Solo el fragmento en respuesta AJAX
        }

        return "layout"; // Vista principal cuando no es AJAX
    }

    @GetMapping("/ajax")
    public String handleAjaxSales(@RequestParam String id,
                                  @RequestParam String nombre,
                                  @RequestParam String precio,
                                  @RequestParam String stock,
                                  Model model) {
        model.addAttribute("id", id);
        model.addAttribute("nombre", nombre);
        model.addAttribute("precio", precio);
        model.addAttribute("stock", stock);
        return "/fragments/sales-frag";  // Este es el fragmento que se devolverá
    }

}
