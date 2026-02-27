package com.cafe;

import java.io.IOException;

import com.cafe.managers.GameManager;
import com.cafe.managers.InventoryManager;
import com.cafe.utils.AudioManager;
import com.cafe.utils.ImageLoader;

import com.cafe.utils.IngredientView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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

    GameManager gameManager = new GameManager();
    InventoryManager inventory = gameManager.getInventoryManager();

    private ImageView customerSprite;

    private ImageView messageBubble;

    private ImageView drinkIcon;
    
    private ImageView satisfactionView;

    private javafx.scene.control.Label patienceLabel;
    private javafx.scene.control.Label orderLabel;
    private ProgressBar patienceBar;
    private javafx.scene.control.Label satisfactionLabel;

    private javafx.scene.control.Label errorLabel;

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);

        Timeline hideError = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            errorLabel.setVisible(false);
        }));
        hideError.play();
    }

    private void updateCustomerDisplay() {
        com.cafe.models.Customer current = gameManager.getCurrentCustomer();

        if (current != null) {
            customerSprite.setImage(ImageLoader.load(current.getSpritePath(), 450, 450));
            customerSprite.setVisible(true);

            orderLabel.setText("Wants: " + current.getOrder().getName());
            orderLabel.setVisible(true);

            messageBubble.setVisible(true);

            String drinkName = current.getOrder().getName();
            String drinkImagePath = "";

            if (drinkName.contains("Coffee")) {
                drinkImagePath = "coffee.png";
            } else if (drinkName.contains("Latte") && !drinkName.contains("Matcha")) {
                drinkImagePath = "latte.png";
            } else if (drinkName.contains("Matcha")) {
                drinkImagePath = "matcha.png";
            } else if (drinkName.contains("Cold Brew")){
                drinkImagePath = "coldBrew.png";
            }

            if (!drinkImagePath.isEmpty()) {
                drinkIcon.setImage(ImageLoader.load(drinkImagePath, 100, 100));
                drinkIcon.setVisible(true);
            }

            // Show patience as text
            patienceLabel.setText("Patience: " + current.getPatience());
            patienceLabel.setVisible(true);

            patienceBar.setProgress((double)current.getPatience() / current.getMaxPatience());
            patienceBar.setVisible(true);


        } else {
            customerSprite.setVisible(false);
            patienceLabel.setVisible(false);
            patienceBar.setVisible(false);
            messageBubble.setVisible(false);
            drinkIcon.setVisible(false);
        }

        satisfactionLabel.setText("Satisfied: " + gameManager.getSatisfiedCount());
    }


    @Override
    public void start(Stage stage) throws IOException {

        AudioManager audio = new AudioManager(getClass());
        audio.playBackground();

        javafx.scene.control.Label espressoCount = new javafx.scene.control.Label();
        espressoCount.setTranslateX(110);
        espressoCount.setTranslateY(120);

        javafx.scene.control.Label milkCount = new javafx.scene.control.Label();
        milkCount.setTranslateX(-215);
        milkCount.setTranslateY(80);

        javafx.scene.control.Label matchaCount = new javafx.scene.control.Label();
        matchaCount.setTranslateX(260);
        matchaCount.setTranslateY(130);

        javafx.scene.control.Label waterCount = new javafx.scene.control.Label();
        waterCount.setTranslateX(-130);
        waterCount.setTranslateY(20);

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            gameManager.update();
            updateCustomerDisplay();
            espressoCount.setText("espresso: " + inventory.getIngredient("espresso").getCurrentAmount());
            milkCount.setText("milk: " + inventory.getIngredient("milk").getCurrentAmount());
            matchaCount.setText("matcha: " + inventory.getIngredient("matcha").getCurrentAmount());
            waterCount.setText("water: " + inventory.getIngredient("water").getCurrentAmount());
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        customerSprite = new ImageView();
        customerSprite.setTranslateX(260);
        customerSprite.setTranslateY(300);

        orderLabel = new javafx.scene.control.Label();
        orderLabel.setTranslateX(120);
        orderLabel.setTranslateY(450);

        patienceBar = new ProgressBar();
        patienceBar.setTranslateX(120);
        patienceBar.setTranslateY(470);

        satisfactionLabel = new javafx.scene.control.Label("Satisfied: 0");
        satisfactionLabel.setTranslateX(-370);
        satisfactionLabel.setTranslateY(-300);

        messageBubble = new ImageView(ImageLoader.load("thought.png", 320, 220));
        messageBubble.setTranslateX(40);
        messageBubble.setTranslateY(250);
        messageBubble.setVisible(false);

// Drink icon inside bubble
        drinkIcon = new ImageView();
        drinkIcon.setTranslateX(20);
        drinkIcon.setTranslateY(200);
        drinkIcon.setVisible(false);

        errorLabel = new javafx.scene.control.Label();
        errorLabel.setTranslateX(0);
        errorLabel.setTranslateY(-350);
        errorLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red; -fx-font-weight: bold;");
        errorLabel.setVisible(false);

        patienceLabel = new javafx.scene.control.Label();
        patienceLabel.setTranslateX(120);
        patienceLabel.setTranslateY(420);

        final String[] playerRecipe = {""};



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
        IngredientView coffeeView = new IngredientView("espresso", "coffeebeans.png", 300, 340, inventory, glow,
                () -> audio.playCoffeeCrunch(),
                () -> playerRecipe[0] += "espresso,",
                () -> showError("Espresso is out! Please refill to use!"));
        coffeeView.getImageView().setTranslateX(110);
        coffeeView.getImageView().setTranslateY(60);
        coffeeView.getProgressBar().setTranslateX(110);
        coffeeView.getProgressBar().setTranslateY(90);

        /* MILK */
        IngredientView milkView = new IngredientView("milk", "milk.png", 280, 320, inventory, glow,
                () -> audio.playMilk(),
                () -> playerRecipe[0] += "milk,",
                () -> showError("Milk is out! Please refill to use!"));
        milkView.getImageView().setTranslateX(-215);
        milkView.getImageView().setTranslateY(20);
        milkView.getProgressBar().setTranslateX(-215);
        milkView.getProgressBar().setTranslateY(50);


        /* SUGAR */
        IngredientView sugarView = new IngredientView("sugar", "SugarBowl.png", 200, 200, inventory, glow,
                () -> audio.playSugarBagSound(),
                () -> playerRecipe[0] += "sugar,",
                () -> showError("Sugar is out! Please refill to use!"));
        sugarView.getImageView().setTranslateX(-280);
        sugarView.getImageView().setTranslateY(80);
        sugarView.getProgressBar().setTranslateX(-280);
        sugarView.getProgressBar().setTranslateY(110);

        /* MATCHA */
        IngredientView matchaView = new IngredientView("matcha", "matchafull.png", 280, 340, inventory, glow,
                () -> audio.playMatchaCrunch(),
                () -> playerRecipe[0] += "matcha,",
                () -> showError("Matcha is out! Please refill to use!"));
        matchaView.getImageView().setTranslateX(260);
        matchaView.getImageView().setTranslateY(70);
        matchaView.getProgressBar().setTranslateX(260);
        matchaView.getProgressBar().setTranslateY(100);

        /* WATER */

        IngredientView waterView = new IngredientView("water", "waterfull.png", 150, 180, inventory, glow,
                () -> audio.playWater(),
                () -> playerRecipe[0] += "water,",
                () -> showError("Water is out! Please refill to use!"));
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
            playerRecipe[0] += "cups,";
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

        javafx.scene.control.Button serveBtn = new javafx.scene.control.Button("Serve");
        serveBtn.setTranslateX(370);
        serveBtn.setTranslateY(-390);
        serveBtn.setOnMouseClicked(e -> {
            com.cafe.models.Customer current = gameManager.getCurrentCustomer();
            if (current != null) {
                String recipe = playerRecipe[0].replaceAll(",$", ""); // Remove trailing comma
                boolean success = gameManager.serveCustomer(current, recipe);

                playerRecipe[0] = ""; // Reset
                updateCustomerDisplay();
            }
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
        root.getChildren().addAll(customerSprite, orderLabel, patienceBar, satisfactionLabel, messageBubble, serveBtn, drinkIcon, patienceLabel, errorLabel);
        serveBtn.setVisible(false);
        refillBtn.setVisible(false);
        
     // 1. Create Image Buttons with matching sizes
        ImageView refillIcon = new ImageView(ImageLoader.load("refill.png", 195, 195));
        refillIcon.setTranslateX(380); 
        refillIcon.setTranslateY(-400);
        refillIcon.setPickOnBounds(true); 

        ImageView serveIcon = new ImageView(ImageLoader.load("serve.png", 220, 220));
        serveIcon.setTranslateX(390);
        serveIcon.setTranslateY(-330); 
        serveIcon.setPickOnBounds(true);

        // 2. Setup the Smile Icon
        satisfactionView = new ImageView(ImageLoader.load("smile.png", 150, 150));
        satisfactionView.setTranslateX(-375); // Positioned over the bear
        satisfactionView.setTranslateY(-360); 
        satisfactionView.setVisible(true);

     	// 2. Add the Click Logic (This uses the logic from the Master branch)
     	refillIcon.setOnMouseClicked(e -> {
     		if (!refillPopup.isShowing()) refillPopup.show(stage);
     	});

        serveIcon.setOnMouseClicked(e -> {
            com.cafe.models.Customer current = gameManager.getCurrentCustomer();
            if (current != null) {
                audio.playClink();

                String recipe = playerRecipe[0].replaceAll(",$", "");
                boolean success = gameManager.serveCustomer(current, recipe);
                coffeeView.getImageView().setImage(ImageLoader.load(inventory.getIngredient("espresso").getImagePath(), 300, 340));
                milkView.getImageView().setImage(ImageLoader.load(inventory.getIngredient("milk").getImagePath(), 280, 320));
                sugarView.getImageView().setImage(ImageLoader.load(inventory.getIngredient("sugar").getImagePath(), 200, 200));
                matchaView.getImageView().setImage(ImageLoader.load(inventory.getIngredient("matcha").getImagePath(), 280, 340));
                waterView.getImageView().setImage(ImageLoader.load(inventory.getIngredient("water").getImagePath(), 150, 180));
                playerRecipe[0] = "";
                updateCustomerDisplay();
            }
        });

     	// 3. Add them to the root - extra ones
     	root.getChildren().addAll(refillIcon, serveIcon, satisfactionView);
        root.getChildren().addAll(espressoCount, milkCount, matchaCount, waterCount);
     	
     	
        root.setStyle("-fx-background-color: #FFCC80;");
        Scene scene = new Scene(root, 900, 1000);
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