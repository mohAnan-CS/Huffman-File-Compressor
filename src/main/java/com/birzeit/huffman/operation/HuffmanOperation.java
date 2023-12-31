package com.birzeit.huffman.operation;

import com.birzeit.huffman.compression.HuffmanCompressor;
import com.birzeit.huffman.compression.HuffmanDeCompressor;
import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.dto.Node;
import com.birzeit.huffman.file.BinaryStreamIn;
import com.birzeit.huffman.file.BinaryStreamOut;
import com.birzeit.huffman.file.BitOutputStream;
import com.birzeit.huffman.file.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public static String FILE_NAME = "";

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
        System.out.println("Length: " + BYTES_IN_FILE.length);
        for (int i = 0; i < BYTES_IN_FILE.length; i++) {
            System.out.println("Byte: " + BYTES_IN_FILE[i]);
        }
        System.out.println("------------------------");
        System.out.println("Print File name ");
        System.out.println(FILE_NAME);


    }

    public static void compress(File file){

        if(INPUT_COMPRESSION_FILE != null) {

            FileOutputStream outputStream = null; //write in file as stream of bytes
            try {
                outputStream = new FileOutputStream(file); //binary and bit stream to write in file
                BinaryStreamOut binaryStream = new BinaryStreamOut(outputStream);
                BitOutputStream bitOutputStream = new BitOutputStream(outputStream); // write string in file
                HuffmanCompressor.initializePriorityQueue();
                HuffmanCompressor.buildHuffmanTree();
                HuffmanCompressor.getHeaderLength(HUFFMAN_TREE_ROOT);
                System.out.println("Header Length: " + HEADER_LENGTH);
                bitOutputStream.writeH(new StringBuilder(FILE_NAME + ":" + HEADER_LENGTH + ":"));
                HuffmanCompressor.buildHeader(HUFFMAN_TREE_ROOT, binaryStream);
                HUFFMAN_CODE_LIST = HuffmanCompressor.addCode(HUFFMAN_TREE_ROOT);
                System.out.println("Get huffman code list ...");
                HuffmanCompressor.writeCompressedData(bitOutputStream, binaryStream);
                ACTUAL_FILE_LENGTH = (int) file.length();  // length of new file
                RATE = ((double) (BYTES_IN_FILE.length - ACTUAL_FILE_LENGTH) / BYTES_IN_FILE.length) * 100;
                String ratio = RATE + "";
                if (ratio.length() > 5) {
                    ratio = ratio.substring(0, 5);
                }
                System.out.println("Compression Rate : " + RATE);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void decompress(File file) throws FileNotFoundException {

        BinaryStreamIn binaryReader = new BinaryStreamIn(INPUT_DECOMPRESSION_FILE);
        HuffmanDeCompressor.splitHeader(binaryReader);

        if (file != null){
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(file);
                BinaryStreamOut bout = new BinaryStreamOut(out);

                Long longDataLength = Long.parseLong(HuffmanOperation.DATA_LENGTH);

                int length = Integer.parseInt(HuffmanOperation.HEADER_LENGTH_STRING);

                StringBuilder headerAsStringBuilder = new StringBuilder("");
                for (int i = 0; i < length; i++) {
                    boolean b = binaryReader.readBoolean();
                    if (b)
                        headerAsStringBuilder.append("1");
                    else
                        headerAsStringBuilder.append("0");
                }
                HuffmanOperation.HEADER_AS_STRING = headerAsStringBuilder.toString();
                HuffmanNode root = HuffmanDeCompressor.decodeHeader();
                for (int i = 0; i < longDataLength; i++) {
                    HuffmanNode node = root;
                    while (!node.isLeaf()) {
                        boolean bit = binaryReader.readBoolean();
                        if (bit)
                            node = node.getRight();
                        else
                            node = node.getLeft();
                    }
                    bout.write(node.getVal());
                }
                bout.close();

            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException ioe) {
                    System.out.println("Error while closing stream: " + ioe);
                }

            }
        }


    }

}
