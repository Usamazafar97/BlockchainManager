package com.example.blockchain;


import android.os.Build;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import androidx.annotation.RequiresApi;

public class Block {

    private String date, data, previousHash, hash;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Block(String date, String data) throws NoSuchAlgorithmException {
        this.date = date;
        this.data = data;
        this.previousHash = "0";
        this.hash = this.calculatehash();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    String calculatehash() throws NoSuchAlgorithmException {
        return SHA.toHexString(SHA.getSHA((this.date + this.data + this.previousHash).toString()));
    }
}


class SHA {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        // Static getInstance method is called with hashing SHA
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // digest() method called
        // to calculate message digest of an input
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }
}

