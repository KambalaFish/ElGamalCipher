package com.company;

import java.util.Random;
import java.util.Scanner;

public class ElGamalCipher {
    private final int p;
    private final int g;
    private final Random random;
    public ElGamalCipher(int p, int g){
        random = new Random();
        this.p = p;
        this.g = g;
    }

    private int generateRandomNumber(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    private int calculatePowerByMod(int base, int power) {
        int result = 1;
        while (power > 0) {
            if ((power & 1) == 1)
                result = (result * base) % p;
            base = (base * base) % p;
            power = power >> 1;
        }
        return result;
    }

    public void encrypt(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input message: ");
        int message = scanner.nextInt();
        if (message>=p){
            System.out.println("Message must be less than P number");
            return;
        }
        int cB = generateRandomNumber(2, p-2);
        int dB = calculatePowerByMod(g, cB);
        System.out.println("Bob's secret key is: " + cB +". Bob's open key is: " + dB);
        System.out.println("Alice wants to send Bob message: "+message);
        int k = generateRandomNumber(1, p-2);
        System.out.println("Alice picked random number k = "+k);
        int r = calculatePowerByMod(g, k) % p;
        System.out.println("Alice calculates r = g^k mod p = "+g+"^"+k+" mod "+p+" = "+r);
        int e = message * calculatePowerByMod(dB, k) % p;
        System.out.println("Alice calculates e = message * dB^k mod p = "+message+" * "+dB+"^"+k+" mod "+p+" = "+e);
        System.out.println("Alice sends Bob pair: (r, e) = ("+r+", "+e+")");

        int decryptedMessage = e*calculatePowerByMod(r,p-1-cB) % p;
        System.out.println("Bob decrypts message using the pair and his secret key: e * r^(p-1-Cb) mod p = "+
        e+" * "+r+"^("+p+"-1"+"-"+cB+") mod "+p+" = "+decryptedMessage);
    }
}
