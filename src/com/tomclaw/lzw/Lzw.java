package com.tomclaw.lzw;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Lzw {

    public long lzw_compress(InputStream input, DataOutputStream output) throws IOException {
        HashMap<String, Integer> dictionary = new LinkedHashMap<>();
        int read;
        String currentChar;
        String phrase = String.valueOf((char) input.read());
        long count = 1;
        int code = 256;
        while ((read = input.read()) != -1) {
            count++;
            currentChar = String.valueOf((char) read);
            if (dictionary.get(phrase + currentChar) != null) {
                phrase += currentChar;
            } else {
                if (phrase.length() > 1) {
                    writeChar((char) dictionary.get(phrase).intValue(), output);
                } else {
                    writeChar((char) Character.codePointAt(phrase, 0), output);
                }

                dictionary.put(phrase + currentChar, code);
                code++;
                phrase = currentChar;
            }
        }

        if (phrase.length() > 1) {
            writeChar((char) dictionary.get(phrase).intValue(), output);
        } else {
            writeChar((char) Character.codePointAt(phrase, 0), output);
        }

        return count;
    }

    public String lzw_extract(String input) {
        HashMap<Integer, String> dictionary = new LinkedHashMap<>();
        String[] data = (input + "").split("");
        String currentChar = data[0];
        String oldPhrase = currentChar;
        String out = currentChar;
        int code = 256;
        String phrase = "";
        for (int i = 1; i < data.length; i++) {
            int currCode = Character.codePointAt(data[i], 0);
            if (currCode < 256) {
                phrase = data[i];
            } else {
                if (dictionary.get(currCode) != null) {
                    phrase = dictionary.get(currCode);
                } else {
                    phrase = oldPhrase + currentChar;
                }
            }
            out += phrase;
            currentChar = phrase.substring(0, 1);
            dictionary.put(code, oldPhrase + currentChar);
            code++;
            oldPhrase = phrase;
        }
        return out;
    }

    private void writeChar(char c, OutputStream output) throws IOException {
        if ((c >= 0x0001) && (c <= 0x007F)) {
            output.write(c);
        } else if (c > 0x07FF) {
            output.write((byte) (0xE0 | ((c >> 12) & 0x0F)));
            output.write((byte) (0x80 | ((c >>  6) & 0x3F)));
            output.write((byte) (0x80 | ((c >>  0) & 0x3F)));
        } else {
            output.write((byte) (0xC0 | ((c >>  6) & 0x1F)));
            output.write((byte) (0x80 | ((c >>  0) & 0x3F)));
        }
    }
}
