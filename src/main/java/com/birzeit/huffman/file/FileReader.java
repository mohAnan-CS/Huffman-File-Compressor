package com.birzeit.huffman.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReader {

    private static  void readFile(File file){

        bytes_in_file =new byte[(int)file.length()];
        //read file in bytes
        try(FileInputStream inputStream= new FileInputStream(file)) {
            inputStream.read(bytes_in_file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        frq = new int[256];
        for (int i = 0; i < bytes_in_file.length; i++)
            frq[bytes_in_file[i] + 128]++; //byte can take is from -128 to 127.

        for (int i = 0; i < frq.length; i++)
            if (frq[i] > 0)
                number_different_bytes++;   // if freq[i] > 0 this mean a new byte in file

    }

}
