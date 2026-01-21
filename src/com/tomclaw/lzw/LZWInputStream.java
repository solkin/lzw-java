package com.tomclaw.lzw;

import java.io.*;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class LZWInputStream extends FilterInputStream {

    private final HashMap<Integer, IntBuffer> dictionary = new LinkedHashMap<>();
    private int currentChar = 0;
    private IntBuffer oldPhrase = null;
    private int code = 256;
    private IntBuffer phrase;
    private int currCode;
    private final BitInputStream bis;
    private int codeWidth = 9; // Start with 9 bits (codes 256-511)

    private static final int MAX_CODE_WIDTH = 16;

    private final PipedInputStream pipedInputStream = new PipedInputStream();
    private final PipedOutputStream pipedOutputStream = new PipedOutputStream();
    private final DataOutputStream dataOutputStream = new DataOutputStream(pipedOutputStream);

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
        bis = new BitInputStream(in);
    }

    @Override
    public int read() throws IOException {
        if (pipedInputStream.available() == 0) {
            try {
                extractBuffer();
            } catch (EOFException ex) {
                return -1;
            }
        }
        return pipedInputStream.read();
    }

    private void extractBuffer() throws IOException {
        if ((currCode = readInt(bis)) == -1) {
            throw new EOFException("EOF");
        }
        if (oldPhrase == null) {
            pipedOutputStream.connect(pipedInputStream);
            currentChar = currCode;
            oldPhrase = toBuffer(currentChar);
            writeIntBuffer(dataOutputStream, oldPhrase);
            oldPhrase.rewind();
            return;
        }
        IntBuffer currIntBuffer = toBuffer(currCode);
        if (isByte(currCode)) {
            phrase = currIntBuffer;
        } else {
            if (dictionary.get(currCode) != null) {
                phrase = (IntBuffer) dictionary.get(currCode).rewind();
            } else {
                phrase = (IntBuffer) IntBuffer.allocate(oldPhrase.limit() + 1).put((IntBuffer) oldPhrase.rewind()).put(currentChar).rewind();
            }
        }

        writeIntBuffer(dataOutputStream, phrase);

        currentChar = phrase.get(0);
        currIntBuffer = toBuffer(currentChar);
        IntBuffer oldPhraseWithCurrentChar = (IntBuffer) IntBuffer.allocate(oldPhrase.limit() + currIntBuffer.limit())
                .put((IntBuffer) oldPhrase.rewind())
                .put(currIntBuffer)
                .rewind();
        dictionary.put(code, oldPhraseWithCurrentChar);
        code++;
        // Increase code width when reaching the limit of current width
        if (code >= (1 << codeWidth) && codeWidth < MAX_CODE_WIDTH) {
            codeWidth++;
        }
        oldPhrase = phrase;
    }

    private void writeIntBuffer(DataOutputStream output, IntBuffer buffer) throws IOException {
        buffer.rewind();
        for (int value : buffer.array()) {
            if (isByte(value)) {
                output.write(value);
            } else {
                output.writeInt(value);
            }
        }
    }

    private int readInt(BitInputStream input) throws IOException {
        // Read code with fixed width (variable-width coding)
        int value = 0;
        for (int i = 0; i < codeWidth; i++) {
            int bit = input.readBit();
            if (bit == -1) {
                return -1;
            }
            value = (value << 1) | bit;
        }
        return value;
    }

    private boolean isByte(int value) {
        return value == (byte) value;
    }

    private IntBuffer toBuffer(int value) {
        return (IntBuffer) IntBuffer.allocate(1).put(value).rewind();
    }

    @Override
    public void close() throws IOException {
        super.close();
        pipedInputStream.close();
        pipedOutputStream.close();
        dataOutputStream.close();
    }
}
