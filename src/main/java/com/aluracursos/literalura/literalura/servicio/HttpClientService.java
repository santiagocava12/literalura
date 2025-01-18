package com.aluracursos.literalura.literalura.servicio;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class HttpClientService {

    private static final String BASE_URL = "https://gutendex.com/books?search=";

    public JsonNode buscarLibroPorTitulo(String titulo) {
        try {
            // Construcción de la URL
            String url = BASE_URL + titulo.replace(" ", "%20");
            System.out.println("URL generada para la solicitud: " + url);

            // Configurar el cliente HTTP con redirección habilitada
            HttpClient client = HttpClient.newBuilder()
                    .followRedirects(HttpClient.Redirect.NORMAL)
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            // Enviar la solicitud
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de respuesta HTTP: " + response.statusCode());
            System.out.println("Respuesta de la API: " + response.body());

            // Validar respuesta HTTP
            if (response.statusCode() != 200) {
                System.out.println("Error: La API devolvió un código de estado HTTP " + response.statusCode());
                return null;
            }

            // Validar cuerpo de la respuesta
            if (response.body().isEmpty()) {
                System.out.println("Error: La respuesta de la API está vacía.");
                return null;
            }

            // Parsear el cuerpo de la respuesta
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response.body());

            if (root.has("results") && root.get("results").isArray() && root.get("results").size() > 0) {
                return root.get("results").get(0); // Retornar el primer resultado
            } else {
                System.out.println("La API no devolvió resultados para el título proporcionado.");
                return null;
            }

        } catch (Exception e) {
            System.out.println("Error al realizar la solicitud HTTP: " + e.getMessage());
            return null;
        }
    }


}
