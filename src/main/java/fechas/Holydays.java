package fechas;

public enum Holydays {

	ANIO_NUEVO("01-01", DaysEnum.NONE.getDay()),	
	DIA_TRABAJO("05-01", DaysEnum.NONE.getDay()),
	DIA_INDEPENDENCIA("07-20", DaysEnum.NONE.getDay()),
	BATALLA_BOYACA("08-07", DaysEnum.NONE.getDay()),
	INMACULADA_CONCEPCION("12-08", DaysEnum.NONE.getDay()),
	NAVIDAD("12-25", DaysEnum.NONE.getDay()),
	
	REYES_MAGOS("01-06", DaysEnum.MONDAY.getDay()),
	DIA_SAN_JOSE("03-19", DaysEnum.MONDAY.getDay()),
	SAN_PEDRO_SAN_PABLO("06-29", DaysEnum.MONDAY.getDay()),
	ASUNCION_VIRGEN("08-15", DaysEnum.MONDAY.getDay()),
	DIA_RAZA("10-12", DaysEnum.MONDAY.getDay()),
	TODOS_SANTOS("11-01", DaysEnum.MONDAY.getDay()),
	INDEPENDENCIA_CARTAGENA("11-11", DaysEnum.MONDAY.getDay());
	
	private int day;
	private String date;

	Holydays(String date, int day) {
		this.setDate(date);
		this.setDay(day);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}
	
	
	                  
}
