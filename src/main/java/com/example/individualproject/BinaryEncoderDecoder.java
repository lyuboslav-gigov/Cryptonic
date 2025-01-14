/**

 * File: BinaryEncoderDecoder.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

public class BinaryEncoderDecoder {
    public static String binaryEncode(String plainText){
        StringBuilder binaryText = new StringBuilder();

        for(char c : plainText.toCharArray()){
            // Integer.toBinaryString(c) - converts c to its binary representation as a string
            // %8s is a format specifier that forces the resulting string to have a minimum width
            // of 8 characters. If it's less than that, it will be padded with spaces.
            // replace(' ', '0') replaces the spaces with zeros
            String binaryChar = String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');
            binaryText.append(binaryChar).append(" "); // Adding space for visibility purposes
        }

        return  binaryText.toString().trim();
    }

    public static String binaryDecode(String binaryText){
        StringBuilder plainText = new StringBuilder();
        String[] binarySubstrings = binaryText.split(" ");

        for(String binaryString : binarySubstrings){
            // Takes a string of bits (in base 2), and converts them to an integer with the standard base 10. Then integer is cast to char.
            char decodedChar = ' ';
            try{
                decodedChar = (char) Integer.parseInt(binaryString, 2);
            }catch (Exception e){
                EncryptScene.showAlert("Invalid Input", "Please enter a binary-encrypted text!");
                e.printStackTrace();
            }
            plainText.append(decodedChar);
        }

        return plainText.toString();
    }
}
