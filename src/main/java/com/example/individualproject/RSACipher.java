/**

 * File: RSACipher.java

 * Author: Lyuboslav Gigov

 * Date: 11/28/2023

 */

package com.example.individualproject;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class RSACipher{

    // Method to generate public and private key pair
    public static KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGenerator = KeyPairGenerator.getInstance("RSA");
        keyGenerator.initialize(1024);
        return keyGenerator.generateKeyPair();
    }

    public static String RSAEncrypt(String plainText, PublicKey publicKey) throws Exception {
        // Selects RSA cipher from Cipher class
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // Encrypts the plain text byte by byte
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        // The bytes of text are converted to a regular String and returned to the user
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String RSADecrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        try{
            // The string is first decoded to bytes, and then the bytes are decrypted one by one
            byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch(Exception e){
            EncryptScene.showAlert("Invalid Input", "Please make sure your input (private key and RSA-encrypted text) is correct!");
            e.printStackTrace();
            return "";
        }
    }
}
