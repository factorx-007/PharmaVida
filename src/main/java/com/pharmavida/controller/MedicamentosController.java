package com.pharmavida.controller;

import com.pharmavida.model.entity.Medicamento;
import com.pharmavida.service.MedicamentoService;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/medications")
public class MedicamentosController {

    private final MedicamentoService medicamentoService;

    public MedicamentosController(MedicamentoService medicamentoService) {
        this.medicamentoService = medicamentoService;

    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping
    public String mostrarTodosLosMedicamentos(Model model, @RequestHeader(value = "X-Requested-With", required = false) String requestedWith) {
        model.addAttribute("medicamento", new Medicamento());
        model.addAttribute("medicamentos", medicamentoService.findAll()); // Método corregido
        model.addAttribute("fragment", "/fragments/medications-frag");
        model.addAttribute("fragment_id", "medications-frag");

        return "XMLHttpRequest".equals(requestedWith) ? "/fragments/medications-frag" : "layout";
    }

}
