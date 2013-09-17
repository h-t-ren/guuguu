package calendars;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

public class MyCalendar {
	private Calendar calendar;
	private List selectedLists;

	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}

	public Calendar getCalendar() {
		if(calendar != null){
			return calendar;
		}else{
			calendar = Calendar.getInstance();
			return calendar;
		}
		
	}

	public void setSelectedLists(List selectedLists) {
		this.selectedLists = selectedLists;
	}

	public List getSelectedLists() {
		if(selectedLists != null){
			return selectedLists;
		}else{
			selectedLists = new LinkedList();
			return selectedLists;
		}
		
	}
	
}
