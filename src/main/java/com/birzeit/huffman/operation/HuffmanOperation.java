package com.birzeit.huffman.operation;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.dto.Node;

import java.io.File;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class HuffmanOperation {

    private static int HEADER_LENGTH = 0;
    private static int ACTUAL_FILE_LENGTH;
    private static double RATE = 0;
    private static String HEADER_AS_STRING = "";
    private static int FREQ[];
    private static byte[] BYTES_IN_FILE;
    private static int NUMBER_DIFFERENT_BYTES = 0;
    private static PriorityQueue<HuffmanNode> HEAP;
    private static HuffmanNode HUFFMAN_TREE_ROOT;
    private static ArrayList<Node> HUFFMAN_CODE_LIST;
    private static File INPUT_COMPRESSION_FILE, INPUT_DECOMPRESSION_FILE;
    private static String TYPE_FILE = "";
    private static String HEADER_LENGTH_STRING = "";
    private static String DATA_LENGTH = "";
    
}
