package com.pharmavida.controller;

import com.pharmavida.model.entity.Medicamento;
import com.pharmavida.service.CategoryService;
import com.pharmavida.service.MedicamentoService;
import com.pharmavida.service.UnitOfMeasureService;
import com.pharmavida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final CategoryService categoryService;
    private final UnitOfMeasureService unitOfMeasureService;
    private final UserService userService;

    public MedicamentosController(MedicamentoService medicamentoService,
                                  CategoryService categoryService,
                                  UnitOfMeasureService unitOfMeasureService,
                                  UserService userService) {
        this.medicamentoService = medicamentoService;
        this.categoryService = categoryService;
        this.unitOfMeasureService = unitOfMeasureService;
        this.userService = userService;
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
        model.addAttribute("categorias", categoryService.getAllCategories());
        model.addAttribute("unidadesDeMedida", unitOfMeasureService.getAllUnitsOfMeasures());
        model.addAttribute("fragment", "/fragments/medications-frag");
        model.addAttribute("fragment_id", "medications-frag");

        return "XMLHttpRequest".equals(requestedWith) ? "/fragments/medications-frag" : "layout";
    }

    @PostMapping("/add")
    public String addMedication(@ModelAttribute("medicamento") Medicamento medicamento, @RequestHeader(value = "X-Requested-With", required = true) String requestedWith) {
        if (!"XMLHttpRequest".equals(requestedWith)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found");
        }
        medicamentoService.createMedication(medicamento);
        return "redirect:/medications";
    }

    @PostMapping("/comprar/{id}")
    public ResponseEntity<String> comprarMedicamento(@PathVariable Long id) {
        boolean exito = medicamentoService.comprarMedicamento(id);
        if (exito) {
            return ResponseEntity.ok("Compra realizada exitosamente.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo realizar la compra.");
        }
    }


    @PostMapping("/carrito/{id}")
    public ResponseEntity<String> agregarAlCarrito(@PathVariable Long id) {
        boolean exito = medicamentoService.agregarAlCarrito(id);
        if (exito) {
            return ResponseEntity.ok("Medicamento añadido al carrito.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al añadir el medicamento al carrito.");
        }
    }



}
