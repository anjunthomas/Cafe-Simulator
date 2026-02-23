package com.cafe.utils;

import com.cafe.managers.InventoryManager;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class IngredientView {

    private ImageView imageView;
    private ProgressBar progressBar;
    private boolean[] busy = { false };
    private String inventoryKey;
    private InventoryManager inventory;
    private double width;
    private double height;

    public IngredientView(String inventoryKey, String imageName, double width, double height, InventoryManager inventory, DropShadow glow, Runnable onClickSound, Runnable onIngredientUsed, Runnable onEmpty) {
        this.inventoryKey = inventoryKey;
        this.inventory = inventory;
        this.width = width;
        this.height = height;

        imageView = new ImageView(ImageLoader.load(imageName, width, height));
        imageView.setPickOnBounds(false);

        imageView.setOnMouseEntered(e -> {
            if (!busy[0]) imageView.setEffect(glow);
            else imageView.setEffect(null);
        });

        imageView.setOnMouseExited(e -> {
            busy[0] = false;
            imageView.setEffect(null);
        });

        imageView.setOnMouseClicked(e -> {

            if (inventory.getIngredient(inventoryKey).isEmpty()) {
                if (onEmpty != null) onEmpty.run();
                return;
            }

            busy[0] = true;
            imageView.setEffect(null);
            if (onClickSound != null) onClickSound.run();

            if (onIngredientUsed != null) onIngredientUsed.run();
        });

        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(120);
        progressBar.setPrefHeight(20);
        progressBar.setVisible(false);
        progressBar.setStyle("-fx-accent: #8b6f5e;");
    }

    public void refill() {
        busy[0] = true;
        progressBar.setProgress(0);
        progressBar.setVisible(true);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(progressBar.progressProperty(), 1)
                )
        );
        timeline.setOnFinished(event -> {
            inventory.refillIngredient(inventoryKey);
            imageView.setImage(ImageLoader.load(inventory.getIngredient(inventoryKey).getImagePath(), width, height));
            progressBar.setVisible(false);
            busy[0] = false;
        });
        timeline.play();
    }

    public ImageView getImageView() { return imageView; }
    public ProgressBar getProgressBar() { return progressBar; }
}