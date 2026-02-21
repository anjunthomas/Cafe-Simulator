package com.cafe.utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private MediaPlayer backgroundMusic;
    private MediaPlayer clinkSound;
    private MediaPlayer matchaBagCrunchSound;
    private MediaPlayer waterPourSound;

    private MediaPlayer milkPourSound;

    public AudioManager(Class<?> resourceClass) {
        backgroundMusic = new MediaPlayer(new Media(resourceClass.getResource("/audio/cafebackgroundsong.mp3").toString()));
        backgroundMusic.setCycleCount(MediaPlayer.INDEFINITE);
        backgroundMusic.setVolume(0.15);

        clinkSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/CupclinkSound.mp3").toString()));
        matchaBagCrunchSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/matchaBag.mp3").toString()));
        waterPourSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/waterSound.mp3").toString()));
        milkPourSound = new MediaPlayer(new Media(resourceClass.getResource("/audio/MilkSound.mp3").toString()));
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
}
