package calendar;

import java.util.Calendar;

public class MyCalendarLogic {

	public MyCalendar createMyCalendar(int... args){

		MyCalendar mc=new MyCalendar();

		Calendar cal=Calendar.getInstance();

		if(args.length==2){

			cal.set(Calendar.YEAR, args[0]);

			cal.set(Calendar.MONTH, args[1]-1);

		}

		mc.setYear(cal.get(Calendar.YEAR));

		if(mc.getYear() > 2018){
			mc.setGengou("令和" + (mc.getYear() -2018));


		}else if(mc.getYear() > 1988){
			mc.setGengou("平成"+ (mc.getYear() -1988));
		}else if(mc.getYear() > 1925){
			mc.setGengou("昭和" + (mc.getYear() -1925));
		}else if(mc.getYear() > 1911){
			mc.setGengou("大正" +(mc.getYear() -1911));
		}else{
			mc.setGengou("" + mc.getYear());
		}

		mc.setMonth(cal.get(Calendar.MONTH) + 1);

		cal.set(Calendar.DATE, 1);

		int before = cal.get(Calendar.DAY_OF_WEEK)-1;

		int daysCount =cal.getActualMaximum(Calendar.DAY_OF_MONTH);

		cal.set(Calendar.DATE, daysCount);

		int after=7-cal.get(Calendar.DAY_OF_WEEK);

		int total= before + daysCount + after;

		int rows=total/7;

		String[][]data=new String[rows][7];

		Calendar now=Calendar.getInstance();

		for(int i=0;i<rows;i++){
			for(int j=0; j<7; j++){
			if(i==0 && j<before || i==rows-1 && j>=(7-after)){
				data[i][j] = "";
			  }else{
				  int date=i*7+j+1 - before;

				  data[i][j] = String.valueOf(date);

				  if(now.get(Calendar.DATE) == date && now.get(Calendar.MONTH) == mc.getMonth() -1 && now.get(Calendar.YEAR)==mc.getYear()){
					  data[i][j]="*"+data[i][j];
				  }
			  	}
			}
		}

		mc.setData(data);
		return mc;
	}

}
