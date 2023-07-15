package com.birzeit.huffman.operation;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.dto.Node;
import com.birzeit.huffman.file.FileReader;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class HuffmanOperation {

    public static int HEADER_LENGTH = 0;
    public static int ACTUAL_FILE_LENGTH;
    public static double RATE = 0;
    public static String HEADER_AS_STRING = "";
    public static int[] FREQ_ARR;
    public static byte[] BYTES_IN_FILE;
    public static int NUMBER_DIFFERENT_BYTES = 0;
    public static PriorityQueue<HuffmanNode> HEAP;
    public static HuffmanNode HUFFMAN_TREE_ROOT;
    public static ArrayList<Node> HUFFMAN_CODE_LIST;
    public static File INPUT_COMPRESSION_FILE, INPUT_DECOMPRESSION_FILE;
    public static String TYPE_FILE = "";
    public static String HEADER_LENGTH_STRING = "";
    public static String DATA_LENGTH = "";

    public static void main(String[] args) {

        FileReader.readFile(new File("C:\\Users\\twitter\\IdeaProjects\\Huffman\\test.txt"));
        System.out.println("Print Frequency Array");
        for (int i = 0; i < FREQ_ARR.length; i++) {
            if (FREQ_ARR[i] > 0) {
                System.out.println("Byte: " + (i - 128) + " Frequency: " + FREQ_ARR[i]);
            }
        }
        System.out.println("------------------------");
        System.out.println("Print Number of Different Bytes: " + NUMBER_DIFFERENT_BYTES);
        System.out.println("------------------------");
        System.out.println("Print Bytes in File");
        for (int i = 0; i < BYTES_IN_FILE.length; i++) {
            System.out.println("Byte: " + BYTES_IN_FILE[i]);
        }

    }

}
