select SAFE_CONVERT_BYTES_TO_STRING(AEAD.DECRYPT_BYTES(KEYS.KEYSET_FROM_JSON('{"primaryKeyId": 41726583, "key": [{"keyData": {"typeUrl": "type.googleapis.com/google.crypto.tink.AesGcmKey", "keyMaterialType": "SYMMETRIC", "value": "GiAE3ro9wRJSZEUDxslVgWsIcbD/PS3N42I+ZOZm+Jx5XQ=="}, "outputPrefixType": "TINK", "keyId": 41726583, "status": "ENABLED"}]}'), 
cast( FROM_HEX('01027cb277c87df6f3534741db572e9d79ee090d3ab3848e6d7fc52f4c3b20177f1768ca8b807b384fe45c709d9d2407be') as bytes), b'ghijk') )  


