package client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncoder {

    /**
     * Encodes user's password using MD5 algorithm
     * @param password user's password
     * @return byte array with encoded input string
     * @throws NoSuchAlgorithmException
     */

    public static String encode(String password) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(password.getBytes());

        StringBuffer sb = new StringBuffer();
        String hex;
        for (byte data : md5.digest()) {
            hex = Integer.toHexString(0xFF & data);
            if (hex.length() == 1) sb.append('0');
            sb.append(hex);
        }
        return sb.toString();
    }
}
