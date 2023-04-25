package util;

import javax.servlet.http.Cookie;

/**
 * @author: dai.jf
 * @date: 2021/8/10 23:16
 * @description: 单独查找想要的cookie
 */
public class CookieUtils {


    /**
     * 查找指定名称的cookie
     * @param name
     * @param cookies
     * @return
     */
    public static Cookie findCookie(String name,Cookie[] cookies ){
        if (name==null || cookies==null||cookies.length==0){
            return null;
        }

        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())){
                return cookie;
            }
        }
        return null;
    }
}
