package com.birzeit.huffman;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HuffmanController implements Initializable {
    @FXML
    private TableView<?> huffmanTableView;

    @FXML
    private TableColumn<?, ?> character;

    @FXML
    private TableColumn<?, ?> frequency;

    @FXML
    private TableColumn<?, ?> code;

    @FXML
    private TextField filePathText;

    @FXML
    void browseFileOnAction() {

        System.out.println("Browsing ...");
        filePathText.setText("C:\\Users\\user\\Desktop\\test.txt");

    }

    @FXML
    void compressOnAction() {

        System.out.println("Compressing ...");

    }

    @FXML
    void decompressOnAction() {

        System.out.println("Decompressing ...");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}