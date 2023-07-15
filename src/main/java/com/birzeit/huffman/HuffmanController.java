package com.birzeit.huffman;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HuffmanController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}