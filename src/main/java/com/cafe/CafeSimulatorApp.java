package com.cafe;

import java.io.IOException;

import com.cafe.managers.GameManager;
import com.cafe.managers.InventoryManager;
import com.cafe.utils.AudioManager;
import com.cafe.utils.ImageLoader;

import com.cafe.utils.IngredientView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CafeSimulatorApp extends Application {

    GameManager gameManager = new GameManager();
    InventoryManager inventory = gameManager.getInventoryManager();

    @Override
    public void start(Stage stage) throws IOException {

        AudioManager audio = new AudioManager(getClass());
        audio.playBackground();

        Image background = ImageLoader.load("newbackground.png", 1000, 1000);
        ImageView backgroundView = new ImageView(background);

        /* PLANT */

        Image plant_decor = ImageLoader.load("Plant.png", 80, 80);
        ImageView plant = new ImageView(plant_decor);

        plant.setTranslateX(280);
        plant.setTranslateY(-30);

        /* CUPS */

        DropShadow glow = new DropShadow();
        glow.setColor(Color.GOLD);
        glow.setRadius(20);
        glow.setSpread(0.6);

        boolean[] pinkBusy = { false };
        boolean[] redBusy = { false };
        boolean[] blueBusy = { false };

        Image pink_cup = ImageLoader.load("pinkcup.png");
        Image red_cup = ImageLoader.load("redcup.png");
        Image blue_cup = ImageLoader.load("bluecup.png");

        ImageView pinkcup = new ImageView(pink_cup);
        ImageView redcup = new ImageView(red_cup);
        ImageView bluecup = new ImageView(blue_cup);

        //pinkcup.setTranslateX(-150);
        //pinkcup.setTranslateY(20);
        //redcup.setTranslateX(20);
       // redcup.setTranslateY(20);
        //bluecup.setTranslateX(20);
        //bluecup.setTranslateY(20);

        pinkcup.setOnMouseEntered(e -> {
            if (!pinkBusy[0]) {
                pinkcup.setEffect(glow);
            }
            else{
                pinkcup.setEffect(null);
            }
        });

        pinkcup.setOnMouseExited(e -> {
            pinkBusy[0] = false;
            if (!pinkBusy[0]) {
                pinkcup.setEffect(null);
            }
        });

        pinkcup.setOnMouseClicked(e -> {
            pinkBusy[0] = true;
            pinkcup.setEffect(null); 

        });

        StackPane cup_stack = new StackPane(pinkcup, redcup, bluecup);

        /* COFFEE */
        IngredientView coffeeView = new IngredientView("espresso", "coffeebeans.png", 300, 340, inventory, glow, () -> audio.playCoffeeCrunch());
        coffeeView.getImageView().setTranslateX(110);
        coffeeView.getImageView().setTranslateY(60);
        coffeeView.getProgressBar().setTranslateX(110);
        coffeeView.getProgressBar().setTranslateY(90);

        /* MILK */
        IngredientView milkView = new IngredientView("milk", "milk.png", 280, 320, inventory, glow, () -> audio.playMilk());
        milkView.getImageView().setTranslateX(-215);
        milkView.getImageView().setTranslateY(20);


        /* SUGAR */
        IngredientView sugarView = new IngredientView("sugar", "SugarBowl.png", 200, 200, inventory, glow, () -> audio.playSugarBagSound());
        sugarView.getImageView().setTranslateX(-280);
        sugarView.getImageView().setTranslateY(80);

        /* MATCHA */
        IngredientView matchaView = new IngredientView("matcha", "matchafull.png", 280, 340, inventory, glow, () -> audio.playMatchaCrunch());
        matchaView.getImageView().setTranslateX(260);
        matchaView.getImageView().setTranslateY(70);

        /* WATER */

        IngredientView waterView = new IngredientView("water", "waterfull.png", 150, 180, inventory, glow, () -> audio.playWater());
        waterView.getImageView().setTranslateX(-130);
        waterView.getImageView().setTranslateY(-40);
        waterView.getProgressBar().setTranslateX(-130);
        waterView.getProgressBar().setTranslateY(-10);

        /* CUPS RACK */

        Image cups_stack = ImageLoader.load("Cups_rack.png", 300, 300);
        ImageView cups = new ImageView(cups_stack);

        cups.setPickOnBounds(false);
        cups.setOnMouseExited(e -> cups.setImage(cups_stack));
        cups.setTranslateX(-140);
        cups.setTranslateY(-250);
        cups.setRotate(0);

        cups.setOnMouseClicked(e -> {
            audio.playClink();
        });

        /* setting up refill functionality with a popup  */
        javafx.stage.Popup refillPopup = new javafx.stage.Popup();

        javafx.scene.layout.VBox popupContent = new javafx.scene.layout.VBox(10);
        popupContent.setStyle("-fx-background-color: #f5e6d3; -fx-padding: 15; -fx-border-color: #8b6f5e; -fx-border-width: 2;");

        javafx.scene.control.Label title = new javafx.scene.control.Label("Refill Ingredients");

        javafx.scene.control.Button refillMilk = new javafx.scene.control.Button("Refill Milk");
        javafx.scene.control.Button refillCoffee = new javafx.scene.control.Button("Refill Coffee");
        javafx.scene.control.Button refillSugar = new javafx.scene.control.Button("Refill Sugar");
        javafx.scene.control.Button refillMatcha = new javafx.scene.control.Button("Refill Matcha");
        javafx.scene.control.Button refillWater = new javafx.scene.control.Button("Refill Water");
        javafx.scene.control.Button closePopup = new javafx.scene.control.Button("Close");

        refillMilk.setOnMouseClicked(e -> milkView.refill());

        refillCoffee.setOnMouseClicked(e -> coffeeView.refill());

        refillSugar.setOnMouseClicked(e -> sugarView.refill());

        refillMatcha.setOnMouseClicked(e -> matchaView.refill());

        refillWater.setOnMouseClicked(e -> waterView.refill());

        closePopup.setOnMouseClicked(e -> refillPopup.hide());
        popupContent.getChildren().addAll(title, refillMilk, refillCoffee, refillSugar, refillMatcha, refillWater, closePopup);
        refillPopup.getContent().add(popupContent);

        // the actual refill button
        javafx.scene.control.Button refillBtn = new javafx.scene.control.Button("Refill");
        refillBtn.setTranslateX(370);
        refillBtn.setTranslateY(-420);
        refillBtn.setOnMouseClicked(e -> {
            refillPopup.show(stage);
        });


        // the StackPane lets us layer images on top of each other
        StackPane root = new StackPane();
        root.getChildren().add(backgroundView);
        //root.getChildren().add(cup_stack);
        root.getChildren().add(coffeeView.getImageView());
        root.getChildren().add(coffeeView.getProgressBar());
        root.getChildren().add(waterView.getImageView());
        root.getChildren().add(waterView.getProgressBar());
        root.getChildren().add(milkView.getImageView());
        root.getChildren().add(milkView.getProgressBar());
        root.getChildren().add(sugarView.getImageView());
        root.getChildren().add(sugarView.getProgressBar());
        root.getChildren().add(cups);
        root.getChildren().add(plant);
        root.getChildren().add(matchaView.getImageView());
        root.getChildren().add(matchaView.getProgressBar());
        root.getChildren().add(refillBtn);

        Scene scene = new Scene(root, 900, 900);
        stage.setTitle("Cafe Simulator!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*InventoryManager inventory = new InventoryManager(); // this initializes the ingredients for us: puts them in the hashmap and adds the maxAmount

        // testing hasIngredient
        System.out.println("Has milk: " + inventory.hasIngredient("milk")); // true
        System.out.println("Has milk: " + inventory.hasIngredient("xyz")); // false

        System.out.println("Starting amount of milk: " + inventory.getIngredient("milk").getCurrentAmount());
        System.out.println("Starting amount of espresso: " + inventory.getIngredient("espresso").getCurrentAmount());
        System.out.println("Starting amount of cups: " + inventory.getIngredient("cups").getCurrentAmount());

        // testing useIngredient works
        inventory.useIngredient("milk");
        System.out.println("Milk after use: " + inventory.getIngredient("milk").getCurrentAmount()); // decrementing it from 10 to 9

        // Test canMakeRecipe
        System.out.println("Can make latte: " + inventory.canMakeRecipe("milk,espresso,cups")); // should print true, all the ingredients were initialized when we called the InventoryManager

        System.out.println("Making latte....!");
        // Test useIngredients to make a recipe
        inventory.useIngredients("milk,espresso,cups");
        System.out.println("Milk after recipe: " + inventory.getIngredient("milk").getCurrentAmount());
        System.out.println("Espresso after recipe: " + inventory.getIngredient("espresso").getCurrentAmount());
        System.out.println("Cups after recipe: " + inventory.getIngredient("cups").getCurrentAmount());

        // Test refill
        inventory.refillIngredient("milk"); // this will refill the ingredient passed in to the max amount (10 milk)
        System.out.println("Milk after refill: " + inventory.getIngredient("milk").getCurrentAmount()); */
        launch();
    }
}