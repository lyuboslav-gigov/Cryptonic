/**

 * File: CaesarCipher.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

public class CaesarCipher {
    public static String caesarEncrypt(String plainText){
        char[] encryptedText = new char[plainText.length()];

        for(int i = 0; i < plainText.length(); i++){
            char originalChar = plainText.charAt(i);

            if(!Character.isLetter(originalChar)){ // Digit characters and special characters not affected by cipher
                encryptedText[i] = originalChar;
            }
            else{
                char base = Character.isLowerCase(originalChar) ? 'a' : 'A';
                // Cipher keeps the case of the original text and hence why formula is different from the usual one
                encryptedText[i] = (char) ((originalChar - base + 3) % 26 + base);
            }
        }

        return String.valueOf(encryptedText);
    }

    public static String caesarDecrypt(String encryptedText){
        char[] decryptedText = new char[encryptedText.length()];

        for(int i = 0; i < encryptedText.length(); i++){
            char originalChar = encryptedText.charAt(i);

            if(!Character.isLetter(originalChar)){ // Digit characters and special characters not affected by cipher
                decryptedText[i] = originalChar;
            }
            else{
                char base = Character.isLowerCase(originalChar) ? 'a' : 'A';
                // Cipher keeps the case of the original text
                // Notice that only the shift/offset is changed
                decryptedText[i] = (char) ((originalChar - base + 23) % 26 + base);
            }
        }

        return String.valueOf(decryptedText);
    }
}
