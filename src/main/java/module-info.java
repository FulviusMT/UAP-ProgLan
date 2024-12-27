module org.example.uap_proglan {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    opens org.example.uap_proglan to javafx.fxml;
    exports org.example.uap_proglan;
}