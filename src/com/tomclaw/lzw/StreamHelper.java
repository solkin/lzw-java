package com.tomclaw.lzw;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamHelper {

    public static void closeStream(Closeable... closeable) {
        for (Closeable c : closeable) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException ignored) {
                }
            }
        }
    }

    public static void copy(InputStream input, OutputStream output) throws IOException {
        int read;
        while ((read = input.read()) != -1) {
            output.write(read);
        }
        output.flush();
    }
}
