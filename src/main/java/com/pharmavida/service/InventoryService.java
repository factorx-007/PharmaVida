package com.pharmavida.service;

import com.pharmavida.observer.Inventory;
import com.pharmavida.observer.PharmacyStaff;

public class InventoryService {
    private Inventory inventory;

    public InventoryService() {
        inventory = new Inventory();
        inventory.addObserver(new PharmacyStaff());
    }

    public void updateStock(int newStock) {
        inventory.setStock(newStock);
    }
}
