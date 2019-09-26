package com.tomclaw.lzw;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

    public void lzw_extract(InputStream input, OutputStream output) throws IOException {
        HashMap<Integer, String> dictionary = new LinkedHashMap<>();
        char currentChar = (char)readChar(input);
        String oldPhrase = String.valueOf(currentChar);
        output.write(currentChar);
        int code = 256;
        String phrase = "";
        int currCode;
        while ((currCode = readChar(input)) != -1) {
            if (currCode < 256) {
                phrase = String.valueOf((char)currCode);
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

    private int readChar(InputStream input) throws IOException {
        int read = input.read();
        if (read == -1) {
            return -1;
        }
        int c = read & 0xff;
        int char2, char3;
        switch (c >> 4) {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
                /* 0xxxxxxx*/
                return (char)c;
            case 12: case 13:
                /* 110x xxxx   10xx xxxx*/
                char2 = input.read();
                if ((char2 & 0xC0) != 0x80)
                    throw new UTFDataFormatException("malformed input");
                return (((c & 0x1F) << 6) |
                        (char2 & 0x3F));
            case 14:
                /* 1110 xxxx  10xx xxxx  10xx xxxx */
                char2 = input.read();
                char3 = input.read();
                if (((char2 & 0xC0) != 0x80) || ((char3 & 0xC0) != 0x80))
                    throw new UTFDataFormatException("malformed input");
                return (((c     & 0x0F) << 12) |
                        ((char2 & 0x3F) << 6)  |
                        ((char3 & 0x3F) << 0));
            default:
                /* 10xx xxxx,  1111 xxxx */
                throw new UTFDataFormatException(
                        "malformed input");
        }
    }
}
