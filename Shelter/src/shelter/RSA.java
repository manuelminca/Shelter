/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shelter;

import static java.lang.Integer.sum;
import static java.lang.Long.sum;
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
    public RSA(int bits) {
        int bitlen = bits;
        SecureRandom r = new SecureRandom();
        BigInteger p = new BigInteger(bitlen / 2, 100, r);
        BigInteger q = new BigInteger(bitlen / 2, 100, r);
        modulus = p.multiply(q);
        BigInteger m = (p.subtract(BigInteger.ONE)).multiply(q
            .subtract(BigInteger.ONE));
        publicKey = new BigInteger("3");
        while (m.gcd(publicKey).intValue() > 1) {
          publicKey = publicKey.add(new BigInteger("2"));
        }
        privateKey = publicKey.modInverse(m);
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
    
    public String random(){
        String result;
        int valorEntero = (int) Math.floor(Math.random()*(50000-90000+1)+90000);
        
        result = valorEntero + "";
        
        return result;
        
    }
    
    public String decryptAux(BigInteger encrypted) {
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
    
    public BigInteger toBigInteger(String foo){
        return new BigInteger(foo);
    }

    public String toString(BigInteger bar){
        return bar.toString();
    }

    //PARA QUE VEAS COMO FUNCIONA RAFA
    
   /* public static void main(String[] args) {
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

    }*/
    


}
