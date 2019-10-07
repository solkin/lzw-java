package com.tomclaw.lzw;

import java.io.*;
import java.nio.file.Files;

import static com.tomclaw.lzw.StreamHelper.closeStream;

public class Main {

    public static void main(String[] args) {
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
                        decOutputStream.flush();
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
