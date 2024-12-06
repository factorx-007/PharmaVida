package com.pharmavida.controller;

import com.pharmavida.service.InventoryService;
import com.pharmavida.service.ShoppingCartService;
import org.springframework.stereotype.Controller;

@Controller
public class PharmacyController {
    private InventoryService inventoryService;
    private ShoppingCartService cartService;

    public PharmacyController() {
        inventoryService = new InventoryService();
        cartService = new ShoppingCartService();
    }
}

