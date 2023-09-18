package com.edge_academy.client.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A classe ApiConnections é responsável por fazer solicitações HTTP para uma API e recuperar o JSON de resposta.
 */
public class ApiConnections {
    private String apiUrl;

    /**
     * Cria uma nova instância de ApiConnections com a URL da API especificada.
     *
     * @param apiUrl A URL da API que será consultada.
     */
    public ApiConnections(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * Realiza uma solicitação HTTP GET à API e retorna a resposta em formato JSON.
     *
     * @return A resposta JSON da API como uma string.
     * @throws HttpRequestException Se ocorrer um erro ao fazer a solicitação HTTP.
     * @throws IOException          Se ocorrer um erro de E/S durante a solicitação.
     */
    public String requestJson(String endPoint) throws HttpRequestException, IOException {
        HttpURLConnection connection = null;

        try {
            URL url = new URL(apiUrl + endPoint);

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // Verifica se a resposta retorna um status 200 (OK)
            if (connection.getResponseCode() != 200) {
                throw new HttpRequestException(connection.getResponseCode());
            }

            /*
            Lê a stream de dados retornada pela API em um buffer
            e adiciona em um StringBuilder para ser utilizado depois
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder jsonResponse = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                jsonResponse.append(line);
            }

            reader.close();

            return jsonResponse.toString();
        } catch (IOException e) {
            throw e;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
}
