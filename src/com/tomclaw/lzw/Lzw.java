package com.tomclaw.lzw;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Lzw {

    public void lzw_extract(BitInputStream input, OutputStream output) throws IOException {
        HashMap<Integer, String> dictionary = new LinkedHashMap<>();
        char currentChar = (char) readInt(input);
        String oldPhrase = String.valueOf(currentChar);
        output.write(currentChar);
        int code = 256;
        String phrase;
        int currCode;
        while ((currCode = readInt(input)) != -1) {
            if (currCode < 256) {
                phrase = String.valueOf((char) currCode);
            } else {
                if (dictionary.get(currCode) != null) {
                    phrase = dictionary.get(currCode);
                } else {
                    phrase = oldPhrase + currentChar;
                }
            }
            output.write(phrase.getBytes(StandardCharsets.UTF_8));
            currentChar = phrase.charAt(0);
            dictionary.put(code, oldPhrase + currentChar);
            code++;
            oldPhrase = phrase;
        }
    }

    private int readInt(BitInputStream input) throws IOException {
        int c = 0;
        int bit;
        while ((bit = input.readBit()) == 0) {
            c++;
        }
        if (bit == -1) {
            return -1;
        }
        StringBuilder bin = new StringBuilder("1");
        for (int i = 0; i < c; i++) {
            bit = input.readBit();
            bin.append(bit);
        }
        return Integer.parseInt(bin.toString(), 2) - 1;
    }
}
