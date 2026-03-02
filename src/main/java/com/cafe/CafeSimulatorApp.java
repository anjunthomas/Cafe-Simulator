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
    private javafx.scene.layout.HBox spoonContainer;
    private javafx.scene.control.Label patienceLabel;
    private javafx.scene.control.Label orderLabel;
    private ProgressBar patienceBar;
    private javafx.scene.control.Label satisfactionLabel;
    private javafx.scene.control.Label satisfactionScore;
    private javafx.scene.control.Label errorLabel;

    private void showError(String message) {
        /*errorLabel.setText(message);
        errorLabel.setVisible(true);

        Timeline hideError = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            errorLabel.setVisible(false);
        }));
        hideError.play();*/
    	errorLabel.setText(message);
        errorLabel.setStyle(
            "-fx-background-color: rgba(230, 57, 70, 0.9);" + // Crimson Red
            "-fx-text-fill: white;" + 
            "-fx-padding: 10 20;" + 
            "-fx-background-radius: 20;" + 
            "-fx-font-weight: bold;" +
            "-fx-font-size: 18px;"
        );

        errorLabel.setVisible(true);
        
        // Auto-hide after 2 seconds
        Timeline hide = new Timeline(new KeyFrame(Duration.seconds(2), e -> errorLabel.setVisible(false)));
        hide.play();
    }

    private void updateCustomerDisplay(String feedbackPath) {
        com.cafe.models.Customer current = gameManager.getCurrentCustomer();

        if (current != null) {
            customerSprite.setImage(ImageLoader.load(current.getSpritePath(), 450, 450));
            customerSprite.setVisible(true);
            messageBubble.setVisible(true);
            
            // Updates the numeric satisfaction score
            satisfactionScore.setText(String.valueOf(gameManager.getSatisfiedCount()));

            if (feedbackPath != null) {
                // Hide spoons when showing feedback (Heart/Broken Heart)
                spoonContainer.setVisible(false); 
                drinkIcon.setImage(ImageLoader.load(feedbackPath, 100, 100));
                drinkIcon.setVisible(true);
                orderLabel.setVisible(false); 
            } else {
                // DEFAULT: Show the drink order
                String drinkName = current.getOrder().getName();
                String drinkImagePath = "";
                if (drinkName.contains("Coffee")) drinkImagePath = "coffee2.png";
                else if (drinkName.contains("Latte") && !drinkName.contains("Matcha")) drinkImagePath = "latte.png";
                else if (drinkName.contains("Matcha")) drinkImagePath = "matcha.png";
                else if (drinkName.contains("Cold Brew")) drinkImagePath = "coldBrew.png";

                if (!drinkImagePath.isEmpty()) {
                    drinkIcon.setImage(ImageLoader.load(drinkImagePath, 100, 100));
                    drinkIcon.setVisible(true);
                }
                orderLabel.setText("Wants: " + drinkName);
                orderLabel.setVisible(true);

                spoonContainer.getChildren().clear(); 
                
                spoonContainer.getChildren().clear();
                int sugarNeeded = 0;

                if (drinkName.contains("1")) {
                    sugarNeeded = 1;
                } else if (drinkName.contains("2")) {
                    sugarNeeded = 2;
                }

                // 3. Draw the spoons based on the parsed number
                if (sugarNeeded > 0) {
                    for (int i = 0; i < sugarNeeded; i++) {
                        ImageView spoon = new ImageView(ImageLoader.load("spoon.png", 80, 80));
                        DropShadow spoonBorder = new DropShadow();
                        spoonBorder.setColor(Color.BLACK); 
                        spoonBorder.setRadius(5);
                        spoonBorder.setSpread(0.4); 
                        spoon.setEffect(spoonBorder);
                        spoonContainer.getChildren().add(spoon);
                    }
                    spoonContainer.setVisible(true);
                } else {
                    spoonContainer.setVisible(false);
                }
            } 

            patienceLabel.setText("Patience: " + current.getPatience());
            patienceBar.setProgress((double)current.getPatience() / current.getMaxPatience());
            patienceBar.setVisible(true);
            patienceLabel.setVisible(true);

        } else {
        
            customerSprite.setVisible(false);
            messageBubble.setVisible(false);
            drinkIcon.setVisible(false);
            patienceBar.setVisible(false);
            patienceLabel.setVisible(false);
            orderLabel.setVisible(false);
            spoonContainer.setVisible(false);
        }
    }


    @Override
    public void start(Stage stage) throws IOException {

        Image background = ImageLoader.load("newbackground.png", 1000, 1000);
        ImageView backgroundView = new ImageView(background);

        AudioManager audio = new AudioManager(getClass());
        audio.playBackground();
        
        spoonContainer = new javafx.scene.layout.HBox(60); 
        spoonContainer.setTranslateX(-50); // Center it with the bubble
        spoonContainer.setTranslateY(500); // Position inside the bubble
        spoonContainer.setVisible(false);
        spoonContainer.setMouseTransparent(true);

        javafx.scene.control.Label espressoCount = new javafx.scene.control.Label();
        espressoCount.setTranslateX(110);
        espressoCount.setTranslateY(120);

        javafx.scene.control.Label milkCount = new javafx.scene.control.Label();
        milkCount.setTranslateX(-200);
        milkCount.setTranslateY(80);

        javafx.scene.control.Label matchaCount = new javafx.scene.control.Label();
        matchaCount.setTranslateX(260);
        matchaCount.setTranslateY(130);

        javafx.scene.control.Label waterCount = new javafx.scene.control.Label();
        waterCount.setTranslateX(-130);
        waterCount.setTranslateY(20);

        javafx.scene.control.Label sugarCount = new javafx.scene.control.Label();
        sugarCount.setTranslateX(-280);
        sugarCount.setTranslateY(110);

        Timeline gameLoop = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            gameManager.update();
            updateCustomerDisplay(null);
            espressoCount.setText("espresso: " + inventory.getIngredient("espresso").getCurrentAmount());
            milkCount.setText("milk: " + inventory.getIngredient("milk").getCurrentAmount());
            matchaCount.setText("matcha: " + inventory.getIngredient("matcha").getCurrentAmount());
            waterCount.setText("water: " + inventory.getIngredient("water").getCurrentAmount());
            sugarCount.setText("sugar: " + inventory.getIngredient("sugar").getCurrentAmount());
        }));
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        gameLoop.play();

        customerSprite = new ImageView();
        customerSprite.setTranslateX(260);
        customerSprite.setTranslateY(300);

        orderLabel = new javafx.scene.control.Label();
        orderLabel.setTranslateX(500);
        orderLabel.setTranslateY(0);
        orderLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");

        patienceBar = new ProgressBar();
        patienceBar.setTranslateX(120);
        patienceBar.setTranslateY(470);
/*
        satisfactionLabel = new javafx.scene.control.Label("Satisfied: 0");
        satisfactionLabel.setTranslateX(-370);
        satisfactionLabel.setTranslateY(-300);
*/
        messageBubble = new ImageView(ImageLoader.load("bubble.png", 450, 300));
        messageBubble.setTranslateX(-50);
        messageBubble.setTranslateY(270);
        messageBubble.setVisible(false);
        messageBubble.setMouseTransparent(true);
        
// Drink icon inside bubble
        drinkIcon = new ImageView();
        drinkIcon.setTranslateX(-50);
        drinkIcon.setTranslateY(210);
        drinkIcon.setVisible(false);
        drinkIcon.setMouseTransparent(true);
        
        errorLabel = new javafx.scene.control.Label();
        errorLabel.setTranslateX(0);
        errorLabel.setTranslateY(-350);
        errorLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: red; -fx-font-weight: bold;");
        errorLabel.setVisible(false);

        patienceLabel = new javafx.scene.control.Label();
        patienceLabel.setTranslateX(120);
        patienceLabel.setTranslateY(420);

        final String[] playerRecipe = {""};
      
        spoonContainer.setTranslateX(-50); 
        spoonContainer.setTranslateY(280); 
        spoonContainer.setAlignment(javafx.geometry.Pos.CENTER);

        Image background = ImageLoader.load("newbackground.png", 1000, 1000);
        ImageView backgroundView = new ImageView(background);

        /* PLANT */

        Image plant_decor = ImageLoader.load("Plant.png", 80, 80);
        ImageView plant = new ImageView(plant_decor);

        plant.setTranslateX(360);
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
                () -> { playerRecipe[0] += "espresso,"; inventory.useIngredient("espresso"); },
                () -> showError("Espresso is out! Please refill to use!"));
        coffeeView.getImageView().setTranslateX(110);
        coffeeView.getImageView().setTranslateY(60);
        coffeeView.getProgressBar().setTranslateX(110);
        coffeeView.getProgressBar().setTranslateY(90);

        /* MILK */
        IngredientView milkView = new IngredientView("milk", "milk.png", 150, 150, inventory, glow,
                () -> audio.playMilk(),
                () -> { playerRecipe[0] += "milk,"; inventory.useIngredient("milk"); },
                () -> showError("Milk is out! Please refill to use!"));
        milkView.getImageView().setTranslateX(-200);
        milkView.getImageView().setTranslateY(20);
        milkView.getProgressBar().setTranslateX(-215);
        milkView.getProgressBar().setTranslateY(50);


        /* SUGAR */
        IngredientView sugarView = new IngredientView("sugar", "sugar.png", 200, 200, inventory, glow,
                () -> audio.playSugarBagSound(),
                () -> { playerRecipe[0] += "sugar,"; inventory.useIngredient("sugar"); },
                () -> showError("Sugar is out! Please refill to use!"));
        sugarView.getImageView().setTranslateX(-280);
        sugarView.getImageView().setTranslateY(80);
        sugarView.getProgressBar().setTranslateX(-280);
        sugarView.getProgressBar().setTranslateY(110);

        /* MATCHA */
        IngredientView matchaView = new IngredientView("matcha", "matchafull.png", 280, 340, inventory, glow,
                () -> audio.playMatchaCrunch(),
                () -> { playerRecipe[0] += "matcha,"; inventory.useIngredient("matcha"); },
                () -> showError("Matcha is out! Please refill to use!"));
        matchaView.getImageView().setTranslateX(260);
        matchaView.getImageView().setTranslateY(70);
        matchaView.getProgressBar().setTranslateX(260);
        matchaView.getProgressBar().setTranslateY(100);

        /* WATER */

        IngredientView waterView = new IngredientView("water", "waterfull.png", 150, 180, inventory, glow,
                () -> audio.playWater(),
                () -> { playerRecipe[0] += "water,"; inventory.useIngredient("water"); },
                () -> showError("Water is out! Please refill to use!"));
        waterView.getImageView().setTranslateX(-130);
        waterView.getImageView().setTranslateY(-40);
        waterView.getProgressBar().setTranslateX(-130);
        waterView.getProgressBar().setTranslateY(-10);

        /* CUPS RACK */

        Image cups_stack = ImageLoader.load("Cups_rack.png", 300, 300);
        ImageView cups = new ImageView(cups_stack);
        
        DropShadow goldGlow = new DropShadow();
        goldGlow.setColor(Color.GOLD);
        goldGlow.setRadius(20);
        goldGlow.setSpread(0.6);

        cups.setPickOnBounds(false);
        cups.setOnMouseExited(e -> cups.setImage(cups_stack));
        cups.setTranslateX(-129);
        cups.setTranslateY(-251);
        cups.setRotate(0);
        
        cups.setOnMouseEntered(e -> cups.setEffect(goldGlow));
        cups.setOnMouseExited(e -> cups.setEffect(null));

        cups.setOnMouseClicked(e -> {
            audio.playClink();
            playerRecipe[0] += "cups,";
        });

        /* setting up refill functionality with a popup  */
        javafx.stage.Popup refillPopup = new javafx.stage.Popup();

        String cozyButtonStyle =
            "-fx-background-color: #d7b899;" +   // latte color
            "-fx-text-fill: #4a2e1f;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 15;" +
            "-fx-border-radius: 15;" +
            "-fx-border-color: #8b6f5e;" +
            "-fx-padding: 8 15 8 15;";

        javafx.scene.layout.VBox popupContent = new javafx.scene.layout.VBox(10);
        popupContent.setStyle("-fx-background-color: #f5e6d3; -fx-padding: 15; -fx-border-color: #8b6f5e; -fx-border-width: 2;");

        javafx.scene.control.Label title = new javafx.scene.control.Label("Refill Ingredients");
        title.setStyle(
            "-fx-font-size: 20px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #5c3b28;"   // dark mocha
        );

        javafx.scene.control.Button refillMilk = new javafx.scene.control.Button("Refill Milk");
        javafx.scene.control.Button refillCoffee = new javafx.scene.control.Button("Refill Coffee");
        javafx.scene.control.Button refillSugar = new javafx.scene.control.Button("Refill Sugar");
        javafx.scene.control.Button refillMatcha = new javafx.scene.control.Button("Refill Matcha");
        javafx.scene.control.Button refillWater = new javafx.scene.control.Button("Refill Water");
        javafx.scene.control.Button closePopup = new javafx.scene.control.Button("Close");

        refillMilk.setOnMouseClicked(e -> milkView.refill());
        refillMilk.setOnMouseEntered(e -> refillMilk.setEffect(glow));
        refillMilk.setOnMouseExited(e -> refillMilk.setEffect(null));

        refillCoffee.setOnMouseClicked(e -> coffeeView.refill());
        refillCoffee.setOnMouseEntered(e -> refillCoffee.setEffect(glow));
        refillCoffee.setOnMouseExited(e -> refillCoffee.setEffect(null));

        refillSugar.setOnMouseClicked(e -> sugarView.refill());
        refillSugar.setOnMouseEntered(e -> refillSugar.setEffect(glow));
        refillSugar.setOnMouseExited(e -> refillSugar.setEffect(null));

        refillMatcha.setOnMouseClicked(e -> matchaView.refill());
        refillMatcha.setOnMouseEntered(e -> refillMatcha.setEffect(glow));
        refillMatcha.setOnMouseExited(e -> refillMatcha.setEffect(null));

        refillWater.setOnMouseClicked(e -> waterView.refill());
        refillWater.setOnMouseEntered(e -> refillWater.setEffect(glow));
        refillWater.setOnMouseExited(e -> refillWater.setEffect(null));

        closePopup.setOnMouseClicked(e -> refillPopup.hide());
        closePopup.setOnMouseEntered(e -> closePopup.setEffect(glow));
        closePopup.setOnMouseExited(e -> closePopup.setEffect(null));

        refillMilk.setStyle(cozyButtonStyle);
        refillCoffee.setStyle(cozyButtonStyle);
        refillSugar.setStyle(cozyButtonStyle);
        refillMatcha.setStyle(cozyButtonStyle);
        refillWater.setStyle(cozyButtonStyle);
        closePopup.setStyle(cozyButtonStyle);
        
        popupContent.getChildren().addAll(title, refillMilk, refillCoffee, refillSugar, refillMatcha, refillWater, closePopup);
        popupContent.setStyle(
            "-fx-background-color: #f8f1e4;" +   // warm cream
            "-fx-padding: 20;" +
            "-fx-border-color: #a67c52;" +       // soft coffee brown
            "-fx-border-width: 3;" +
            "-fx-background-radius: 20;" +
            "-fx-border-radius: 20;"
        );
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
                updateCustomerDisplay(null);
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
        root.getChildren().addAll(customerSprite, orderLabel, patienceBar, messageBubble, serveBtn, drinkIcon, patienceLabel, errorLabel, spoonContainer);
        serveBtn.setVisible(false);
        refillBtn.setVisible(false);
        
     // Create Image Buttons with matching sizes
        ImageView refillIcon = new ImageView(ImageLoader.load("refill.png", 195, 195));
        refillIcon.setTranslateX(380); 
        refillIcon.setTranslateY(-400);
        refillIcon.setPickOnBounds(true); 

        ImageView serveIcon = new ImageView(ImageLoader.load("serve.png", 220, 220));
        serveIcon.setTranslateX(390);
        serveIcon.setTranslateY(-330); 
        serveIcon.setPickOnBounds(true);
        
        // Satisfaction Number
        //javafx.scene.control.Label satisfactionScore = new javafx.scene.control.Label("0");
        satisfactionScore = new javafx.scene.control.Label("0");
        DropShadow scoreGlow = new DropShadow();
        scoreGlow.setRadius(15.0);
        scoreGlow.setColor(Color.rgb(245, 230, 211, 0.5));
        satisfactionScore.setEffect(scoreGlow);
        satisfactionScore.setStyle("-fx-font-size: 64px; -fx-text-fill: #F5E6D3; -fx-font-weight: bold;");
        satisfactionScore.setTranslateX(-300); // Placed to the right of the smile
        satisfactionScore.setTranslateY(-360);
        // The Smile Icon
        satisfactionView = new ImageView(ImageLoader.load("smiley.png", 150, 150));
        satisfactionView.setTranslateX(-385); // Positioned over the bear
        satisfactionView.setTranslateY(-360); 
        satisfactionView.setVisible(true);
        

     	refillIcon.setOnMouseClicked(e -> {
     		if (!refillPopup.isShowing()) refillPopup.show(stage);
     	});
     	serveIcon.setOnMouseClicked(e -> {
     	    com.cafe.models.Customer current = gameManager.getCurrentCustomer();
     	    if (current != null) {
     	        audio.playClink();
     	        String recipe = playerRecipe[0].replaceAll(",$", "");
     	        
     	        // Checks if the recipe is correct
     	        boolean success = gameManager.serveCustomer(current, recipe);

     	        // TRIGGER IMAGES: Show heart for success, broken_heart for failure
                drinkIcon.setImage(ImageLoader.load(success ? "Heart.png" : "brokenheart.png", 100, 100));

     	        // Wait 1 second for the player to see the result, then clear it
     	        Timeline feedbackTimer = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
     	            updateCustomerDisplay(null); // Return to showing order/next customer
     	        }));
     	        feedbackTimer.play();

     	        playerRecipe[0] = ""; 
     	    }
     	});

     	// 3. Add them to the root - extra ones
     	root.getChildren().addAll(refillIcon, serveIcon, satisfactionView, satisfactionScore);
        root.getChildren().addAll(espressoCount, milkCount, matchaCount, waterCount, sugarCount);
     	
     	
        root.setStyle("-fx-background-color: #2E1A47;");
        Scene scene = new Scene(root, 900, 1000);
        stage.setTitle("Cafe Simulator!");
        stage.setScene(scene);
        stage.show();
        root.getChildren().remove(messageBubble);
        root.getChildren().remove(drinkIcon);
        root.getChildren().remove(spoonContainer);
        root.getChildren().addAll(messageBubble, drinkIcon, spoonContainer);
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