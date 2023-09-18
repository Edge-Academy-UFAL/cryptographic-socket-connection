/**
 * Este programa demonstra a funcionalidade de envio de dados criptografados e compactados
 * para um servidor usando um cliente de soquete. Ele permite que o usuário escolha o tipo
 * de dado a ser enviado (usuário, endereço, banco ou cerveja) e, em seguida, busca dados
 * aleatórios da API, criptografa e compacta esses dados e, finalmente, envia para o servidor
 * usando um cliente de soquete.
 *
 * O programa utiliza as classes ApiConnections, Encrypt e Compressor para buscar, criptografar
 * e compactar os dados, respectivamente.
 *
 * @author Ricardo Vinicius
 * @version 1.0
 */

package com.edge_academy.client;

import com.edge_academy.client.http.ApiConnections;
import com.edge_academy.client.socket.Client;
import com.edge_academy.compression.Compressor;
import com.edge_academy.cryptography.Encrypt;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.edge_academy.AesKey.AES_KEY;

public class Main {
    // URL da API para buscar dados aleatórios
    static final String API_URL = "https://random-data-api.com/api/v2";

    public static void main(String[] args) throws Exception {
        // Inicialização das variáveis e objetos necessários
        ApiConnections randomJson = new ApiConnections(API_URL);
        String json = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int option = -1;
            String endPoint = "";

            System.out.println("Escolha o tipo de dado a enviar: ");
            System.out.println("""
                    1 - User
                    2 - Address
                    3 - Bank
                    4 - Beer""");
            option = getInputOption(scanner);

            switch(option) {
                case 1:
                    endPoint = "/users";
                    break;
                case 2:
                    endPoint = "/addresses";
                    break;
                case 3:
                    endPoint = "/banks";
                    break;
                case 4:
                    endPoint = "/beers";
                    break;
            }

            try {
                // Busca dados aleatórios da API
                json = randomJson.requestJson(endPoint);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Criptografa os dados
            String encryptedJson = Encrypt.encryptString(json, AES_KEY);

            // Compacta os dados
            Compressor compressor = new Compressor(encryptedJson.getBytes());
            byte[] compressedJson = compressor.compress();

            // Inicializa o cliente de soquete e envia os dados
            Client client = new Client();

            try {
                client.init();
                client.sendData(compressedJson);
                client.close();
                System.out.println("Dado enviado com sucesso");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Solicita ao usuário que escolha uma opção de 1 a 4 e verifica se a entrada do usuário é válida.
     *
     * @param scanner O objeto Scanner usado para ler a entrada do usuário.
     * @return A opção selecionada pelo usuário.
     */
    private static int getInputOption(Scanner scanner) {
        while (true) {
            try {
                int option = scanner.nextInt();

                if (option >= 1 && option <= 4) {
                    return option;
                } else {
                    System.out.println("Digite um número entre 1 e 4 como opção");
                }
            } catch (InputMismatchException e) {
                System.out.println("Digite um número inteiro como opção");
                scanner.nextLine();
            }
        }
    }
}
