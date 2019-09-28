package com.tomclaw.lzw;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LZWInputStream extends FilterInputStream {

    private HashMap<Integer, String> dictionary = new LinkedHashMap<>();
    private int currentChar = -1;
    private String oldPhrase;
    private int code = 256;
    private String phrase = "";
    private int currCode;

    /**
     * Creates a <code>FilterInputStream</code>
     * by assigning the  argument <code>in</code>
     * to the field <code>this.in</code> so as
     * to remember it for later use.
     *
     * @param in the underlying input stream, or <code>null</code> if
     *           this instance is to be created without an underlying stream.
     */
    protected LZWInputStream(InputStream in) {
        super(in);
    }

    @Override
    public int read() throws IOException {
        if (currCode < 256) {
            phrase = String.valueOf((char) currCode);
        } else {
            if (dictionary.get(currCode) != null) {
                phrase = dictionary.get(currCode);
            } else {
                phrase = oldPhrase + currentChar;
            }
        }
        phrase.getBytes(StandardCharsets.UTF_8);
        currentChar = phrase.charAt(0);
        dictionary.put(code, oldPhrase + currentChar);
        code++;
        oldPhrase = phrase;
        return 1;
    }
}
