/**

 * File: DecryptScene.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.*;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class DecryptScene {
    private Scene decryptScene;
    public static TextField vigenereKeyTextField = new TextField();
    private Label vigenereKeyLabel = new Label("Enter Vigenère Key");
    private Label privateKeyLabel = new Label("Enter Private Key");
    private TextField privateKeyTextField = new TextField();
    private VBox rsaKeyVBox = new VBox(privateKeyLabel, privateKeyTextField);

    private HistoryTable historyTable = new HistoryTable();

    public DecryptScene(Stage window, Scene welcomeScene, double screenWidth, double screenHeight){
        TextArea encryptedTextArea = new TextArea();
        encryptedTextArea.setFont(new Font("Comic Sans MS", 14));

        TextArea decryptedTextArea = new TextArea();
        decryptedTextArea.setFont(new Font("Comic Sans MS", 14));
        decryptedTextArea.setEditable(false);

        ComboBox decryptionMethodComboBox = new ComboBox<String>();
        decryptionMethodComboBox.setStyle("-fx-font-size: 14; -fx-font-family: 'Comic Sans MS';");
        decryptionMethodComboBox.getItems().addAll("Caesar Cipher", "Vigenère Cipher", "Bacon's Cipher", "NATO Phonetic Alphabet", "Binary Decryption", "RSA Decryption");
        decryptionMethodComboBox.setValue("Caesar Cipher");

        // Initially hides Vigenere Key label and text field
        vigenereKeyTextField.setVisible(false);
        vigenereKeyTextField.setFont(new Font("Comic Sans MS", 14));
        vigenereKeyLabel.setFont(new Font("Comic Sans MS", 16));
        vigenereKeyLabel.setVisible(false);

        // Hides RSA Private Key
        rsaKeyVBox.setVisible(false);
        rsaKeyVBox.setSpacing(5);
        privateKeyLabel.setFont(new Font("Comic Sans MS", 16));
        privateKeyTextField.setFont(new Font("Comic Sans MS", 14));
        // Hides RSA Private Key

        Button decryptButton = new Button("Decrypt");
        decryptButton.setFont(new Font("Comic Sans MS", 14));

        Button goBackButton = new Button("Go back");
        goBackButton.setFont(new Font("Comic Sans MS", 14));

        Button historyButton = new Button("History");
        historyButton.setFont(new Font("Comic Sans MS", 14));

        Button clearButton = new Button("Clear");
        clearButton.setFont(new Font("Comic Sans MS", 14));

        // Buttons layout Setup
        HBox buttonsHBox = new HBox(decryptionMethodComboBox, decryptButton, historyButton, clearButton, goBackButton);
        buttonsHBox.setSpacing(5);

        // History Table Setup
        HBox historyLayout = historyTable.createHistoryTableLayout(encryptedTextArea);
        // End of History Table Setup

        // Main layout setup
        GridPane decryptSceneLayout = new GridPane();
        decryptSceneLayout.setPadding(new Insets(10, 10, 10, 10));
        decryptSceneLayout.setVgap(5);
        decryptSceneLayout.setHgap(10);
        decryptSceneLayout.setAlignment(Pos.CENTER);

        Label plainTextLabel = new Label("Plain Text");
        plainTextLabel.setFont(new Font("Comic Sans MS", 20));

        Label encryptedTextLabel = new Label("Encrypted Text");
        encryptedTextLabel.setFont(new Font("Comic Sans MS", 20));

        decryptSceneLayout.add(encryptedTextLabel, 0, 0);
        decryptSceneLayout.add(encryptedTextArea, 0, 1);
        decryptSceneLayout.add(plainTextLabel, 1, 0);
        decryptSceneLayout.add(decryptedTextArea, 1, 1);
        decryptSceneLayout.add(buttonsHBox, 0, 2);
        decryptSceneLayout.add(vigenereKeyLabel, 0, 3);
        decryptSceneLayout.add(vigenereKeyTextField, 0, 4);
        decryptSceneLayout.add(rsaKeyVBox, 1, 2);

        // Set the background color for the encryption scene (matches the color on the decrypt button from the welcome scene)
        Color backgroundColor = Color.DEEPSKYBLUE;
        decryptSceneLayout.setBackground(new Background(new BackgroundFill(backgroundColor, null, null)));
        // End of main layout setup

        goBackButton.setOnAction(e -> {
            window.setScene(welcomeScene);
        });

        historyButton.setOnAction(e -> {
            // Check if the History Table is already added to the GridPane
            if (decryptSceneLayout.getChildren().contains(historyLayout)) {
                // Remove the History Table from the GridPane
                decryptSceneLayout.getChildren().remove(historyLayout);
            } else {
                // Add the History Table to the GridPane
                decryptSceneLayout.add(historyLayout, 0, 5);
                historyTable.toggleHistoryVisibility();
            }
        });

        clearButton.setOnAction(e ->{
            encryptedTextArea.clear();
            decryptedTextArea.clear();
        });

        decryptScene = new Scene(decryptSceneLayout, screenWidth, screenHeight);

        // Listen for changes in the decryption method selection and show/hide the appropriate text fields
        decryptionMethodComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            handleDecryptionMethodChange(newValue.toString());
        });

        // Based on the selected decryption method, decrypt the entered text with the appropriate cipher
        decryptButton.setOnAction(e -> {
            String inputText = encryptedTextArea.getText();
            String decryptionMethod = decryptionMethodComboBox.getValue().toString();
            String decryptedText = "";

            switch (decryptionMethod) {
                case "Caesar Cipher":
                    decryptedText = CaesarCipher.caesarDecrypt(inputText);
                    break;
                case "Vigenère Cipher":
                    String tmpKey = vigenereKeyTextField.getText();
                    if(EncryptScene.isValidVigenereKey(tmpKey))
                        decryptedText = VigenereCipher.vigenereDecrypt(inputText, tmpKey);
                    else
                        EncryptScene.showAlert("Invalid Vigenère Key", "Vigenère key should consist of only letters!");
                    break;
                case "Bacon's Cipher":
                    decryptedText = BaconianCipher.baconianDecrypt(inputText);
                    break;
                case "NATO Phonetic Alphabet":
                    decryptedText = NATOPhoneticAlphabet.natoDecrypt(inputText);
                    break;
                case "Binary Decryption":
                    decryptedText = BinaryEncoderDecoder.binaryDecode(inputText);
                    break;
                case "RSA Decryption":
                    String privateKeyString = privateKeyTextField.getText();
                    try {
                        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString);
                        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
                        KeyFactory keyFactory;
                        keyFactory = KeyFactory.getInstance("RSA");
                        decryptedText = RSACipher.RSADecrypt(inputText, keyFactory.generatePrivate(keySpec));
                    }catch (Exception exception){
                        EncryptScene.showAlert("Invalid Input", "Please make sure your input (private key and RSA-encrypted text) is correct!");
                        exception.printStackTrace();
                    }
            }

            decryptedTextArea.setText(decryptedText);
        });
    }

    public Scene getScene() {
        return decryptScene;
    }

    private void handleDecryptionMethodChange(String newValue) {
        // Show or hide the Vigenere Key (or the RSA keys) based on the selected decryption method
        boolean isVigenereSelected = "Vigenère Cipher".equals(newValue);
        vigenereKeyTextField.setVisible(isVigenereSelected);
        vigenereKeyLabel.setVisible(isVigenereSelected);

        boolean isRSASelected = "RSA Decryption".equals(newValue);
        rsaKeyVBox.setVisible(isRSASelected);
    }
}
