package com.birzeit.huffman.file;

import com.birzeit.huffman.operation.HuffmanOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

    public static  void readFile(File file){

        HuffmanOperation.INPUT_COMPRESSION_FILE = file;

        HuffmanOperation.BYTES_IN_FILE =new byte[(int)file.length()];
        //read file in bytes
        try(FileInputStream inputStream= new FileInputStream(file)) {
            inputStream.read(HuffmanOperation.BYTES_IN_FILE);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        HuffmanOperation.FREQ_ARR = new int[256];
        for (int i = 0; i < HuffmanOperation.BYTES_IN_FILE.length; i++)
            HuffmanOperation.FREQ_ARR[HuffmanOperation.BYTES_IN_FILE[i] + 128]++; //byte can take is from -128 to 127.

        for (int i = 0; i < HuffmanOperation.FREQ_ARR.length; i++)
            if (HuffmanOperation.FREQ_ARR[i] > 0)
                HuffmanOperation.NUMBER_DIFFERENT_BYTES++;   // if freq[i] > 0 this mean a new byte in file

    }

}
