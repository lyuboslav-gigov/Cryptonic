/**

 * File: BaconianCipher.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import javafx.scene.control.Alert;
import java.awt.*;
import java.util.*;

// Uses the 26-letter version of Bacon's cipher where each letter has a unique encoding (thus, cipher is more difficult to break)
// Note that Bacon's cipher is not case-sensitive, and in fact it is better if the case is not kept. This is why my implementation is also not case-sensitive.

public class BaconianCipher {
    // Creates a hash map that maps each character to its corresponding encryption pattern
    private static final HashMap<Character, String> BACONIAN_ALPHABET = new HashMap<>();

    // Static initialization block for the Baconian alphabet
    static {
        // Initialize the Baconian alphabet mapping
        BACONIAN_ALPHABET.put('A', "AAAAA");
        BACONIAN_ALPHABET.put('B', "AAAAB");
        BACONIAN_ALPHABET.put('C', "AAABA");
        BACONIAN_ALPHABET.put('D', "AAABB");
        BACONIAN_ALPHABET.put('E', "AABAA");
        BACONIAN_ALPHABET.put('F', "AABAB");
        BACONIAN_ALPHABET.put('G', "AABBA");
        BACONIAN_ALPHABET.put('H', "AABBB");
        BACONIAN_ALPHABET.put('I', "ABAAA");
        BACONIAN_ALPHABET.put('J', "ABAAB");
        BACONIAN_ALPHABET.put('K', "ABABA");
        BACONIAN_ALPHABET.put('L', "ABABB");
        BACONIAN_ALPHABET.put('M', "ABBAA");
        BACONIAN_ALPHABET.put('N', "ABBAB");
        BACONIAN_ALPHABET.put('O', "ABBBA");
        BACONIAN_ALPHABET.put('P', "ABBBB");
        BACONIAN_ALPHABET.put('Q', "BAAAA");
        BACONIAN_ALPHABET.put('R', "BAAAB");
        BACONIAN_ALPHABET.put('S', "BAABA");
        BACONIAN_ALPHABET.put('T', "BAABB");
        BACONIAN_ALPHABET.put('U', "BABAA");
        BACONIAN_ALPHABET.put('V', "BABAB");
        BACONIAN_ALPHABET.put('W', "BABBA");
        BACONIAN_ALPHABET.put('X', "BABBB");
        BACONIAN_ALPHABET.put('Y', "BBAAA");
        BACONIAN_ALPHABET.put('Z', "BBAAB");
    }

    // Create a reversed map for decryption
    private static final HashMap<String, Character> REVERSED_BACONIAN_ALPHABET = new HashMap<>();

    static {
        // Iterate through the original map and build the reversed map
        for (Map.Entry<Character, String> entry : BACONIAN_ALPHABET.entrySet()) {
            REVERSED_BACONIAN_ALPHABET.put(entry.getValue(), entry.getKey());
        }
    }

    public static String baconianEncrypt(String plaintext) {
        StringBuilder ciphertext = new StringBuilder();
        boolean containsLetters = false;

        for (char letter : plaintext.toUpperCase().toCharArray()) {
            if (Character.isAlphabetic(letter)) {
                ciphertext.append(BACONIAN_ALPHABET.get(letter)).append(" ");
                containsLetters = true;
            }
            // Non-alphabetic characters are ignored (This is the method of the cipher)
        }

        // Shows a warning that Bacon's cipher ignores non-letter characters by default
        if(!containsLetters){
            showWarning("Warning", "Keep in mind that Bacon's cipher ignores non-alphabetic characters!");
        }

        // trim() removes trailing white spaces
        return ciphertext.toString().trim();
    }

    public static String baconianDecrypt(String encryptedText){
        StringBuilder decryptedText = new StringBuilder();
        StringBuilder word = new StringBuilder();
        char[] tmpArr = encryptedText.toUpperCase().toCharArray();

        for (char c : tmpArr){
            if(Character.isLetter(c)){
                word.append(c);
            }
        }

        for(int j = 0; j < word.length(); j += 5){
            StringBuilder group = new StringBuilder();
            for(int m = 0; m <= 4 && (j + m) < word.length(); m++){

                group.append(word.charAt(j + m));
            }

            decryptedText.append(REVERSED_BACONIAN_ALPHABET.get(group.toString()));

            group.setLength(0);
        }

        return decryptedText.toString();
    }

    // A method to show a warning to the user
    public static void showWarning(String title, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        Toolkit.getDefaultToolkit().beep(); // Sounds a beep to indicate error
        alert.showAndWait();
    }
}
