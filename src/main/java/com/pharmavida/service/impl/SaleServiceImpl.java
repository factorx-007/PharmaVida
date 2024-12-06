package com.pharmavida.service.impl;

import com.pharmavida.dao.SaleDAO;
import com.pharmavida.model.entity.Medication;
import com.pharmavida.model.entity.Sale;
import com.pharmavida.model.entity.SalesItem;
import com.pharmavida.service.MedicationService;
import com.pharmavida.service.SaleService;
import com.pharmavida.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private SaleDAO saleDAO;
    private MedicationService medicationService;
    private UserService userService;

    @Autowired
    public SaleServiceImpl(SaleDAO saleDAO, MedicationService medicationService, UserService userService) {
        this.saleDAO = saleDAO;
        this.medicationService = medicationService;
        this.userService = userService;
    }

    @Override
    public Sale getSaleById(Long id) {
        return saleDAO.findById(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return saleDAO.findAll();
    }

    @Override
    @Transactional
    public Sale createSale(Sale salesHeader) {
        // TODO: this method would make much more sense when we use DTOs
        salesHeader.setCreatedBy(userService.getLoggedInUsername());
        salesHeader.setLastUpdateBy("-1");
        salesHeader.setCreationDate(new Date());
        salesHeader.setLastUpdateDate(new Date());
        for (SalesItem salesItem : salesHeader.getSalesItems()) {
            salesItem.setCreatedBy(userService.getLoggedInUsername());
            salesItem.setLastUpdateBy("-1");
            salesItem.setCreationDate(new Date());
            salesItem.setLastUpdateDate(new Date());
            salesItem.setSale(salesHeader);

            Medication medication = medicationService.getMedicationById(salesItem.getMedication().getId());
            salesItem.setMedication(medication);
        }

        saleDAO.save(salesHeader);

        return salesHeader;
    }

    @Override
    @Transactional
    public Sale updateSale(Sale salesHeader) {
        salesHeader.setLastUpdateBy(userService.getLoggedInUsername());
        salesHeader.setLastUpdateDate(new Date());
        saleDAO.save(salesHeader);
        return salesHeader;
    }

    @Override
    @Transactional
    public void deleteSale(Long id) {
        saleDAO.deleteById(id);
    }

}
