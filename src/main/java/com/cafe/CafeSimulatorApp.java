package com.cafe;

import java.io.IOException;

import com.cafe.managers.GameManager;
import com.cafe.managers.InventoryManager;
import com.cafe.utils.ImageLoader;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class CafeSimulatorApp extends Application {

    GameManager gameManager = new GameManager();
    InventoryManager inventory = gameManager.getInventoryManager();

    private MediaPlayer backgroundMusic;

    @Override
    public void start(Stage stage) throws IOException {

        String backgroundSong = getClass().getResource("/audio/cafebackgroundsong.mp3").toString();
        String clink = getClass().getResource("/audio/CupclinkSound.mp3").toString();
        String matchaBagCrunch = getClass().getResource("/audio/matchaBag.mp3").toString();
        String waterSound = getClass().getResource("/audio/waterSound.mp3").toString();

        Media song = new Media(backgroundSong);
        backgroundMusic = new MediaPlayer(song);
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(0.15);
        backgroundMusic.play();

        // for cup clink sound
        MediaPlayer clinkSound = new MediaPlayer(new Media(clink));

        // for matchaBagCrunch
        MediaPlayer matchaBagCrunchSound = new MediaPlayer(new Media(matchaBagCrunch));

        // for milkSound
        MediaPlayer waterPourSound = new MediaPlayer(new Media(waterSound));


        boolean[] coffeeBusy = { false };
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

        Image coffee_full = ImageLoader.load("coffeebeans.png");
        ImageView coffee = new ImageView(coffee_full);

        coffee.setOnMouseEntered(e -> {
            if (!coffeeBusy[0]) {
                coffee.setEffect(glow);
            }
            else{
                coffee.setEffect(null);
            }
        });

        coffee.setOnMouseExited(e -> {
            coffeeBusy[0] = false;
            if (!coffeeBusy[0]) {
                coffee.setEffect(null);
            }
        });

        StackPane coffeeStack = new StackPane(coffee);

        coffeeStack.setTranslateX(110);
        coffeeStack.setTranslateY(60);

        coffee.setOnMouseClicked(e -> {
            System.out.println("Clicked coffee");
            coffeeBusy[0] = true;
            coffee.setEffect(null);
            inventory.useIngredient("espresso");
            if(!inventory.hasIngredient("espresso")){
                System.out.println("coffee beans are empty!");
                //   coffee.setImage(ImageLoader.load("coffeebeans2.png"))
            }
        });

        /* MILK */

        boolean[] milkBusy = { false };
        Image milk_full = ImageLoader.load("milk.png");
        ImageView milk = new ImageView(milk_full);

        milk.setPickOnBounds(false);
        milk.setTranslateX(-215);
        milk.setTranslateY(20);

        milk.setOnMouseEntered(e -> {
            if (!milkBusy[0]) {
                milk.setEffect(glow);
            }
            else{
                milk.setEffect(null);
            }
        });

        milk.setOnMouseExited(e -> {
            milkBusy[0] = false;
            if (!milkBusy[0]) {
                milk.setEffect(null);
            }
        });

        milk.setOnMouseClicked(e -> {
            System.out.println("clicked on milk!");
            milkBusy[0] = true;
            milk.setEffect(null);
            inventory.useIngredient("milk");
            if(!inventory.hasIngredient("milk")){
                System.out.println("milk is empty!");
                //   coffee.setImage(ImageLoader.load("coffeebeans2.png"))
            }
        });

        /* SUGAR */

        boolean[] sugarBusy = { false };

        Image sugar_full = ImageLoader.load("SugarBowl.png", 200, 200);
        ImageView sugar = new ImageView(sugar_full);

        sugar.setPickOnBounds(false); 
        sugar.setTranslateX(-280);
        sugar.setTranslateY(80);

        sugar.setOnMouseEntered(e -> {
            if (!sugarBusy[0]) {
                sugar.setEffect(glow);
            }
            else{
                sugar.setEffect(null);
            }
        });

        sugar.setOnMouseExited(e -> {
            sugarBusy[0] = false;
            if (!sugarBusy[0]) {
                sugar.setEffect(null);
            }
        });

        sugar.setOnMouseClicked(e -> {
            System.out.println("Clicked on sugar");
            sugarBusy[0] = true;
            sugar.setEffect(null);
            inventory.useIngredient("sugar");
            if(!inventory.hasIngredient("sugar")){
                System.out.println("sugar is empty!");
                sugar.setImage(ImageLoader.load(inventory.getIngredient("sugar").getImagePath(), 200, 200));
            }

        });

        /* MATCHA */
        boolean[] matchaBusy = { false };

        Image matcha_full = ImageLoader.load("matchafull.png", 280, 340);
        ImageView matcha = new ImageView(matcha_full);

        matcha.setPickOnBounds(false);
        matcha.setTranslateX(260);
        matcha.setTranslateY(70);

        matcha.setOnMouseEntered(e -> {
            if (!matchaBusy[0]) {
                matcha.setEffect(glow);
            }
            else{
                matcha.setEffect(null);
            }
        });

        matcha.setOnMouseExited(e -> {
            matchaBusy[0] = false;
            if (!matchaBusy[0]) {
                matcha.setEffect(null);
            }
        });

        matcha.setOnMouseClicked(e -> {
            System.out.println("clicked on matcha!");
            matchaBusy[0] = true;
            matcha.setEffect(null);
            matchaBagCrunchSound.seek(matchaBagCrunchSound.getStartTime());
            matchaBagCrunchSound.play();
            inventory.useIngredient("matcha");
            if(!inventory.hasIngredient("matcha")){
                System.out.println("matcha is empty!");
                matcha.setImage(ImageLoader.load(inventory.getIngredient("matcha").getImagePath(), 280, 340));
            }
        });

        /* WATER */

        boolean[] waterBusy = { false };

        Image water_full = ImageLoader.load("waterfull.png", 150, 180);
        ImageView water = new ImageView(water_full);

        water.setPickOnBounds(false);
        water.setTranslateX(-130);
        water.setTranslateY(-40);

        water.setOnMouseEntered(e -> {
            if (!waterBusy[0]) {
                water.setEffect(glow);
            }
            else{
                water.setEffect(null);
            }
        });

        water.setOnMouseExited(e -> {
            waterBusy[0] = false;
            if (!waterBusy[0]) {
                water.setEffect(null);
            }
        });

        water.setOnMouseClicked(e -> {
            System.out.println("Clicked on water");
            waterBusy[0] = true;
            water.setEffect(null);
            waterPourSound.seek(waterPourSound.getStartTime());
            waterPourSound.play();
            inventory.useIngredient("water");
            if(!inventory.hasIngredient("water")){
                System.out.println("water is empty!");
                water.setImage(ImageLoader.load(inventory.getIngredient("water").getImagePath(), 150, 180));
            }
        });

        /* CUPS RACK */

        Image cups_stack = ImageLoader.load("Cups_rack.png", 300, 300);
        ImageView cups = new ImageView(cups_stack);

        cups.setPickOnBounds(false);
        cups.setOnMouseExited(e -> cups.setImage(cups_stack));
        cups.setTranslateX(-140);
        cups.setTranslateY(-250);
        cups.setRotate(0);

        cups.setOnMouseClicked(e -> {
            clinkSound.seek(clinkSound.getStartTime());
            clinkSound.play();
        });

        // the StackPane lets us layer images on top of each other
        StackPane root = new StackPane();
        root.getChildren().add(backgroundView);
        //root.getChildren().add(cup_stack);
        root.getChildren().add(coffeeStack);
        root.getChildren().add(water);
        root.getChildren().add(milk);
        root.getChildren().add(sugar);
        root.getChildren().add(cups);
        root.getChildren().add(plant);
        root.getChildren().add(matcha);

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