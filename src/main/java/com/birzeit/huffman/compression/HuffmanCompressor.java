package com.birzeit.huffman.compression;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.dto.Node;
import com.birzeit.huffman.file.BinaryStreamOut;
import com.birzeit.huffman.file.BitOutputStream;
import com.birzeit.huffman.operation.HuffmanOperation;

import java.io.IOException;
import java.util.ArrayList;
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

    public static ArrayList<Node> addCode(HuffmanNode root) {
        ArrayList<Node> huffmanCodes = new ArrayList<Node>();
        String s = ("");
        addCode(root, s, huffmanCodes);
        return huffmanCodes;
    }

    private static void addCode(HuffmanNode root, String s, ArrayList<Node> huffmanCodes) {
        if (!(root.isLeaf())) {
            String sl = s + "0"; // 0 in left
            String sr = s + "1"; // 1 in right
            addCode(root.getLeft(), sl, huffmanCodes);
            addCode(root.getRight(), sr, huffmanCodes);
        } else {
            huffmanCodes.add(new Node(s.toString(), root)); // add node of list with new code
        }
    }

    public static void writeCompressedData(BitOutputStream bitStream, BinaryStreamOut binaryStreamOut) throws IOException {

        System.out.println("Writing compress data to file");

        StringBuilder s = new StringBuilder("");

        for (int i = 0; i < HuffmanOperation.BYTES_IN_FILE.length; i++) { //Covert all bytes in file to the new huffman code
            for (int j = 0; j < HuffmanOperation.HUFFMAN_CODE_LIST.size(); j++) {
                if ((HuffmanOperation.BYTES_IN_FILE[i]) == HuffmanOperation.HUFFMAN_CODE_LIST.get(j).getVal().getVal()) {
                    s.append(HuffmanOperation.HUFFMAN_CODE_LIST.get(j).getHuffmanCode());
                    break;
                }
            }
        }
        bitStream.writeH(new StringBuilder(HuffmanOperation.BYTES_IN_FILE.length + ":"));

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1')
                binaryStreamOut.write(true);
            else if (s.charAt(i) == '0')
                binaryStreamOut.write(false);
        }
        // After write the file length in the compressed file, we Wrote the header.
        binaryStreamOut.close();  // print header + compress data
    }
}
