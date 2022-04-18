package SPClass;

import java.text.SimpleDateFormat;
import java.util.Date;

public class dateHelper {
	public static String getDate() {
		SimpleDateFormat f = new SimpleDateFormat("dd-MM-yyyy");
		Date d = new Date();
		return f.format(d);
	}
}
