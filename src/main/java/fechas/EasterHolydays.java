package fechas;

public enum EasterHolydays {
	


	JUEVES_SANTO(-3, DaysEnum.NONE.getDay()),	
	VIERNES_SANTO(-2, DaysEnum.NONE.getDay()),
	ASCENSION_SENIOR(39, DaysEnum.MONDAY.getDay()),
	CORPHUS_CHRISTI(60, DaysEnum.MONDAY.getDay()),
	SAGRADO_CORAZON_JESUS(68, DaysEnum.MONDAY.getDay());
	
	
	
	private int day;
	private int dayToSum;
	
	EasterHolydays(int day, int dayToSum) {
		this.day = day;
		this.dayToSum = dayToSum;
	}

	public int getDayToSum() {
		return dayToSum;
	}

	public void setDayToSum(int dayToSum) {
		this.dayToSum = dayToSum;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}





	
	
	                  
}
