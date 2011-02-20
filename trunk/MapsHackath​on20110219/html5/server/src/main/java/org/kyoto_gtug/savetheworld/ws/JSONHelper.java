package org.kyoto_gtug.savetheworld.ws;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.kyoto_gtug.savetheworld.domain.Help;

public class JSONHelper {

	public static String toJSON(Help help) {
		if (help == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		StringWriter writer = new StringWriter();
		try {
			mapper.writeValue(writer, help);
			return writer.toString();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return String.format("{error:\"%s\"}", e.getMessage());
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return String.format("{error:\"%s\"}", e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			return String.format("{error:\"%s\"}", e.getMessage());
		}
	}


}
