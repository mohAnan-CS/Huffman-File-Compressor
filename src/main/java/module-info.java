module com.birzeit.huffman {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.birzeit.huffman to javafx.fxml;
    opens com.birzeit.huffman.dto to javafx.base;
    exports com.birzeit.huffman;
}