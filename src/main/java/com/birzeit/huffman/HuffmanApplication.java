package com.birzeit.huffman;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HuffmanApplication extends Application {

    public static Stage STAGE;

    @Override
    public void start(Stage stage) throws IOException {
        STAGE = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HuffmanApplication.class.getResource("huffman-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        STAGE.setTitle("Huffman Compressor");
        STAGE.setScene(scene);
        STAGE.centerOnScreen();
        STAGE.show();
    }

    public static void main(String[] args) {
        launch();
    }
}