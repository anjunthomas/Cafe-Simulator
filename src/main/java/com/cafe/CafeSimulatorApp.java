package com.cafe;

import com.cafe.utils.ImageLoader;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;

public class CafeSimulatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Image background = ImageLoader.load("backgroundReference.png", 800, 800);

        ImageView backgroundView = new ImageView(background);

        // the StackPane lets us layer images on top of each other
        StackPane root = new StackPane();
        root.getChildren().add(backgroundView);

        Scene scene = new Scene(root, 900, 900);
        stage.setTitle("Cafe Simulator!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}