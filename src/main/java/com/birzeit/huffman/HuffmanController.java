package com.birzeit.huffman;

import com.birzeit.huffman.operation.HuffmanOperation;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
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
    private RadioButton compressRadioButton;

    @FXML
    private RadioButton decompressRadioButton;

    @FXML
    void browseFileOnAction() {


        if (compressRadioButton.isSelected()) {
            // Set extension filter
            FileChooser compressFileChooser = new FileChooser();
            compressFileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"),
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"), new FileChooser.ExtensionFilter("Java Files", "*.java"),
                    new FileChooser.ExtensionFilter("Web Files", "*.html", "*.css", "*.js", "*.php"),
                    new FileChooser.ExtensionFilter("Image files", "*.png", "*.jpg"), new FileChooser.ExtensionFilter("Word files", "*.docx"),
                    new FileChooser.ExtensionFilter("Pdf files", "*.pdf")

            );
            HuffmanOperation.INPUT_COMPRESSION_FILE = compressFileChooser.showOpenDialog(HuffmanApplication.STAGE);
            if (HuffmanOperation.INPUT_COMPRESSION_FILE != null) {
                filePathText.setText(HuffmanOperation.INPUT_COMPRESSION_FILE.getAbsolutePath());
            } else {
                filePathText.setText("No file selected");
            }
        }


    }


    @FXML
    void compressOnAction() {

        System.out.println("Compressing ...");

    }

    @FXML
    void decompressOnAction() {

        System.out.println("Decompressing ...");

    }

    @FXML
    void saveOnAction() {


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        preparedRadioButton();

    }

    private void preparedRadioButton() {

        compressRadioButton.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) {
                decompressRadioButton.setSelected(false);
            }
        });

        decompressRadioButton.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) {
                compressRadioButton.setSelected(false);
            }
        });

    }
}