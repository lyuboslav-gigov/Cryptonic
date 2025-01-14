/**

 * File: EncryptScene.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.awt.*;
import java.security.*;
import java.util.Base64;

public class EncryptScene {
    private Scene encryptScene;
    private static TextField vigenereKeyTextField = new TextField();
    private Label vigenereKeyLabel = new Label("Enter Vigenère Key");
    private Label privateKeyLabel = new Label("Private Key");
    private TextField privateKeyTextField = new TextField();
    private Label publicKeyLabel = new Label("Public Key");
    private TextField publicKeyTextField = new TextField();
    private VBox rsaKeyVBox = new VBox(publicKeyLabel, publicKeyTextField, privateKeyLabel, privateKeyTextField);

    // Using regular expressions to ensure Vigenere key input contains only letters
    public static boolean isValidVigenereKey(String input){

        return input.matches("^[a-zA-Z]+$");
    }

    // Method to show an alert box to the user (e.g. if invalid input is entered)
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Toolkit.getDefaultToolkit().beep(); // Sounds a beep to indicate error
        alert.showAndWait();
    }

    public EncryptScene(Stage window, Scene welcomeScene, double screenWidth, double screenHeight){
        TextArea inputTextArea = new TextArea();
        inputTextArea.setFont(new Font("Comic Sans MS", 14));

        TextArea encryptedTextArea = new TextArea();
        encryptedTextArea.setFont(new Font("Comic Sans MS", 14));
        encryptedTextArea.setEditable(false);

        ComboBox encryptionMethodComboBox = new ComboBox<String>();
        encryptionMethodComboBox.setStyle("-fx-font-size: 14; -fx-font-family: 'Comic Sans MS';");
        encryptionMethodComboBox.getItems().addAll("Caesar Cipher", "Vigenère Cipher", "Bacon's Cipher", "NATO Phonetic Alphabet", "Binary Encryption", "RSA Encryption");
        encryptionMethodComboBox.setValue("Caesar Cipher");

        // Initially hides Vigenere Key label and text field
        vigenereKeyTextField.setVisible(false);
        vigenereKeyTextField.setFont(new Font("Comic Sans MS", 14));
        vigenereKeyLabel.setFont(new Font("Comic Sans MS", 16));
        vigenereKeyLabel.setVisible(false);
        // Initially hides Vigenere Key label and text field

        // Buttons set-up
        Button encryptButton = new Button("Encrypt");
        encryptButton.setFont(new Font("Comic Sans MS", 14));

        Button goBackButton = new Button("Go Back");
        goBackButton.setFont(new Font("Comic Sans MS", 14));

        Button saveToHistoryButton = new Button("Save to History");
        saveToHistoryButton.setFont(new Font("Comic Sans MS", 14));

        Button clearButton = new Button("Clear");
        clearButton.setFont(new Font("Comic Sans MS", 14));
        // Buttons set-up

        // Sub-layouts Setup
        HBox buttonsHBox = new HBox(encryptionMethodComboBox, encryptButton, saveToHistoryButton, clearButton, goBackButton);
        buttonsHBox.setSpacing(5);
        VBox inputVBox = new VBox(new Label("Plain Text"), inputTextArea);
        VBox encryptedVBox = new VBox(new Label("Encrypted Text"), encryptedTextArea);
        HBox textFieldsHBox = new HBox(inputVBox, encryptedVBox);
        // Sub-layouts Setup

        // RSA Key Hiding
        rsaKeyVBox.setVisible(false);
        rsaKeyVBox.setSpacing(5);
        publicKeyTextField.setEditable(false);
        privateKeyTextField.setEditable(false);
        publicKeyTextField.setFont(new Font("Comic Sans MS", 14));
        privateKeyTextField.setFont(new Font("Comic Sans MS", 14));
        publicKeyLabel.setFont(new Font("Comic Sans MS", 16));
        privateKeyLabel.setFont(new Font("Comic Sans MS", 16));
        // RSA Key Hiding

        // Final Layout
        GridPane encryptSceneLayout = new GridPane();
        encryptSceneLayout.setPadding(new Insets(10, 10, 10, 10));
        encryptSceneLayout.setVgap(5);
        encryptSceneLayout.setHgap(10);
        encryptSceneLayout.setAlignment(Pos.CENTER);

        Label plainTextLabel = new Label("Plain Text");
        plainTextLabel.setFont(new Font("Comic Sans MS", 20));

        Label encryptedTextLabel = new Label("Encrypted Text");
        encryptedTextLabel.setFont(new Font("Comic Sans MS", 20));

        encryptSceneLayout.add(plainTextLabel, 0, 0);
        encryptSceneLayout.add(inputTextArea, 0, 1);
        encryptSceneLayout.add(encryptedTextLabel, 1, 0);
        encryptSceneLayout.add(encryptedTextArea, 1, 1);
        encryptSceneLayout.add(buttonsHBox, 0, 2);
        encryptSceneLayout.add(vigenereKeyLabel, 0, 3);
        encryptSceneLayout.add(vigenereKeyTextField, 0, 4);
        encryptSceneLayout.add(rsaKeyVBox, 1, 2);

        // Set the background color for the encryption scene to match the color of the encrypt button in the welcome scene
        Color backgroundColor = Color.rgb(255, 51, 51);
        encryptSceneLayout.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));

        // End of Final Layout Setup

        goBackButton.setOnAction(e -> {
            window.setScene(welcomeScene);
        });

        saveToHistoryButton.setOnAction(e -> {
            String encryptedText = encryptedTextArea.getText();
            HistoryTable.getHistoryData().add(encryptedText);
        });

        clearButton.setOnAction(e ->{
            inputTextArea.clear();
            encryptedTextArea.clear();
        });

        encryptScene = new Scene(encryptSceneLayout, screenWidth, screenHeight);

        // Listen for changes in the encryption method selection. If Vigenere cipher is selected, show the Vigenere key text field.
        // If RSA is selected, show the RSA keys.
        encryptionMethodComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleEncryptionMethodChange(newValue.toString());
        });

        // Based on the selected encryption method, encrypt the entered text with the appropriate cipher
        encryptButton.setOnAction(e -> {
            String inputText = inputTextArea.getText();
            String encryptionMethod = encryptionMethodComboBox.getValue().toString();
            String encryptedText = "";

            switch (encryptionMethod) {
                case "Caesar Cipher":
                    encryptedText = CaesarCipher.caesarEncrypt(inputText);
                    break;
                case "Vigenère Cipher":
                    String tmpKey = vigenereKeyTextField.getText();

                    if(isValidVigenereKey(tmpKey)){
                        encryptedText = VigenereCipher.vigenereEncrypt(inputText, tmpKey);
                        DecryptScene.vigenereKeyTextField.setText(tmpKey);
                    }
                    else{
                        showAlert("Invalid Vigenère Key", "Vigenère key should consist of only letters!");
                    }
                    break;
                case "Bacon's Cipher":
                    encryptedText = BaconianCipher.baconianEncrypt(inputText);
                    break;
                case "NATO Phonetic Alphabet":
                    encryptedText = NATOPhoneticAlphabet.natoEncrypt(inputText);
                    break;
                case "Binary Encryption":
                    encryptedText = BinaryEncoderDecoder.binaryEncode(inputText);
                    break;
                case "RSA Encryption":
                    // Generating RSA Key Pair
                    KeyPair keyPair = null;
                    try{
                        keyPair = RSACipher.generateKeyPair();
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
                    // Generating RSA Key Pair

                    try {
                        // Encrypt the message with the public key. Show the public and private keys to the user.
                        encryptedText = RSACipher.RSAEncrypt(inputText, keyPair.getPublic());
                        rsaKeyVBox.setVisible(true);
                        String publicKeySting = Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded());
                        String privateKeyString = Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded());
                        publicKeyTextField.setText(publicKeySting);
                        privateKeyTextField.setText(privateKeyString);
                    } catch (Exception exception){
                        exception.printStackTrace();
                    }
            }

            encryptedTextArea.setText(encryptedText);
        });
    }

    public Scene getScene() {
        return encryptScene;
    }

    // Show or hide the Vigenere Key TextField or RSA keys based on the selected encryption method
    private void handleEncryptionMethodChange(String newValue) {
        boolean isVigenereSelected = "Vigenère Cipher".equals(newValue);
        vigenereKeyTextField.setVisible(isVigenereSelected);
        vigenereKeyLabel.setVisible(isVigenereSelected);

        boolean isRSASelected = "RSA Encryption".equals(newValue);
        rsaKeyVBox.setVisible(isRSASelected);
    }
}
