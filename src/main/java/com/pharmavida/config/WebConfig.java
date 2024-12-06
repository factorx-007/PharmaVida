package com.pharmavida.config;

import com.pharmavida.util.StringToCategoryConverter;
import com.pharmavida.util.StringToDateConverter;
import com.pharmavida.util.StringToUnitOfMeasureConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private StringToUnitOfMeasureConverter stringToUnitOfMeasureConverter;
    private StringToCategoryConverter stringToCategoryConverter;
    private StringToDateConverter stringToDateConverter;

    @Autowired
    public WebConfig(StringToUnitOfMeasureConverter stringToUnitOfMeasureConverter, StringToCategoryConverter stringToCategoryConverter, StringToDateConverter stringToDateConverter) {
        this.stringToUnitOfMeasureConverter = stringToUnitOfMeasureConverter;
        this.stringToCategoryConverter = stringToCategoryConverter;
        this.stringToDateConverter = stringToDateConverter;
    }

    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081") // Asegúrate de que este sea el puerto de tu frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUnitOfMeasureConverter);
        registry.addConverter(stringToCategoryConverter);
        registry.addConverter(stringToDateConverter);
    }
}
