package com.tomclaw.lzw;

import java.io.*;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {
        try {
            ByteArrayInputStream input;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            File in = new File("/Users/solkin/Desktop/app-info.json");
            byte[] data = Files.readAllBytes(in.toPath());
            System.out.println(data.length + " bytes, (original)");

            bytes.reset();
            LZWOutputStream lzw = new LZWOutputStream(bytes);
            lzw.write(data);
            lzw.flush();
            lzw.close();
            System.out.println(bytes.size() + " bytes (encoded)");
            File out = new File("/Users/solkin/Desktop/app-info.lzw");
            OutputStream fos = new FileOutputStream(out);
            fos.write(bytes.toByteArray());
            fos.flush();
            fos.close();

            input = new ByteArrayInputStream(bytes.toByteArray());
            bytes.reset();

            LZWInputStream lzwInputStream = new LZWInputStream(input);
            int read;
            ByteArrayOutputStream sb = new ByteArrayOutputStream();
            while ((read = lzwInputStream.read()) != -1) {
                sb.write(read);
            }
            System.out.println(sb.size() + " bytes, (decoded)");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
