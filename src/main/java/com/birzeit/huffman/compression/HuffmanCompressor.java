package com.birzeit.huffman.compression;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.file.BinaryStreamOut;
import com.birzeit.huffman.operation.HuffmanOperation;

import java.io.IOException;
import java.util.PriorityQueue;

public class HuffmanCompressor {

    public static void initializePriorityQueue() { // Time = O(n*log n)

        System.out.println("Initializing Priority Queue...");

        HuffmanOperation.HEAP = new PriorityQueue<HuffmanNode>();

        for (int i = 0; i < HuffmanOperation.FREQ_ARR.length; i++)
            if (HuffmanOperation.FREQ_ARR[i] > 0)
                HuffmanOperation.HEAP.add(new HuffmanNode(HuffmanOperation.FREQ_ARR[i], (byte) i, true));
    }

    public static void buildHuffmanTree() {  // n*log n

        System.out.println("Building Huffman Tree...");

        while (HuffmanOperation.HEAP.size() > 1) {
            HuffmanNode node = new HuffmanNode(0);
            HuffmanNode left = (HuffmanNode) HuffmanOperation.HEAP.poll(); //log n
            HuffmanNode right = (HuffmanNode) HuffmanOperation.HEAP.poll();
            node.addLift(left);
            node.addRight(right);
            node.setFreq(left.getFreq()+right.getFreq());
            HuffmanOperation.HEAP.add(node);
        }

        HuffmanOperation.HUFFMAN_TREE_ROOT = (HuffmanNode) HuffmanOperation.HEAP.peek();

    }

    public static void getHeaderLength(HuffmanNode huffmanTreeRoot) {

        System.out.println("Getting Header Length...");

        if (huffmanTreeRoot == null)
            return;
        if (huffmanTreeRoot.isLeaf()) {
            HuffmanOperation.HEADER_LENGTH += 9;
            return;
        } else {
            HuffmanOperation.HEADER_LENGTH++;
            getHeaderLength(huffmanTreeRoot.getLeft());
            getHeaderLength(huffmanTreeRoot.getRight());
        }

    }

    public static void buildHeader(HuffmanNode huffmanTreeRoot, BinaryStreamOut bos) throws IOException {

        System.out.println("Building Header ...");

        if (huffmanTreeRoot.isLeaf()) { //if node leaf print "1 value of node " else print "0"
            bos.write(true);
            String byteInBinaryAsString = convertByteToBitString(huffmanTreeRoot.getVal());
            for (int i = 0; i < byteInBinaryAsString.length(); i++) {
                if (byteInBinaryAsString.charAt(i) == '1')
                    bos.write(true);
                else
                    bos.write(false);
            }
            return;
        }
        else
            bos.write(false);
        buildHeader(huffmanTreeRoot.getLeft(), bos);
        buildHeader(huffmanTreeRoot.getRight(), bos);
    }

    private static String convertByteToBitString(byte b) { // convert byte to bits in string
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; --i) {
            sb.append(b >>> i & 1);
        }
        return sb.toString();
    }
}
