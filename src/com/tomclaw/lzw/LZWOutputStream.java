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
                writeChar((char) dictionary.get(phrase).intValue());
            } else {
                writeChar((char) Character.codePointAt(phrase, 0));
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
                writeChar((char) dictionary.get(phrase).intValue());
            } else {
                writeChar((char) Character.codePointAt(phrase, 0));
            }
        }
        isFlushed = true;
        super.flush();
    }

    private void writeChar(char c) throws IOException {
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out.write((byte) c);
        } else if (c > 0x07FF) {
            out.write((byte) (0xE0 | ((c >> 12) & 0x0F)));
            out.write((byte) (0x80 | ((c >> 6) & 0x3F)));
            out.write((byte) (0x80 | ((c >> 0) & 0x3F)));
        } else {
            out.write((byte) (0xC0 | ((c >> 6) & 0x1F)));
            out.write((byte) (0x80 | ((c >> 0) & 0x3F)));
        }
    }
}
