/**
 * Este programa do lado do servidor demonstra a funcionalidade de receber, descompactar e descriptografar
 * dados enviados por um cliente usando um servidor de soquete. Ele aguarda conexões de clientes,
 * recebe dados compactados, descomprime esses dados e, em seguida, descriptografa os dados antes
 * de exibi-los no console.
 *
 * O programa utiliza as classes Server, Decompressor e Decrypt para receber, descompactar e descriptografar
 * os dados, respectivamente.
 *
 * @author Ricardo Vinicius
 * @version 1.0
 */

package com.edge_academy.server;

import com.edge_academy.AesKey;
import com.edge_academy.client.socket.Server;
import com.edge_academy.compression.Decompressor;
import com.edge_academy.cryptography.Decrypt;

import java.io.IOException;

public class Main {
    // Tamanho máximo dos dados a serem recebidos (32 KB)
    static private final int MAX_DATA_SIZE = 32 * 1024;

    public static void main(String[] args) {
        // Inicialização do servidor de socket
        Server server = new Server();
        server.init();

        while (true) {
            // Aguarda uma conexão de cliente
            server.waitForClientConnection();

            byte[] data = new byte[0];
            try {
                // Recebe dados do cliente
                data = server.receiveData();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Descomprime os dados recebidos
            Decompressor decompressor = new Decompressor(data);
            byte[] decompressedJson = decompressor.decompress();
            String stringDecompressedJson = new String(decompressedJson);

            String decryptedJson = null;
            try {
                // Descriptografa os dados
                decryptedJson = Decrypt.decryptString(stringDecompressedJson, AesKey.AES_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Exibe os dados descriptografados no console
            System.out.println(decryptedJson);

            try {
                // Encerra a conexão com o cliente
                server.closeClientConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
