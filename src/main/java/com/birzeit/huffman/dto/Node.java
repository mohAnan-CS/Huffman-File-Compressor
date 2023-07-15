package com.birzeit.huffman.dto;

public class Node implements Comparable<Node> {
    private String huffmanCode;
    private HuffmanNode val;

    public Node(String huffmanCode, HuffmanNode val) {
        super();
        this.huffmanCode = huffmanCode;
        this.val = val;
    }

    public String getHuffmanCode() {
        return huffmanCode;
    }

    public void setHuffmanCode(String hCode) {
        this.huffmanCode = hCode;
    }

    public HuffmanNode getVal() {
        return val;
    }

    public void setVal(HuffmanNode val) {
        this.val = val;
    }

    @Override
    public int compareTo(Node a) {
        // TODO Auto-generated method stub
        if (this.val.getVal() > a.val.getVal())
            return 1;
        if (this.val.getVal() < a.val.getVal())
            return -1;
        else
            return 0;
    }

}

