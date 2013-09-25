package cn.ecust.bs.guuguu.client;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonPrinter {
	
	public static void print(Object o)
	{
	    //json output
	    ObjectMapper mapper = new ObjectMapper();
	    try {
			String json = mapper.writeValueAsString(o);
			System.out.println(json);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}

}
