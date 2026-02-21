package com.cafe.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private MediaPlayer backgroundMusic;
    private MediaPlayer clinkSound;
    private MediaPlayer matchaBagCrunchSound;
    private MediaPlayer waterPourSound;

    private MediaPlayer milkPourSound;

    private MediaPlayer coffeeGrindingSound;

    private MediaPlayer coffeeCrunchSound;

    private MediaPlayer sugarBagSound;

    public AudioManager(Class<?> resourceClass) {
        backgroundMusic = new MediaPlayer(new Media(resourceClass.getResource("/audio/cafebackgroundsong.mp3").toString()));
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(0.15);

        clinkSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/CupclinkSound.mp3").toString()));
        matchaBagCrunchSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/matchaBag.mp3").toString()));
        waterPourSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/waterSound.mp3").toString()));
        milkPourSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/MilkSound.mp3").toString()));
        coffeeGrindingSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/CoffeeGrinder1.mp3").toString()));
        coffeeCrunchSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/CoffeeCrunch.mp3").toString()));
        sugarBagSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/Sugar.mp3").toString()));

        sugarBagSound.setVolume(0.3);
    }

    public void playBackground() { backgroundMusic.play(); }

    public void playClink() {
        clinkSound.seek(clinkSound.getStartTime());
        clinkSound.play();
    }

    public void playMatchaCrunch() {
        matchaBagCrunchSound.seek(matchaBagCrunchSound.getStartTime());
        matchaBagCrunchSound.play();
    }

    public void playWater() {
        waterPourSound.seek(waterPourSound.getStartTime());
        waterPourSound.play();
    }

    public void playMilk() {
        milkPourSound.seek(milkPourSound.getStartTime());
        milkPourSound.play();
    }

    public void playCoffeeGrinder() {
        coffeeGrindingSound.seek(coffeeGrindingSound.getStartTime());
        coffeeGrindingSound.play();
    }

    public void playCoffeeCrunch() {
        coffeeCrunchSound.seek(coffeeCrunchSound.getStartTime());
        coffeeCrunchSound.play();
    }

    public void playSugarBagSound() {
        sugarBagSound.seek(sugarBagSound.getStartTime());
        sugarBagSound.play();
    }
}
