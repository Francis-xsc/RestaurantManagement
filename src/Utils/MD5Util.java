package Utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author xsc
 * @time 2020/12/17 - 11:02
 */
public class MD5Util
{
    private static final String slat = "&#5123***&&%%$$#@";

    /**
     * 将字符串转为MD5码
     * @param dataStr 要转换的字符串
     * @return
     */
    public static String tomd5(String dataStr)
    {
        try
        {
            dataStr = dataStr + slat;
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(dataStr.getBytes(StandardCharsets.UTF_8));
            byte s[] = m.digest();
            String result = "";
            for (int i = 0; i < s.length; i++)
            {
                result+=Integer.toHexString((0x000000FF & s[i]) | 0xFFFFFF00).substring(6);
            }
            return result;
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return "";
    }
}
