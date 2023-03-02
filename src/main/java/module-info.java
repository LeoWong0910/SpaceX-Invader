module com.ood.spacexinvader {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens com.ood.spacexinvader to javafx.fxml;
    exports com.ood.spacexinvader;
}