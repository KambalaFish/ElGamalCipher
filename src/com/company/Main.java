package com.company;

public class Main {

    public static void main(String[] args) {
        ElGamalCipher elGamalCipher = new ElGamalCipher(30803, 2);
        elGamalCipher.encrypt();
    }
}