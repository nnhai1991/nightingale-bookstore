package com.nightingale.app.util;

public class UtilFormat {

    public static final String DISPLAY_DATE_TIME = "(E) dd MMM uuuu HH:mm";
    public static final String DATE_TIME = "dd/MM/uuuu HH:mm";
    public static final String UTC_JAVSCRIPT_DISPLAY_FORMAT = "yyyy-MM-dd HH:mm";
    public static final String DATE_TIME_MOMENT = "DD/MM/YYYY HH:mm";

    public static final String PARSE_DATE_MOMENT = "YYYY,MM,DD";
    public static final String PARSE_DATE_TIME_MOMENT = "YYYY,MM,DD,HH,mm";
    public static final String DISPLAY_DATE_MOMENT = "(ddd) DD MMM YYYY";
    public static final String DISPLAY_DATE_TIME_MOMENT = "(ddd) DD MMM YYYY HH:mm";

    public static final String DATE_COMBODATE_FORMAT = "DD MMM YYYY";
    public static final String DATE = "dd/MM/uuuu";
    public static final String DATE_JAVASCRIPT = "dd/mm/yyyy";
    public static final String TIME = "HH:mm";
    public static final String TIME_JAVASCRIPT = "HH:MM";
    public static final String DISPLAY_DATE = "(E) dd MMM uuuu";
    public static final String DISPLAY_DATE_REPORT = "dd MMM uuuu";
    public static final String DISPLAY_UTIL_DATE_REPORT = "dd/MM/yyyy";
    public static final String SQL_INPUT_UTIL_DATE_FORMAT = "yyyy-MM-dd";
    
    public static final String DISPLAY_DATE_FORMAT = "dd MMM uuuu";
    public static final String DATEPICKER_JAVASCRIPT_FORMAT = "DD/MM/YYYY";
    //public static final String DATE_FORMAT = "YYYY-MM-DD";
    public static final String DISPLAY_DATE_TIME_FORMAT = "dd MMM uuuu HH:mm";
    public static final String DISPLAY_DATE_FORMAT_1 = "dd/MM/yyyy";
    
	public static String showOnlylast4Characters(String info) {

		String masked = info;

		int length = info.length();
		
		if (length >= 4) {
			String last4 = info.substring(length - 4, length);
			
			StringBuilder sb = new StringBuilder();
			
			for(int i = 0; i < (length - 4); i++) {
				sb.append("*");
			}
			
			masked = sb.append(last4).toString();
		}

		return masked;
	}
}
