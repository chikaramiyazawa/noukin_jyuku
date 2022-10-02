package calendar;

import java.io.Serializable;

public class MyCalendar implements Serializable{

	private String gengou;

	private int year;

	private int month;

	private String[][] data;

	public String getGengou(){
		return gengou;
	}

	public void setGengou(String gengou){
		this.gengou = gengou;
	}

	public int getYear(){
		return year;
	}

	public void setYear(int year){
		this.year = year;
	}

	public int getMonth(){
		return month;
	}

	public void setMonth(int month){
		this.month = month;
	}

	public String[][] getData(){
		return data;
	}

	public void setData(String[][] data){
		this.data = data;
	}

}
