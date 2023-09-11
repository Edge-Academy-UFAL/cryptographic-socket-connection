package com.edge_academy.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

public class Compressor {
    // TODO: Fazer os métodos para comprimir o stream de bytes
    private ByteArrayOutputStream outputStream;
    private byte[] originalData;

    public Compressor(byte[] data) {
         this.originalData = data;
         outputStream = new ByteArrayOutputStream();
    }

    public byte[] compress() throws RuntimeException {
        try (DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(outputStream, new Deflater(Deflater.HUFFMAN_ONLY))) {
            deflaterOutputStream.write(originalData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        byte[] compressedData = outputStream.toByteArray();

        return compressedData;
    }


}
