package com.tomclaw.lzw;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LZWOutputStream extends FilterOutputStream {

    private HashMap<String, Integer> dictionary = new LinkedHashMap<>();
    private String phrase = null;
    private int code = 256;
    private boolean isFlushed = false;

    private int digits;
    private int numDigits;

    private static final int BYTE_SIZE = 8;

    /**
     * Creates an output stream filter built on top of the specified
     * underlying output stream.
     *
     * @param out the underlying output stream to be assigned to
     *            the field {@code this.out} for later use, or
     *            <code>null</code> if this instance is to be
     *            created without an underlying stream.
     */
    public LZWOutputStream(OutputStream out) {
        super(out);
    }

    /**
     * Writes a byte to the compressed output stream. This method will
     * block until the byte can be written.
     *
     * @param b the byte to be written
     * @throws IOException if an I/O error has occurred
     */
    @Override
    public void write(int b) throws IOException {
        char ch = (char) Byte.toUnsignedInt((byte) b);
        if (phrase == null) {
            phrase = String.valueOf(ch);
            return;
        }
        String currentChar = String.valueOf(ch);
        if (dictionary.get(phrase + currentChar) != null) {
            phrase += currentChar;
        } else {
            if (phrase.length() > 1) {
                writeInt(dictionary.get(phrase));
            } else {
                writeInt(Character.codePointAt(phrase, 0));
            }
            dictionary.put(phrase + currentChar, code);
            code++;
            phrase = currentChar;
        }
        isFlushed = false;
    }

    /**
     * Writes an array of bytes to the compressed output stream. This
     * method will block until all the bytes are written.
     *
     * @param b   the data to be written
     * @param off the start offset of the data
     * @param len the length of the data
     * @throws IOException if an I/O error has occurred
     */
    public void write(byte[] b, int off, int len) throws IOException {
        super.write(b, off, len);
    }

    @Override
    public void flush() throws IOException {
        if (!isFlushed) {
            if (phrase.length() > 1) {
                writeInt(dictionary.get(phrase));
            } else {
                writeInt(Character.codePointAt(phrase, 0));
            }
        }
        isFlushed = true;

        out.write(digits);
        digits = 0;
        numDigits = 0;
        super.flush();
    }

    @Override
    public void close() throws IOException {
        if (numDigits > 0) {
            flush();
        }
        out.close();
    }

    public void writeInt(int value) throws IOException {
        String bin = Integer.toBinaryString(value + 1);
        int c = bin.length() - 1;
        for (int i = 0; i < c; i++) {
            writeBit(0);
        }
        for (int i = 0; i <= c; i++) {
            writeBit(bin.charAt(i) == '1' ? 1 : 0);
        }
    }

    public void writeBit(int bit) throws IOException {
        if (bit < 0 || bit > 1) {
            throw new IllegalArgumentException("Illegal bit: " + bit);
        }
        digits += bit << numDigits;
        numDigits++;
        if (numDigits == BYTE_SIZE) {
            flush();
        }
    }
}
