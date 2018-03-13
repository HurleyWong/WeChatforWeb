package util;

import java.io.UnsupportedEncodingException;

/**
 * @Author: 丁杰
 * @Description:
 * @Date: Created in 22:41 2017/12/8
 */
public class StringUtil {

    public String toCN(String str) {
         /**
           * @Description:  将str的编码转为utf-8并返回
           * @param str
           * @return java.lang.String
           */
        if (str == null)
            return str;
        try {
            return new String(str.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }
}
