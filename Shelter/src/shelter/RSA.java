/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import java.math.BigInteger;
import java.security.*;

/**
 *
 * @author minguez
 */
public class RSA {

    private final static BigInteger one = new BigInteger("1");
    private final static SecureRandom random = new SecureRandom();

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger modulus;

    // generate an N-bit (roughly) public and private key
    public RSA(int N) {
        BigInteger p = BigInteger.probablePrime(N / 2, random);
        BigInteger q = BigInteger.probablePrime(N / 2, random);
        BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

        modulus = p.multiply(q);
        publicKey = new BigInteger("65537");     // common value in practice = 2^16 + 1
        privateKey = publicKey.modInverse(phi);
    }

    public BigInteger encrypt(String s) {
        byte[] bytes = s.getBytes();
        BigInteger message = new BigInteger(bytes);
        return message.modPow(publicKey, modulus);
    }

    public String decrypt(BigInteger encrypted) {

        BigInteger decrypt = encrypted.modPow(privateKey, modulus);
        String resultado = new String(decrypt.toByteArray());
        return resultado;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public BigInteger getModulus() {
        return modulus;
    }

    public void setPublicKey(BigInteger a) {
        publicKey = a;
    }

    public void setPrivateKey(BigInteger a) {
        privateKey = a;
    }

    public void setModulus(BigInteger a) {
        modulus = a;
    }

    //PARA QUE VEAS COMO FUNCIONA RAFA
    /*
    public static void main(String[] args) {
        int N = Integer.parseInt("1024");
        RSA key = new RSA(N);
        System.out.println(key);

        BigInteger encrypt = key.encrypt("hola");
        String decrypt = key.decrypt(encrypt);
        System.out.println("encrypted = " + encrypt);

        System.out.println("decrypted = " + decrypt);

        String resultado = new String(encrypt.toByteArray());

        byte[] bytes = resultado.getBytes();
        BigInteger message = new BigInteger(bytes);
        System.out.println("encrypted2 = " + message);

    }
*/


}
