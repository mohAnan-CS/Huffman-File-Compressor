module com.birzeit.huffman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.birzeit.huffman to javafx.fxml;
    exports com.birzeit.huffman;
}