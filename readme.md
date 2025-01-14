Cryptonic
Project Overview
Application name: “Cryptonic”
Author: Lyuboslav Gigov
Date: 11.28.2023
Project Description
“Cryptonic” is a GUI application for encrypting and decrypting messages. From its very conception, it was designed to resemble “Google Translate” but instead of translating between different languages, it translates plain text to encrypted text and vice versa. The user can choose between six ciphers, and these range from simple ones, such as Caesar’s cipher, to modern ciphers, such as RSA. The user interface is clear-cut and simplistic, and this, combined with the rigorous input validation, makes the app easy to use even without previous knowledge of encryption/decryption methods.
Main Technologies Used
I used JavaFX for the creation of the app. I also used several special libraries and packages:
> Java Cryptography Architecture (JCA)
* javax.crypto.Cipher – the “Cipher” class from JCA provides cryptographic functionality, allowing encryption and decryption operations.
> Java Security
* java.security.* - this package includes classes for RSA public and private key generation, transformation from String to RSA key and vice versa. This package and the “Cipher” class include ready-made methods to implement RSA encryption and decryption, which are a fundamental part of my project.
> Java Util
* java.util.HashMap – the Hash Map data structure was used to map certain keys to their corresponding values according to the cipher being used. For example, in the Baconian cipher (but also in the NATO Phonetic alphabet), during the encryption process, the character ‘A’ is mapped to its corresponding ciphertext “AAAAA”. Without a Hash Map, providing such functionality would have been quite laborious.
Challenges Faced and Future Updates to the Project
The main challenges were in setting up the UI, as unbelievable as it may seem. Implementing the logic of the ciphers was a daunting task at first, but after carefully learning how the ciphers worked on paper, I was able to translate this into code relatively easily. However, problems such as how to change between different scenes in the same stage, how to transfer information between them, how to set up a history table that “remembered” what the user has typed, were what kept me up at night. In fact, this history table was what challenged me most but without it the program seemed incomplete.
As for future updates, I do indeed plan on developing this project further after the course finishes. I would like to add at least 5 more ciphers (e.g. Nihilist cipher, Block cipher, Hash function, etc.), and upgrade the history functionality go to both ways: saving encrypted AND decrypted items, and saving everything that the user types. I want to enhance the ambience of the program: each time the user presses a key, an authentic keyboard-pressing sound will be played.
How to Install and Run the Project
For the project to be able to run on the user’s device, they need to have JavaFX installed on their machines. Then, they need to download the project from my GitHub page and open it with an IDE. The user has to download the images used in the program (located in a separate directory entitled “icons”) and set their new pathnames in the relevant places in the code (indicated with comments). That’s it – the code should run fine with these slight modifications.
How to Use the Project
After everything has been set up, just run the main class of the program – the “EncryptionApp.java” class. Then, you will see the welcome window with two options – “Encrypt” or “Decrypt”. Choose one.
Choosing “Encrypt” will take you to the encryption window. In the left textbox, you should write the message that you want to encrypt. Then, choose the cipher from the drop-down menu right under the textbox. When you are ready, click on the “Encrypt” button and the encrypted text will be shown on the right. You can save it to the history with the “Save to History” button; you can clear both text fields with the “Clear” button, and the “Go Back” button takes you back to the welcome window.
The decryption window is similar. Here the only difference is that clicking the “History” button will open a history table, where the user can copy and paste directly what he/she encrypted in the previous window. This way the user does not have to remember gibberish text (encrypted text does indeed look like that) and can focus on the essence of the app: encrypting and decrypting.
Credits
I want to give credits to this site, from where I studied the algorithms of the ciphers (although technically I haven’t taken code directly from the site):
https://www.geeksforgeeks.org/
Further, the background image for the welcome screen is not my own. I took it from Vecteezy.com, https://www.vecteezy.com/vector-art/1211341-digital-data-encryption-background-with-glowing-red-circle. The image itself is free to download, and the author simply asks for credits, like I have done here, and also in the code.
