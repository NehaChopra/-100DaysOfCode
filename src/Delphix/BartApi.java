package Delphix;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Delphix.Models.Station;
import Delphix.parser.BartParser;
import Delphix.parser.CustomParser;

/*
 * BART API Helper - GET API 
 * 1. fetches the list of stations
 * 2. fetches the list of train from destination
 */
public class BartApi {
	private static final String API_KEY = "MW9S-E7SL-26DU-VV8V";
	private static final String BASE_API_URL = "http://api.bart.gov/api";
	private static final String STATIONS_URL = BASE_API_URL + "/stn.aspx?cmd=stns";
	private static final String ETD_URL = BASE_API_URL + "/etd.aspx?cmd=etd&orig=";
	private static final String GET = "GET";
	private static final String POST = "POST";

	/*
	 * GET VERB API to fetch the details of given API METHOD - GET Response CODE -
	 * 200 OK returns the json string response
	 */
	public static String GET(String api) throws Exception {
		String response = "";
		URL url = new URL(api + "&key=" + API_KEY + "&json=y");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// GET VERB
		conn.setRequestMethod(GET);
		// create the connection
		conn.connect();
		// fetch the response status of the Rest API
		int responsecode = conn.getResponseCode();
//			System.out.println("Response code is: " + responsecode);

		// if response code is not 200 then throw an exception else continue parsing
		if (responsecode != 200)
			throw new RuntimeException("HttpResponseCode: " + responsecode);
		else {
			String inline = "";
			Scanner sc = new Scanner(url.openStream());
			while (sc.hasNext()) {
				inline += sc.nextLine();
			}
			response = inline;
//				System.out.println("\nJSON Response in String format : " + response);
			sc.close();
		}

		return response;
	}

	/*
	 * List of station objects
	 */
	public static List<Station> parseStationJson() throws BartException {
		List<Station> stationList = new ArrayList<Station>();
		try {
			String response = GET(STATIONS_URL);
			JSONObject jsonObject = CustomParser.parseJson(response);
			stationList = BartParser.parseStation(jsonObject);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BartException(BartExceptionCodes.PARSING_ERROR.getCode(),
					BartExceptionCodes.PARSING_ERROR.getMessage());
		}
		return stationList;
	}

	public static List<Station> parseDepartureJson(String stationCode) throws BartException {
		List<Station> stationList = new ArrayList<Station>();
		try {
			String response = GET(ETD_URL + stationCode);
			JSONObject jsonObject = CustomParser.parseJson(response);
			stationList = BartParser.parseDepartures(jsonObject);

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new BartException(BartExceptionCodes.PARSING_ERROR.getCode(),
					BartExceptionCodes.PARSING_ERROR.getMessage());
		}
		return stationList;
	}
}
