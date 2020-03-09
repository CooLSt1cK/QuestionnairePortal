package com.aleksieienko.questionnaire.portal.db.entity;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class UserUtil {
    public static boolean fillPassword(User user,String password){
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            user.setSalt(new BigInteger(1, salt).toString(16));
            user.setHash(getHashedPassword(password,user.getSalt()));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static String getHashedPassword(String password, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        try {
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65736, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return new BigInteger(1, hash).toString(16);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw e;
        }
    }

    public static void main(String[] args) {
        User user = new User();
        String password="ukrainian_people";
        fillPassword(user,password);
        System.out.println(user.getHash());
        System.out.println(user.getSalt());
    }
}
