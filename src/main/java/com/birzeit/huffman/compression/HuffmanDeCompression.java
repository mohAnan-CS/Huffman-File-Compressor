package com.birzeit.huffman.compression;

import com.birzeit.huffman.file.BinaryStreamIn;
import com.birzeit.huffman.operation.HuffmanOperation;

public class HuffmanDeCompression {

    private void splitHeader(BinaryStreamIn binaryRead) {

        boolean n = true;

        while (n) {
            char c = (char) binaryRead.readByte();
            if (c == ':')
                n = false;
            else
                HuffmanOperation.TYPE_FILE += c;
        }
        n = true;
        while (n) {
            char c = (char) binaryRead.readByte();
            if (c == ':')
                n = false;
            else
                HuffmanOperation.HEADER_LENGTH += c;
        }

        n = true;
        while (n) {
            char c = (char) binaryRead.readByte();
            if (c == ':')
                n = false;
            else
                HuffmanOperation.DATA_LENGTH += c;
        }

    }


}
