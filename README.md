
1. Import the java code in your favourite IDE which support maven. 
2. Run the TinkEncrypter 
3. It will print a string value 
3. Use the string value in the BQ query inside FROM_HEX function. 

select SAFE_CONVERT_BYTES_TO_STRING(AEAD.DECRYPT_BYTES(KEYS.KEYSET_FROM_JSON('{"primaryKeyId": 41726583, "key": [{"keyData": {"typeUrl": "type.googleapis.com/google.crypto.tink.AesGcmKey", "keyMaterialType": "SYMMETRIC", "value": "GiAE3ro9wRJSZEUDxslVgWsIcbD/PS3N42I+ZOZm+Jx5XQ=="}, "outputPrefixType": "TINK", "keyId": 41726583, "status": "ENABLED"}]}'), 
cast( FROM_HEX('01027cb277c87df6f3534741db572e9d79ee090d3ab3848e6d7fc52f4c3b20177f1768ca8b807b384fe45c709d9d2407be') as bytes), b'ghijk') )  

4. The value must be "Encryption works" in Bigquery , showing the encryption works across Java and BQ. 

The code does not intend to be used in production! This is just for demonstration purspose only. 
