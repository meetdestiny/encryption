package com.google.encrypt;

import com.google.crypto.tink.*;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;
import com.google.crypto.tink.daead.DeterministicAeadKeyTemplates;
import com.google.crypto.tink.proto.ChaCha20Poly1305Key;
import com.google.crypto.tink.proto.Keyset;
import com.google.crypto.tink.subtle.Hex;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.GeneralSecurityException;

public class TinkEncrypter {


  public static void main(String args[]) throws GeneralSecurityException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
    TinkConfig.register();

    Hex hex = new Hex();

    /*KeysetHandle keysetHandle = KeysetHandle.generateNew(
       AeadKeyTemplates.AES256_GCM);

    String keysetFilename = "my_keyset7.json";
    CleartextKeysetHandle.write(keysetHandle, JsonKeysetWriter.withFile(
        new File(keysetFilename)));*/

    KeysetHandle keysetHandle =  CleartextKeysetHandle.read(JsonKeysetReader.withFile(new File("my_keyset7.json")));


    Aead aead = keysetHandle.getPrimitive(Aead.class);
    byte[] ciphertext = aead.encrypt("Stephan Meyn".getBytes(), "ghijk".getBytes());
    System.out.println(Hex.encode(ciphertext));
  }
}
