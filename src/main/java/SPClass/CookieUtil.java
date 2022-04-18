package SPClass;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static void addCookie(HttpServletResponse resp, String name, String value, int hour) {
		Cookie c = new Cookie(name, value);
		c.setPath("/");
		c.setMaxAge(hour);
		resp.addCookie(c);
	}
	public static String getCookie(HttpServletRequest req, String name) {
		Cookie c[] = req.getCookies();
		if(c != null) {
			for(Cookie i:c) {
				if(i.getName().equals(name)) {
					return i.getValue();
				}
			}
			return "";
		}
		return "";
	}
	public static void deleteCookie(HttpServletRequest req,HttpServletResponse resp, String name) {
		for(Cookie i : req.getCookies()) {
			if(i.getName().equals(name)) {
				Cookie c = new Cookie(name, "");
				c.setPath("/");
				c.setMaxAge(1);
				resp.addCookie(c);
			}	
		}
	}
	public static void updateCookie(HttpServletResponse resp,HttpServletRequest req, String name, String value, int hour) {
		for(Cookie i : req.getCookies()) {
			if(i.getName().equals(name)) {
				i.setValue(value);
				resp.addCookie(i);
			}	
		}
	}
}
