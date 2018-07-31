package com.demo.provaneogrid.util;

import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateTimeUtil {

	public static Date generateTime(int hour, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		return cal.getTime();
	}
	
    public static Date addMinutesToTime(Date originalTime, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(originalTime); 
		cal.add(Calendar.MINUTE, minutes); 
		return cal.getTime();
	}
    
    public static String formatDate(Date date) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mma");
    	String dateFormat = simpleDateFormat.format(date);
    	return dateFormat;
    }
	
}
