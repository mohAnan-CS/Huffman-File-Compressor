package com.birzeit.huffman.compression;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.file.BinaryStreamIn;
import com.birzeit.huffman.operation.HuffmanOperation;

public class HuffmanDeCompressor {

    public static void splitHeader(BinaryStreamIn binaryRead) {

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

    public static HuffmanNode decodeHeader() {

        String st = peek(1);
        boolean n = st.equals("");
        if (n)
            return null;
        boolean isLeaf = st.equals("1");
        if (isLeaf) {
            byte b = (byte) ((Integer.parseInt(peek(8), 2)) - 128);
            return new HuffmanNode(b, -1, null, null, true);
        }
        return new HuffmanNode(decodeHeader(), decodeHeader());

    }

    private static String peek(int i) {
        if (i > HuffmanOperation.HEADER_AS_STRING.length())
            return "";
        String r = HuffmanOperation.HEADER_AS_STRING.substring(0, i);
        HuffmanOperation.HEADER_AS_STRING = HuffmanOperation.HEADER_AS_STRING.substring(i);
        return r;
    }

}
