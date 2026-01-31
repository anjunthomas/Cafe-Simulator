package com.cafe.utils;

import javafx.scene.image.Image;

public class ImageLoader {
    private static final String IMAGE_PATH = "/images/";

    /* this is used to load an image from the resources/images folder
    @param imageName will be the name of the image file (ex. background.png)
    returns the loaded Image object
    will throw a null pointer exception if the name is null
     */

    public static Image load(String imageName){
        try{
        return new Image(ImageLoader.class.getResourceAsStream(IMAGE_PATH + imageName));
    } catch (Exception e ){
        System.err.println("Error loading image: " + imageName);
        e.printStackTrace();
        return null;
    }
}

    /* to load an image with custom dimensions */
    public static Image load(String imageName, double width, double height) {
        try {
            return new Image(
                    ImageLoader.class.getResourceAsStream(IMAGE_PATH + imageName),
                    width,
                    height,
                    true, // to preserve the Ratio
                    true
            );
        } catch (Exception e) {
            System.out.println("Error loading image: " + imageName);
            e.printStackTrace();
            return null;
        }
    }
}
