package com.edge_academy.compression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.InflaterInputStream;

public class Decompressor {
    private ByteArrayOutputStream outputStream;
    private byte[] compressedData;

    public Decompressor(byte[] data) {
        this.compressedData = data;
        outputStream = new ByteArrayOutputStream();
    }

    public byte[] decompress() throws RuntimeException {
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(compressedData);
             InflaterInputStream inflaterInputStream = new InflaterInputStream(inputStream);
             ByteArrayOutputStream decompressedOutput = new ByteArrayOutputStream()) {

            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = inflaterInputStream.read(buffer)) != -1) {
                decompressedOutput.write(buffer, 0, bytesRead);
            }

            return decompressedOutput.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
