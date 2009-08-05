package menu.util;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class ExceptionUtils {

	private ExceptionUtils() {
	}

	public static String getStringStackTrace(Throwable e) {
		String result = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			result = sw.toString();
		}
		catch (Throwable e2) {
			return result;
		}
		return result;
	}

}
