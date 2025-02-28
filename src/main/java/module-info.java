module com.example.pt2024_group5_moga_diana_assigment1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.pt2024_group5_moga_diana_assigment1 to javafx.fxml;
    exports com.example.pt2024_group5_moga_diana_assigment1;
}