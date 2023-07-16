package com.birzeit.huffman.dto;

public class HuffmanTableView {

    private String character;
    private String frequency;
    private String code;

    public HuffmanTableView(String character, String frequency, String code) {
        this.character = character;
        this.frequency = frequency;
        this.code = code;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "HuffmanTableView{" +
                "character='" + character + '\'' +
                ", frequency='" + frequency + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
