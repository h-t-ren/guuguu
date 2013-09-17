package calendars;

import java.util.Calendar;

public class CalendarMgr {
	private static CalendarMgr mgr;
	static{
		mgr = new CalendarMgr();
	}
	private CalendarMgr(){
		
	}
	
	public static CalendarMgr getInstance(){
		return mgr;
	}
	
	public MyCalendar getMyCalendar(){
		return new MyCalendar();
	}
	
	public Calendar getNewCalendar(){
		return Calendar.getInstance();
	}
	
	public void set(Calendar c1,Calendar c2){
		c1.set(c2.get(Calendar.YEAR), c2.get(Calendar.MONTH), 1);
	}
	
	public int getWeekday(Calendar c){
		int weekday = c.get(Calendar.DAY_OF_WEEK);
		
		if(weekday == 1){//第一天就是星期一
			weekday = 7;	
		}else{
			weekday=weekday-1;
		}
		return weekday;
	}
	
	public int[] initNows(){
		int[] nows = new int[3];
		Calendar now = Calendar.getInstance();
		nows[0] = now.get(Calendar.YEAR);
		nows[1] = now.get(Calendar.MONTH);
		nows[2] = now.get(Calendar.DAY_OF_MONTH);
		return nows;
	}
}
