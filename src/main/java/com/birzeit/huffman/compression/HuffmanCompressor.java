package com.birzeit.huffman.compression;

import com.birzeit.huffman.dto.HuffmanNode;
import com.birzeit.huffman.operation.HuffmanOperation;

import java.util.PriorityQueue;

public class HuffmanCompressor {

    public static void initializePriorityQueue() { // Time = O(n*log n)

        System.out.println("Initializing Priority Queue...");

        HuffmanOperation.HEAP = new PriorityQueue<HuffmanNode>();

        for (int i = 0; i < HuffmanOperation.FREQ_ARR.length; i++)
            if (HuffmanOperation.FREQ_ARR[i] > 0)
                HuffmanOperation.HEAP.add(new HuffmanNode(HuffmanOperation.FREQ_ARR[i], (byte) i, true));
    }
}
