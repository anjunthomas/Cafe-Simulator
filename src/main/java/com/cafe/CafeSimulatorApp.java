package com.cafe;

import java.io.FileInputStream;
import java.io.IOException;

import com.cafe.utils.ImageLoader;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;


public class CafeSimulatorApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        boolean[] coffeeBusy = { false };
        Image background = ImageLoader.load("backgroundReference.png", 800, 800);

        ImageView backgroundView = new ImageView(background);

        Image coffee_full = ImageLoader.load("coffeebeans.png");

        ImageView coffee = new ImageView(coffee_full);

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(20);
        glow.setSpread(0.6);

        coffee.setOnMouseEntered(e -> {
            if (!coffeeBusy[0]) {
                coffee.setEffect(glow);
            }
        });

        coffee.setOnMouseExited(e -> {
            if (!coffeeBusy[0]) {
                coffee.setEffect(null);
            }
        });



        ProgressBar coffeeProgress = new ProgressBar(0);
        coffeeProgress.setPrefWidth(120);   // bigger
        coffeeProgress.setPrefHeight(20);   // thicker
        coffeeProgress.setVisible(false);
        coffeeProgress.setStyle("-fx-accent: red;"); // bright color

        StackPane coffeeStack = new StackPane(coffee, coffeeProgress);
        StackPane.setAlignment(coffeeProgress, Pos.CENTER);

        coffeeStack.setTranslateX(180);
        coffeeStack.setTranslateY(40);

        coffee.setOnMouseClicked(e -> {

            coffeeBusy[0] = true;      // mark busy
            coffee.setEffect(null);    // remove glow immediately
            coffee.setDisable(true);  

            coffeeProgress.setProgress(0);
            coffeeProgress.setVisible(true);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(2),
                            new KeyValue(coffeeProgress.progressProperty(), 1)
                    )
            );

            timeline.setOnFinished(event -> {
                coffeeProgress.setVisible(false);
                coffeeBusy[0] = false;  // allow hover again
                coffee.setDisable(false);
            });
            timeline.play();
        });

        Image milk_full = ImageLoader.load("milk.png");
        Image milk_hover = ImageLoader.load("milk2.png");
        ImageView milk = new ImageView(milk_full);


        milk.setPickOnBounds(false); // IMPORTANT
        milk.setOnMouseEntered(e -> milk.setImage(milk_hover));
        milk.setOnMouseExited(e -> milk.setImage(milk_full));
        milk.setTranslateX(-130);
        milk.setTranslateY(-40);

        Image sugar_full = ImageLoader.load("SugarBowl.png");
        Image sugar_hover = ImageLoader.load("SugarBowl2.png");
        ImageView sugar = new ImageView(sugar_full);


        sugar.setPickOnBounds(false); // IMPORTANT
        sugar.setOnMouseEntered(e -> sugar.setImage(sugar_hover));
        sugar.setOnMouseExited(e -> sugar.setImage(sugar_full));
        sugar.setTranslateX(-240);
        sugar.setTranslateY(35);

        Image cups_stack = ImageLoader.load("Cups_rack.png");
        Image cups_hover = ImageLoader.load("Cups_rack2.png");
        ImageView cups = new ImageView(cups_stack);


        cups.setPickOnBounds(false); // IMPORTANT
        cups.setOnMouseEntered(e -> cups.setImage(cups_hover));
        cups.setOnMouseExited(e -> cups.setImage(cups_stack));
        cups.setTranslateX(-140);
        cups.setTranslateY(-180);
        cups.setRotate(5);


        // the StackPane lets us layer images on top of each other
        StackPane root = new StackPane();
        root.getChildren().add(backgroundView);
        root.getChildren().add(coffeeStack);
        root.getChildren().add(milk);
        root.getChildren().add(sugar);
        root.getChildren().add(cups);

        Scene scene = new Scene(root, 900, 900);
        stage.setTitle("Cafe Simulator!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}