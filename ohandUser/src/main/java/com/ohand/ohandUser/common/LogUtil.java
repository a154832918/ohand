package com.ohand.ohandUser.common;

public class LogUtil {

	private static final String FORMAT_CONTROLLER = "《《《《%s》》》》====>%s";

	public static String formatControllerLog(Object controller, String log) {
		return String.format(FORMAT_CONTROLLER, controller.getClass().getSimpleName(), log);
	}
}
