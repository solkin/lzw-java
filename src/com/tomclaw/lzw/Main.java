package com.tomclaw.lzw;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.GZIPOutputStream;

public class Main {

    public static void main(String[] args) {
        try {
            ByteArrayInputStream input;
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();

            File in = new File("/Users/solkin/Desktop/app-info.json");
            byte[] data = Files.readAllBytes(in.toPath());
            System.out.println(data.length + " bytes, (original)");

            Lzw lzw_compression = new Lzw();

            bytes.reset();
            LZWOutputStream lzw = new LZWOutputStream(bytes);
            lzw.write(data);
            lzw.flush();
            lzw.close();
            System.out.println(bytes.size() + " bytes (encoded)");
            // HexUtil.dump_(bytes.toByteArray(), "encoded  ");
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
            System.out.println(sb.toString());

//            lzw_compression.lzw_extract(new BitInputStream(input), new DataOutputStream(bytes));
//            bytes.flush();
//            // HexUtil.dump_(bytes.toByteArray(), "decoded  ");
//            String decoded = new String(bytes.toByteArray());
//            String original = new String(data);
//            System.out.println(bytes.size() + " bytes, (decoded)  " + decoded);
//            System.out.println(data.length + " bytes, (original) " + original);
//            System.out.println(decoded.equals(original) ? "passed" : "failed");
//            bytes.reset();
//
//            OutputStream gzip = new GZIPOutputStream(bytes);
//            gzip.write(OriginalString.getBytes(StandardCharsets.UTF_8));
//            gzip.flush();
//            gzip.close();
//            System.out.println(bytes.size() + " bytes");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
