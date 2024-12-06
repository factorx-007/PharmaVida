package com.pharmavida.util;

import com.pharmavida.model.entity.UnitOfMeasure;
import com.pharmavida.service.UnitOfMeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUnitOfMeasureConverter implements Converter<String, UnitOfMeasure> {

    private final UnitOfMeasureService unitOfMeasureService;

    @Autowired
    public StringToUnitOfMeasureConverter(UnitOfMeasureService unitOfMeasureService) {
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @Override
    public UnitOfMeasure convert(String source) {
        return unitOfMeasureService.getUnitOfMeasureByCode(source);
    }
}
