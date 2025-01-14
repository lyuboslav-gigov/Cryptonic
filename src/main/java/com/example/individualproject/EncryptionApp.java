/**

 * File: EncryptionApp.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.stage.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Rectangle2D;
import javafx.util.Duration;

// Background image taken from Vecteezy.com
// https://www.vecteezy.com/vector-art/1211341-digital-data-encryption-background-with-glowing-red-circle

// This is the main class of the program.
public class EncryptionApp extends Application {

    private Scene welcomeScene, encryptScene, decryptScene;

    private Stage window = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    // Get screen size of monitor
    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();

    @Override
    public void start(Stage primaryStage) {

        // This sets up the welcome window. User chooses whether he/she wants to encrypt or decrypt messages

        window.setTitle("Cryptonic");

        // Changing app image to something meaningful
        // Change pathname if running on another machine
        Image appImage = new Image("C:\\Java\\IndividualProject\\src\\main\\resources\\icons\\Encryption-icon.png");
        window.getIcons().add(appImage);

        // Setting background image
        // Change pathname if running on another machine
        Image backgroundLoad = new Image("C:\\Java\\IndividualProject\\src\\main\\resources\\icons\\Encryption-Background4.jpg");
        BackgroundImage backgroundImage = new BackgroundImage(backgroundLoad, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                new BackgroundPosition(Side.LEFT, 0.0, true, Side.TOP, -0.0, true),
                new BackgroundSize(screenSize.getWidth(), screenSize.getHeight(), false, false, true, true));
        // End of setting background image

        Label welcomeLabel = new Label("Welcome to Cryptonic!");
        welcomeLabel.setStyle("-fx-font-weight: bold;");
        welcomeLabel.setFont(new Font("Comic Sans MS", 30));
        welcomeLabel.setTextFill(Color.WHITE);

        Button encryptOption = new Button("Encrypt");
        encryptOption.setFont(new Font("Comic Sans MS", 22));
        encryptOption.setTextFill(Paint.valueOf("black"));
        encryptOption.setBackground(new Background(new BackgroundFill(Color.rgb(255, 51, 51), null, null)));

        Button decryptOption = new Button("Decrypt");
        decryptOption.setFont(new Font("Comic Sans MS", 22));
        decryptOption.setTextFill(Paint.valueOf("black"));
        decryptOption.setBackground(new Background(new BackgroundFill(Color.DEEPSKYBLUE, null, null)));

        HBox buttonsHBox = new HBox(encryptOption, decryptOption);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20);

        // Setting up the layout of the scene
        VBox topVBox = new VBox(10);
        topVBox.getChildren().addAll(welcomeLabel, buttonsHBox);
        topVBox.setAlignment(Pos.CENTER);
        topVBox.setBackground(new Background(backgroundImage));

        welcomeScene = new Scene(topVBox, screenSize.getWidth(), screenSize.getHeight());

        // Set up fade-in animation
//        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(4), topVBox);
//        fadeTransition.setFromValue(0.0);
//        fadeTransition.setToValue(1.0);
//        fadeTransition.play();
        // End of fade animation

        // Button Actions

        // Sends user to encrypt scene / encrypt mode
        encryptOption.setOnAction(e -> {
           EncryptScene encryptScene = new EncryptScene(window, welcomeScene, screenSize.getWidth(), screenSize.getHeight());
           window.setScene(encryptScene.getScene());
        });

        // Sends user to decrypt scene / decrypt mode
        decryptOption.setOnAction(e -> {
            DecryptScene decryptScene = new DecryptScene(window, welcomeScene, screenSize.getWidth(), screenSize.getHeight());
            window.setScene(decryptScene.getScene());
        });

        // End of Button Actions

        window.setScene(welcomeScene);
        window.setMaximized(true);
        window.show();
    }
}