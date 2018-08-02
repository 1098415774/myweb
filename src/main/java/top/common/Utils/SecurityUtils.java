package top.common.Utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {

    public static String encryptPassWord(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        String result = base64Encoder.encode(md5.digest(password.getBytes("UTF-8")));
        return result;
    }

    public static boolean checkPassWord(String pwd, String dbpwd) throws UnsupportedEncodingException, NoSuchAlgorithmException {
            pwd = encryptPassWord(pwd);
            if (pwd.equals(dbpwd)){
                return true;
            }else {
                return false;
            }

    }
}
