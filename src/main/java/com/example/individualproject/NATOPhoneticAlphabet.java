/**

 * File: NATOPhoneticAlphabet.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import java.util.HashMap;
import java.util.Map;

public class NATOPhoneticAlphabet {
    private static final HashMap<Character, String> NATO_ALPHABET = new HashMap<>();

    // Static initialization block for the NATO phonetic alphabet
    static {
        NATO_ALPHABET.put('A', "Alpha");
        NATO_ALPHABET.put('B', "Bravo");
        NATO_ALPHABET.put('C', "Charlie");
        NATO_ALPHABET.put('D', "Delta");
        NATO_ALPHABET.put('E', "Echo");
        NATO_ALPHABET.put('F', "Foxtrot");
        NATO_ALPHABET.put('G', "Golf");
        NATO_ALPHABET.put('H', "Hotel");
        NATO_ALPHABET.put('I', "India");
        NATO_ALPHABET.put('J', "Juliet");
        NATO_ALPHABET.put('K', "Kilo");
        NATO_ALPHABET.put('L', "Lima");
        NATO_ALPHABET.put('M', "Mike");
        NATO_ALPHABET.put('N', "November");
        NATO_ALPHABET.put('O', "Oscar");
        NATO_ALPHABET.put('P', "Papa");
        NATO_ALPHABET.put('Q', "Quebec");
        NATO_ALPHABET.put('R', "Romeo");
        NATO_ALPHABET.put('S', "Sierra");
        NATO_ALPHABET.put('T', "Tango");
        NATO_ALPHABET.put('U', "Uniform");
        NATO_ALPHABET.put('V', "Victor");
        NATO_ALPHABET.put('W', "Whiskey");
        NATO_ALPHABET.put('X', "Xray");
        NATO_ALPHABET.put('Y', "Yankee");
        NATO_ALPHABET.put('Z', "Zulu");
        NATO_ALPHABET.put('0', "Zero");
        NATO_ALPHABET.put('1', "One");
        NATO_ALPHABET.put('2', "Two");
        NATO_ALPHABET.put('3', "Three");
        NATO_ALPHABET.put('4', "Four");
        NATO_ALPHABET.put('5', "Five");
        NATO_ALPHABET.put('6', "Six");
        NATO_ALPHABET.put('7', "Seven");
        NATO_ALPHABET.put('8', "Eight");
        NATO_ALPHABET.put('9', "Nine");
        NATO_ALPHABET.put(' ', "(space)");
        NATO_ALPHABET.put('.', "Stop");
    }

    // Creating a reversed map for decryption
    private static final HashMap<String, Character> REVERSED_NATO_ALPHABET = new HashMap<>();

    static {
        // Iterate through the original map and build the reversed map
        for (Map.Entry<Character, String> entry : NATO_ALPHABET.entrySet()) {
            REVERSED_NATO_ALPHABET.put(entry.getValue(), entry.getKey());
        }
    }

    // Code is very similar to that used for the Baconian cipher, so go there for further details and explanations
    public static String natoEncrypt(String plainText){
        StringBuilder encryptedText = new StringBuilder();

        for(char c : plainText.toUpperCase().toCharArray()){
            if(Character.isLetterOrDigit(c) || c == '.' || c == ' '){
                encryptedText.append(NATO_ALPHABET.get(c)).append(" ");
            }
            else
                encryptedText.append(c).append(" ");
        }

        return encryptedText.toString().trim();
    }

    public static String natoDecrypt(String encryptedText){
        StringBuilder decryptedText = new StringBuilder();

        String[] substrings = encryptedText.split(" ");

        for(String word : substrings){
            if(REVERSED_NATO_ALPHABET.containsKey(word)){
                decryptedText.append(REVERSED_NATO_ALPHABET.get(word));
            }
            else
                decryptedText.append(word);
        }

        return decryptedText.toString().toLowerCase();
    }
}
