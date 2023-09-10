package com.edge_academy.client.http;

public class HttpRequestException extends Exception {
    public HttpRequestException() { super(); }
    public HttpRequestException(int responseCode) {
        super("Falha na solicitação: Código de status " + responseCode);
    }
}
