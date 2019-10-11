package com.tomclaw.lzw;

import java.io.*;

import static com.tomclaw.lzw.StreamHelper.closeStream;
import static com.tomclaw.lzw.StreamHelper.copy;

public class Main {

    private static String HELP = "First option must be a mode specifier:\n" +
            "  -c Create  -x Extract\n" +
            "Create: bzz -c <file> | <archive>\n" +
            "  <file>  add these item to archive\n" +
            "  <archive>  archived output file with .bzz extension\n" +
            "Extract: bzz -x <archive> | <file>\n" +
            "  <archive>  archive with .bzz extension path\n" +
            "  <file>  extracted file path\n" +
            "bzz 1.0";

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
                    return;
                case "-h":
                    System.out.println(HELP);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
