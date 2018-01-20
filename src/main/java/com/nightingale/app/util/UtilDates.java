package com.nightingale.app.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class UtilDates {

    public static LocalDateTime getUTCFromTimeZone(LocalDateTime local, String zoneId) {

        ZonedDateTime localZonedDateTime = local.atZone(ZoneId.of(zoneId));

        return LocalDateTime.ofInstant(localZonedDateTime.toInstant(), ZoneOffset.UTC);

    }

    public static LocalDateTime getLocalDateFromUTC(LocalDateTime utc, String desiredZoneId) {

        ZonedDateTime utcZonedDateTime = utc.atZone(ZoneOffset.UTC);

        return LocalDateTime.ofInstant(utcZonedDateTime.toInstant(), ZoneId.of(desiredZoneId));

    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }
    
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }
    
    public static String convertUtilDateFormat(String strDateString, String strCurDateFormat, String strTargetFormat){
    	String result = null;
    	try{
    		DateFormat df = new SimpleDateFormat(strCurDateFormat);
    		DateFormat targetDateFormat = new SimpleDateFormat(strTargetFormat);
    		Date date = df.parse(strDateString);
    		result = targetDateFormat.format(date);
    	}catch(Exception igored){
    		
    	}
    	return result;
    }
}
