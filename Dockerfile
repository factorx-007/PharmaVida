# Usa una imagen base con Maven y Java preinstalados
FROM maven:3.8.7-eclipse-temurin-17

# Configura el directorio de trabajo en el contenedor
WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
COPY . .

# Construye el proyecto con Maven
RUN mvn clean package

# Exponer el puerto donde correrá la aplicación (8080 por defecto)
EXPOSE 8084

# Comando para ejecutar el archivo .jar de la aplicación
CMD ["java", "-jar", "target/pharmacyms-0.0.1-SNAPSHOT.jar"]
