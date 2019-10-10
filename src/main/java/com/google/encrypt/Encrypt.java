package com.google.encrypt;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;

public class Encrypt {

  static final int AES_KEYLENGTH = 256;


  public Token encrypt(String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {


    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
    keyGen.init(AES_KEYLENGTH);
    SecretKey secretKey = keyGen.generateKey();
    String key = Base64.getEncoder().encodeToString(secretKey.getEncoded());

    byte[] iv = new byte[AES_KEYLENGTH / 8];
    SecureRandom prng = new SecureRandom();
    prng.nextBytes(iv);
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");

    cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(new byte[16]));
    String encryptedText =  Base64.getEncoder().encodeToString(cipher.doFinal(plainText.getBytes("UTF-8")));
    return new Token(encryptedText, key);

  }

  public String decrypt(String base64EncryptedText, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {



    Key k = new SecretKeySpec(Base64.getDecoder().decode(key), "AES");
    Cipher c = Cipher.getInstance("AES/CBC/PKCS5PADDING");
    c.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(new byte[16]));

    byte[] decodedValue = Base64.getDecoder().decode(base64EncryptedText);
    byte[] decValue = c.doFinal(decodedValue);
    String decryptedValue = new String(decValue);
    System.out.println(decryptedValue);
    return decryptedValue;
  }


  public static void main(String args[]) throws NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException {

    Encrypt encrypt = new Encrypt();

    String plainText = "Big Bank On Prem tokenization!";
    System.out.println("Plain text is:" + plainText);
    Token encryption = encrypt.encrypt(plainText);

    System.out.println("Encryption key used is :"+ encryption.getPrivateKey());

    System.out.println("Encrypted string: " +  encryption.getEncryptedText());
    encrypt.decrypt( encryption.getEncryptedText(),  encryption.getPrivateKey());

  }
}


class Token{
  String encryptedText;
  String privateKey;

  public Token(String encryptedText, String privateKey) {
    this.encryptedText = encryptedText;
    this.privateKey = privateKey;
  }

  public String getEncryptedText() {
    return encryptedText;
  }

  public void setEncryptedText(String encryptedText) {
    this.encryptedText = encryptedText;
  }

  public String getPrivateKey() {
    return privateKey;
  }

  public void setPrivateKey(String privateKey) {
    this.privateKey = privateKey;
  }
}
