package cn.ecust.bs.guuguu.controller.rest;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import cn.ecust.bs.guuguu.domain.json.TimeSlotJson;
import cn.ecust.bs.guuguu.domain.json.TimeSlotJsons;



public class JsonExample {

	
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		TimeSlotJson ts = new TimeSlotJson();
		ts.setStart("99-0");
		ts.setEnd("xxx");
		
		TimeSlotJson ts1 = new TimeSlotJson();
		ts1.setStart("9229-0");
		ts1.setEnd("xdxx");
		TimeSlotJsons tss = new TimeSlotJsons();
		tss.getTimeSlotJsons().add(ts);
		tss.getTimeSlotJsons().add(ts1);
		String json = mapper.writeValueAsString(tss);
		System.out.println(json);
		
		
		
		TimeSlotJsons tsss = mapper.readValue(json, TimeSlotJsons.class);

		
		System.out.println(tsss.getTimeSlotJsons().get(0).getStart());
	}
}
