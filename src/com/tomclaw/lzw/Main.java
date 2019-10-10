package com.tomclaw.lzw;

import java.io.*;

import static com.tomclaw.lzw.StreamHelper.closeStream;
import static com.tomclaw.lzw.StreamHelper.copy;

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
                        copy(decInputStream, lzwOutputStream);
                    } finally {
                        closeStream(lzwOutputStream, decInputStream, encOutputStream);
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
                        copy(lzwInputStream, decOutputStream);
                    } finally {
                        closeStream(lzwInputStream, encInputStream, decOutputStream);
                    }
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
