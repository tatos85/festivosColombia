package fechas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateUtil2 {
	private long MILLISECONDS_DAY = 86400000;
	private ArrayList<String> holidays;
	private int year;

	public DateUtil2(int year) {
		this.year = year;
		this.holidays = new ArrayList<String>();
		this.holidays.add("01-01");// Primero de Enero
		this.holidays.add("05-01");// Dia del trabajo 1 de mayo
		this.holidays.add("07-20");// Independencia 20 de Julio
		this.holidays.add("08-07");// Batalla de boyaca 7 de agosto
		this.holidays.add("12-08");// Maria inmaculada 8 de diciembre
		this.holidays.add("12-25");// Navidad 25 de diciembre
		
		this.holidays.add(nextDay(Holydays.REYES_MAGOS.getDate(), Holydays.REYES_MAGOS.getDay()));
		this.holidays.add(nextDay(Holydays.DIA_SAN_JOSE.getDate(), Holydays.DIA_SAN_JOSE.getDay()));
		this.holidays.add(nextDay(Holydays.SAN_PEDRO_SAN_PABLO.getDate(), Holydays.SAN_PEDRO_SAN_PABLO.getDay()));
		this.holidays.add(nextDay(Holydays.ASUNCION_VIRGEN.getDate(), Holydays.ASUNCION_VIRGEN.getDay()));
		this.holidays.add(nextDay(Holydays.DIA_RAZA.getDate(), Holydays.DIA_RAZA.getDay()));
		this.holidays.add(nextDay(Holydays.TODOS_SANTOS.getDate(), Holydays.TODOS_SANTOS.getDay()));
		this.holidays.add(nextDay(Holydays.INDEPENDENCIA_CARTAGENA.getDate(), Holydays.INDEPENDENCIA_CARTAGENA.getDay()));
		
		//Semana santa
		String easter =  easterAlgorithm(year);
		System.out.println("Easter: " + easter);
		Date easterDate = null;
		try {
			easterDate = new SimpleDateFormat("yyyy-MM-dd").parse(this.year+"-"+easter);
			System.out.println("Easter Date: " + easterDate.toString());

			
			 this.holidays.add(easterDate(easterDate,EasterHolydays.JUEVES_SANTO));
			 this.holidays.add(easterDate(easterDate,EasterHolydays.VIERNES_SANTO));
			 this.holidays.add(easterDate(easterDate,EasterHolydays.ASCENSION_SENIOR));
			 this.holidays.add(easterDate(easterDate,EasterHolydays.CORPHUS_CHRISTI));
			 this.holidays.add(easterDate(easterDate,EasterHolydays.SAGRADO_CORAZON_JESUS));

			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		
		
	}

	public boolean isHoliday(int month, int day) {
		return this.holidays.contains(month + "-" +day);
	}
	
	private String easterDate(Date date, EasterHolydays easterHolyday) {
		Date newDate = new Date(date.getTime() + easterHolyday.getDay()* MILLISECONDS_DAY);
		Date newDate2 = getUTCdatetimeAsDate(newDate);
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(newDate2);
		int year = calendar.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		String d = getFormattedDate(year, month, day); 
		
		return	nextDay(d, easterHolyday.getDayToSum());
	}
	
	private String nextDay(String date, int sum) {
		
		Date dt = new Date();
		
		try {
			dt = new SimpleDateFormat("yyyy-MM-dd").parse(this.year+"-"+date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//var newDate = (sum === 7 ? date : new Date(date.getTime() + (((7 + sum) - date.getUTCDay()) % 7) * MILLISECONDS_DAY));
		
		
		Date newDate = (sum == 7 ? dt : new Date(dt.getTime() + (((7 + sum) - getUTCdatetimeAsDate(dt).getDay()) % 7) * MILLISECONDS_DAY));
		
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(newDate);
		int year = calendar.get(Calendar.YEAR);
		//Add one to month {0 - 11}
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		
		return getFormattedDate(year, month, day);
	}
	
	
	private String easterAlgorithm(Integer year) {
		Integer A = year % 19;
		Integer B = (int) Math.floor(year / 100);
		Integer C = year % 100;
		Integer D = (int)Math.floor(B / 4);
		Integer E = B % 4;
		Integer F = (int)Math.floor((B + 8) / 25);
		Integer G = (int)Math.floor((B - F + 1) / 3);
		Integer H = (19 * A + B - D - G + 15) % 30;
		Integer I = (int)Math.floor(C / 4);
		Integer K = C % 4;
		Integer L = (32 + 2 * E + 2 * I - H - K) % 7;
		Integer M = (int)Math.floor((A + 11 * H + 22 * L) / 451);
		Integer N = H + L - 7 * M + 114;
		Integer month = (int)Math.floor(N / 31);
		Integer day = 1 + (N % 31);
		return getFormattedDate(year, month, day);
	}

	
	
	private String getFormattedDate(int year, int month, int date) {
		//return String.valueOf(year).concat("-").concat(addZero(month)).concat("-").concat(addZero(date));
		return (addZero(month)).concat("-").concat(addZero(date));

	}



	private String addZero(int number) {
		String numberStr = String.valueOf(number);
		if (number > 0 && number < 10 && !numberStr.startsWith("0")) {
			return "0".concat(numberStr);
		} else {
			return numberStr;
		}
	}



	static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date getUTCdatetimeAsDate(Date date) {
	    // note: doesn't check for null
	    return stringDateToDate(getUTCdatetimeAsString(date));
	}

	public static String getUTCdatetimeAsString(Date date) {
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(date);

	    return utcTime;
	}

	public static Date stringDateToDate(String StrDate) {
	    Date dateToReturn = null;
	    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

	    try {
	        dateToReturn = (Date)dateFormat.parse(StrDate);
	    }
	    catch (ParseException e) {
	        e.printStackTrace();
	    }

	    return dateToReturn;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
