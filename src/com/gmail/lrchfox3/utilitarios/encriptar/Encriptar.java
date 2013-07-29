/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gmail.lrchfox3.utilitarios.encriptar;

/**
 *
 * @author lchinchilla
 */
public class Encriptar {

    public String currentPassword = ".lrchfox3.";
    private char key[] = new char[10];
    private char subkey1[] = new char[8];
    private char subkey2[] = new char[8];

    // S-DES encryption algorithm
    public String encrypt(String _textToEncrypt) throws Exception {
        if (currentPassword.length() < 10) {
            throw new Exception("La llave para encriptar debe tener al menos 10 caracteres");
        }

        generateKeys();

        String textToEncrypt = _textToEncrypt;
        String EncryptedText = new String("");

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToEncrypt.length(); i++) {

            char theChar = textToEncrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }

            // chars to manipulate are now ready

            S_DES s = new S_DES();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey1);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey2);
            char array5[] = new char[8];
            array5 = s.IP_inverse(array4);


            String tmp = new String("");
            for (int k = 0; k < array5.length; k++) {
                tmp += String.valueOf(array5[k]);
            }

            EncryptedText += (char) (Integer.parseInt(tmp, 2));

        }

        return EncryptedText;

    }

    // S-DES decryption algorithm 
    public String decrypt(String _textToDecrypt) throws Exception {


        if (currentPassword.length() < 10) {
            throw new Exception("La llave para encriptar debe tener al menos 10 caracteres");
        }

        generateKeys();

        String textToDecrypt = _textToDecrypt;
        String DecryptedText = new String("");

        char charsToManipulate[] = new char[8];

        for (int i = 0; i < textToDecrypt.length(); i++) {

            char theChar = textToDecrypt.charAt(i);
            int value = (int) theChar;
            String valueString = Integer.toBinaryString(value);

            while (valueString.length() < 8) {
                valueString = "0" + valueString;
            }

            for (int j = 0; j < 8; j++) {
                charsToManipulate[j] = valueString.charAt(j);
            }
            // chars to manipulate are now ready

            S_DES s = new S_DES();

            char array1[] = new char[8];
            array1 = s.IP(charsToManipulate);
            char array2[] = new char[8];
            array2 = s.FK(array1, subkey2);
            char array3[] = new char[8];
            array3 = s.SW(array2);
            char array4[] = new char[8];
            array4 = s.FK(array3, subkey1);
            char array5[] = new char[8];
            array5 = s.IP_inverse(array4);


            String tmp = new String("");
            for (int k = 0; k < array5.length; k++) {
                tmp += String.valueOf(array5[k]);
            }

            DecryptedText += (char) (Integer.parseInt(tmp, 2));

        }

        return DecryptedText;
    }

    private void generateKeys() {


        key = (new YKey(currentPassword)).generateKey();

        // P10
        char p10[] = new char[10];
        p10[0] = key[2];
        p10[1] = key[4];
        p10[2] = key[1];
        p10[3] = key[6];
        p10[4] = key[3];
        p10[5] = key[9];
        p10[6] = key[0];
        p10[7] = key[8];
        p10[8] = key[7];
        p10[9] = key[5];

        // Split the 10 bits into 5's

        YBitSet LeftFiveBits = new YBitSet(5);
        YBitSet RightFiveBits = new YBitSet(5);

        for (int i = 0; i < p10.length; i++) {

            if (i < 5) {
                if (p10[i] == '1') {
                    LeftFiveBits.set(i);
                } else if (p10[i] == '0') {
                    LeftFiveBits.clear(i);
                }
            } else {
                if (p10[i] == '1') {
                    RightFiveBits.set(i);
                } else if (p10[i] == '0') {
                    RightFiveBits.clear(i);
                }
            }
        }

        // Apply LS_1 on each one

        YBitSet LS_1LeftBits = LeftFiveBits.LS_1(5);
        YBitSet LS_1RightBits = RightFiveBits.LS_1(5);

        // Apply P8 to produce the first subkey

        char theKeyAfterLS_1[] = new char[10];

        char left1[] = LS_1LeftBits.bitSetToCharArray(5);
        char right1[] = LS_1RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_1[i] = left1[i];
            } else {
                theKeyAfterLS_1[i] = right1[i - 5];
            }
        }


        subkey1[0] = theKeyAfterLS_1[5];
        subkey1[1] = theKeyAfterLS_1[2];
        subkey1[2] = theKeyAfterLS_1[6];
        subkey1[3] = theKeyAfterLS_1[3];
        subkey1[4] = theKeyAfterLS_1[7];
        subkey1[5] = theKeyAfterLS_1[4];
        subkey1[6] = theKeyAfterLS_1[9];
        subkey1[7] = theKeyAfterLS_1[8];



        // Apply LS_2 

        YBitSet LS_2LeftBits = LS_1LeftBits.LS_2(5);
        YBitSet LS_2RightBits = LS_1RightBits.LS_2(5);

        // Apply P8

        char theKeyAfterLS_2[] = new char[10];

        char left2[] = LS_2LeftBits.bitSetToCharArray(5);
        char right2[] = LS_2RightBits.bitSetToCharArray(5);

        for (int i = 0; i < 10; i++) {
            if (i < 5) {
                theKeyAfterLS_2[i] = left2[i];
            } else {
                theKeyAfterLS_2[i] = right2[i - 5];
            }
        }

        subkey2[0] = theKeyAfterLS_2[5];
        subkey2[1] = theKeyAfterLS_2[2];
        subkey2[2] = theKeyAfterLS_2[6];
        subkey2[3] = theKeyAfterLS_2[3];
        subkey2[4] = theKeyAfterLS_2[7];
        subkey2[5] = theKeyAfterLS_2[4];
        subkey2[6] = theKeyAfterLS_2[9];
        subkey2[7] = theKeyAfterLS_2[8];
    }
}
