package com.tomclaw.lzw;

import java.io.Closeable;
import java.io.IOException;

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
}
