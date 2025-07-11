import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ConsumoApi {

    private static final String API_KEY = "a80c6c802ab63a985e54ca9b";

    // Consulta la tasa de cambio entre dos monedas
    public static double obtenerTasaCambio(String monedaOrigen, String monedaDestino) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaOrigen;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) return -1;

        // Extrae la tasa de cambio desde el JSON de respuesta
        JsonObject json = JsonParser.parseString(response.body()).getAsJsonObject();
        JsonObject rates = json.getAsJsonObject("conversion_rates");

        if (!rates.has(monedaDestino)) return -1;

        return rates.get(monedaDestino).getAsDouble();
    }
}
