package com.model.encrypt;

/**
 * Created by Anastasia_Paramonova on 26.11.2016.
 */
public class HashEncrypt implements Encrypt {
    public String encryptString(String value) {
        return String.valueOf(value.hashCode());
    }
}
