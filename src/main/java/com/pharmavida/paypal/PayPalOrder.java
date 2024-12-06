package com.pharmavida.paypal;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PayPalOrder {

    private static final String ACCESS_TOKEN = "YOUR_ACCESS_TOKEN";  // Token obtenido anteriormente
    private static final String API_URL = "https://api-m.sandbox.paypal.com/v2/checkout/orders";

    public static String createOrder() throws Exception {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Bearer " + ACCESS_TOKEN);
        connection.setRequestProperty("Content-Type", "application/json");

        String body = "{\n" +
                "  \"intent\": \"CAPTURE\",\n" +
                "  \"purchase_units\": [\n" +
                "    {\n" +
                "      \"amount\": {\n" +
                "        \"currency_code\": \"USD\",\n" +
                "        \"value\": \"100.00\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = body.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (InputStreamReader reader = new InputStreamReader(connection.getInputStream())) {
            StringBuilder response = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                response.append((char) ch);
            }
            // Parse response to get order details
            return response.toString();  // Aquí deberías parsear la respuesta JSON para obtener detalles del pedido
        }
    }
}
