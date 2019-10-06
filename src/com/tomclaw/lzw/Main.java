package com.tomclaw.lzw;

import java.io.*;
import java.nio.file.Files;

import static com.tomclaw.lzw.StreamHelper.closeStream;

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

        try {
            String command = args[0];
            String source = args[1];
            String destination = args[2];
            switch (command) {
                case "-c":
                    InputStream decInputStream = null;
                    OutputStream encOutputStream = null;
                    LZWOutputStream lzwOutputStream = null;
                    try {
                        decInputStream = new BufferedInputStream(new FileInputStream(source));
                        encOutputStream = new BufferedOutputStream(new FileOutputStream(destination));
                        lzwOutputStream = new LZWOutputStream(encOutputStream);
                        int read;
                        while ((read = decInputStream.read()) != -1) {
                            lzwOutputStream.write(read);
                        }
                    } finally {
                        closeStream(lzwOutputStream);
                        closeStream(decInputStream);
                        closeStream(encOutputStream);
                    }
                    return;
                case "-x":
                    InputStream encInputStream = null;
                    OutputStream decOutputStream = null;
                    LZWInputStream lzwInputStream = null;
                    try {
                        encInputStream = new BufferedInputStream(new FileInputStream(source));
                        decOutputStream = new BufferedOutputStream(new FileOutputStream(destination));
                        lzwInputStream = new LZWInputStream(encInputStream);
                        int read;
                        while ((read = lzwInputStream.read()) != -1) {
                            decOutputStream.write(read);
                        }
                    } finally {
                        closeStream(lzwInputStream);
                        closeStream(encInputStream);
                        closeStream(decOutputStream);
                    }
                    return;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
