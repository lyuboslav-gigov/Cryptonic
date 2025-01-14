/**

 * File: VigenereCipher.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

public class VigenereCipher {
    public static String vigenereEncrypt(String message, String keyword) {
        StringBuilder encryptedText = new StringBuilder();
        int keywordLength = keyword.length();
        int messageLength = message.length();

        for (int i = 0, j = 0; i < messageLength; i++) {
            char originalChar = message.charAt(i);
            boolean isUpper = Character.isUpperCase(originalChar);

            if (Character.isLetter(originalChar)) {
                // Base variable is necessary for keeping the case of the original text
                char base = Character.isLowerCase(originalChar) ? 'a' : 'A';
                // Modulo is used below because the key is repeated continuously until it matches the length of the message
                char keywordChar = Character.toLowerCase(keyword.charAt(j % keywordLength));

                // Calculate the shifted character using Vigenere cipher formula (but modified so that the case is kept)
                char shiftedChar = (char) ((originalChar - base + keywordChar - 'a') % 26 + base);
                encryptedText.append(shiftedChar);

                j++; // Move to the next character in the keyword
            } else {
                // Preserve non-alphabetic characters (i.e. digits and special characters)
                encryptedText.append(originalChar);
            }
        }

        return encryptedText.toString();
    }

    public static String vigenereDecrypt(String encryptedMessage, String keyword) {
        StringBuilder decryptedText = new StringBuilder();
        int keywordLength = keyword.length();
        int encryptedLength = encryptedMessage.length();

        for (int i = 0, j = 0; i < encryptedLength; i++) {
            char encryptedChar = encryptedMessage.charAt(i);

            if (Character.isLetter(encryptedChar)) {
                char base = Character.isLowerCase(encryptedChar) ? 'a' : 'A';
                char keywordChar = Character.toLowerCase(keyword.charAt(j % keywordLength));

                // Calculate the reverse shifted character using Vigenere cipher formula
                char reversedChar = (char) ((encryptedChar - base - (keywordChar - 'a') + 26) % 26 + base);
                decryptedText.append(reversedChar);

                j++; // Move to the next character in the keyword
            } else {
                // Preserve non-alphabetic characters
                decryptedText.append(encryptedChar);
            }
        }

        return decryptedText.toString();
    }
}

