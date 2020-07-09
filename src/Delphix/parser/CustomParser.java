package Delphix.parser;

/*
 * CustomParser - JSONParser
 * return custom JSONObject
 *  */
public class CustomParser {
	private static final JSONParser parser = new JSONParser();

	public static JSONObject parseJson(String response) throws Exception {
		JSONObject jsonObject = (JSONObject) parser.parse(response);
		return jsonObject;
	}

}
