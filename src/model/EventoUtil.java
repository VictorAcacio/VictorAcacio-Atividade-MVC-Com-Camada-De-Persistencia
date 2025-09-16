package model;

import java.util.Calendar;
import java.util.Date;

public class EventoUtil {
	
	public static Date getDateFromString(String dateString) {
		
		
		Calendar date = Calendar.getInstance();
		
		//dd/MM/yyyy
		//0123456789
		date.set(Calendar.YEAR, Integer.parseInt(dateString.substring(6, 10)));
		date.set(Calendar.MONTH, Integer.parseInt(dateString.substring(3, 5)));
		date.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateString.substring(0, 2)));
		
		return date.getTime();
		
	}

}
