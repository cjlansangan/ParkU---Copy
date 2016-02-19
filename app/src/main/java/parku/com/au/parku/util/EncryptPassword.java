package parku.com.au.parku.util;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Celdane.Lansangan on 2/18/2016.
 */
public class EncryptPassword {

    String SHAHash;

    public String computeSHAHash(String password)
    {
        MessageDigest mdSha2 = null;
        try
        {
            mdSha2 = MessageDigest.getInstance("SHA-256");
            mdSha2.update(password.getBytes());

        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }

        byte[] bytes = mdSha2.digest();
        try {
            SHAHash=convertToHex(bytes);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return SHAHash;
    }

    private static String convertToHex(byte[] bytes) throws java.io.IOException {

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }


}
