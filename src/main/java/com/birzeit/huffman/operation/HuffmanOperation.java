package com.birzeit.huffman.operation;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.dto.Node;

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
    
}
