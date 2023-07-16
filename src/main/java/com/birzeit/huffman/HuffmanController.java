package com.birzeit.huffman;

import com.birzeit.huffman.dto.HuffmanTableView;
import com.birzeit.huffman.file.FileReader;
import com.birzeit.huffman.operation.HuffmanOperation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class HuffmanController implements Initializable {
    @FXML
    private TableView<HuffmanTableView> huffmanTableView;

    @FXML
    private TableColumn<HuffmanTableView, String> character;

    @FXML
    private TableColumn<HuffmanTableView, String> frequency;

    @FXML
    private TableColumn<HuffmanTableView, String> code;

    @FXML
    private TextField filePathText;

    @FXML
    private RadioButton compressRadioButton;

    @FXML
    private RadioButton decompressRadioButton;

    private ObservableList<HuffmanTableView> huffmanObservableList = FXCollections.observableArrayList();

    @FXML
    private TextArea compressionDataTextArea;

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

        if (HuffmanOperation.INPUT_COMPRESSION_FILE != null) {

            FileChooser fileChooser =new FileChooser();
            //set extension filter to make if just huffman type file
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("huff files (*.huff)", "*.huff");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(HuffmanApplication.STAGE);
            System.out.println("file new path for huff " + file.getAbsolutePath());
            FileReader.readFile(HuffmanOperation.INPUT_COMPRESSION_FILE);
            HuffmanOperation.compress(file);

            fillObservableList();
            huffmanTableView.setItems(huffmanObservableList);
            setDataCompressionTextArea(
                    HuffmanOperation.HEADER_LENGTH,
                    HuffmanOperation.ACTUAL_FILE_LENGTH,
                    HuffmanOperation.RATE
            );

        } else {
            System.out.println("Please select file");
        }

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
        setAllCellValueFactory();

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

    private void fillObservableList(){

        //loop on freq array to fill observable list
        for (int i = 0; i < HuffmanOperation.HUFFMAN_CODE_LIST.size(); i++) {
            String character = String.valueOf((char) HuffmanOperation.HUFFMAN_CODE_LIST.get(i).getVal().getVal());
            String code = HuffmanOperation.HUFFMAN_CODE_LIST.get(i).getHuffmanCode();
            String frequency = String.valueOf(HuffmanOperation.HUFFMAN_CODE_LIST.get(i).getVal().getFreq());
            HuffmanTableView huffmanTableView = new HuffmanTableView(character, frequency, code);
            huffmanObservableList.add(huffmanTableView);
        }

    }

    public void setAllCellValueFactory(){

        character.setCellValueFactory(new PropertyValueFactory<>("character"));
        frequency.setCellValueFactory(new PropertyValueFactory<>("frequency"));
        code.setCellValueFactory(new PropertyValueFactory<>("code"));

    }

    private void setDataCompressionTextArea(int fileHeadLength, int actualDataLength, double compressionRate){

        compressionDataTextArea.setText("File Head Length: " + fileHeadLength + "\n" +
                "Actual Data Length: " + actualDataLength + "\n" +
                "Compression Rate: " + compressionRate + " % \n");

    }
}