package com.birzeit.huffman.file;

import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream {

    private FileOutputStream out;
    public BitOutputStream(FileOutputStream is) {

        this.out = is;
    }

    public void writeH(StringBuilder header) throws IOException {
        for (int i = 0; i < header.length(); i++) {
            out.write((byte) header.charAt(i));
        }
    }

}
