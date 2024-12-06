package com.pharmavida.service;

import com.pharmavida.model.entity.Sale;

import java.util.List;

public interface SaleService {
    // sales headers
    Sale getSaleById(Long id);
    List<Sale> getAllSales();
    Sale createSale(Sale salesHeader);
    Sale updateSale(Sale salesHeader);
    void deleteSale(Long id);
}
