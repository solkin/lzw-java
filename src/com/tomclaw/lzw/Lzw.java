package com.tomclaw.lzw;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Lzw {

    public void lzw_extract(BitInputStream input, DataOutputStream output) throws IOException {
        HashMap<Integer, IntBuffer> dictionary = new LinkedHashMap<>();
        int currentChar = readInt(input);
        IntBuffer oldPhrase = toBuffer(currentChar);
        output.write(currentChar);
        int code = 256;
        IntBuffer phrase;
        int currCode;
        while ((currCode = readInt(input)) != -1) {
            IntBuffer currIntBuffer = toBuffer(currCode);
            if (isByte(currCode)) {
                phrase = currIntBuffer;
            } else {
                if (dictionary.get(currCode) != null) {
                    phrase = dictionary.get(currCode).rewind();
                } else {
                    phrase = IntBuffer.allocate(oldPhrase.limit() + 1).put(oldPhrase.rewind()).put(currentChar).rewind();
                }
            }

            writeIntBuffer(output, phrase);

            currentChar = phrase.get(0);
            currIntBuffer = toBuffer(currentChar);
            IntBuffer oldPhraseWithCurrentChar = IntBuffer.allocate(oldPhrase.limit() + currIntBuffer.limit())
                    .put(oldPhrase.rewind())
                    .put(currIntBuffer)
                    .rewind();
            dictionary.put(code, oldPhraseWithCurrentChar);
            code++;
            oldPhrase = phrase;
        }
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
            if (bit == -1) {
                throw new EOFException();
            }
            bin.append(bit);
        }
        return (int) Long.parseLong(bin.toString(), 2) - 1;
    }

    private boolean isByte(int value) {
        return value == (byte) value;
    }

    private IntBuffer toBuffer(int value) {
        return IntBuffer.allocate(1).put(value).rewind();
    }
}
