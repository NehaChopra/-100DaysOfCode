package Delphix.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import Delphix.Models.Estimate;
import Delphix.Models.Etd;
import Delphix.Models.Station;

/*
 * BartParser to parse stations and trains at destination json object to POJO
 * Model- Station and Trains
 */
public class BartParser {
	private static final String ROOT = "root";
	private static final String STATIONS = "stations";
	private static final String STATION = "station";
	private static final String NAME = "name";
	private static final String ABBR = "abbr";
	private static final String ADDRESS = "address";
	private static final String CITY = "city";
	private static final String COUNTRY = "county";
	private static final String STATE = "state";
	private static final String ZIPCODE = "zipcode";
	private static final String ETD = "etd";
	private static final String ESTIMATE = "estimate";
	private static final String DESTINATION = "destination";
	private static final String ABBREVIATION = "abbreviation";
	private static final String MINUTES = "minutes";
	private static final String PLATFORM = "platform";
	private static final String DELAY = "delay";
	private static final String DIRECTION = "direction";

	/*
	 * parse station object
	 */
	public static List<Station> parseStation(JSONObject jsonObject) throws Exception {
		List<Station> stationList = new ArrayList<Station>();
		if (jsonObject != null) {
			JSONObject root = (JSONObject) jsonObject.get(ROOT);
			if (root != null) {
				JSONObject stationObject = (JSONObject) root.get(STATIONS);
				if (stationObject != null) {
					JSONArray stations = (JSONArray) stationObject.get(STATION);
					if (stations != null) {
						for (int i = 0; i < stations.size(); i++) {
							JSONObject stJson = (JSONObject) stations.get(i);
							if (stJson != null) {
								Station st = new Station();
								if (stJson.get(NAME) != null) {
									st.setName((String) stJson.get(NAME));
								}
								if (stJson.get(ABBR) != null) {
									st.setAbbr((String) stJson.get(ABBR));
								}
								if (stJson.get(ADDRESS) != null) {
									st.setAddress((String) stJson.get(ADDRESS));
								}
								if (stJson.get(CITY) != null) {
									st.setCity((String) stJson.get(CITY));
								}
								if (stJson.get(COUNTRY) != null) {
									st.setCounty((String) stJson.get(COUNTRY));
								}
								if (stJson.get(STATE) != null) {
									st.setState((String) stJson.get(STATE));
								}
								if (stJson.get(ZIPCODE) != null) {
									st.setZipcode((String) stJson.get(ZIPCODE));
								}
//								System.out.println("station = " + st);
								stationList.add(st);
							}
						}
					}
				}
			}
		}
		return stationList;
	}

	/*
	 * parse station node object with etd and estimate
	 */
	public static List<Station> parseDepartures(JSONObject jsonObject) throws Exception {
		List<Station> stationList = new ArrayList<Station>();
		if (jsonObject != null) {
			JSONObject root = (JSONObject) jsonObject.get(ROOT);
			if (root != null) {
				JSONArray stations = (JSONArray) root.get(STATION);
				if (stations != null) {
					for (int i = 0; i < stations.size(); i++) {
						JSONObject stJson = (JSONObject) stations.get(i);
						if (stJson != null) {
							Station st = new Station();
							if (stJson.get(NAME) != null) {
								st.setName((String) stJson.get(NAME));
							}
							if (stJson.get(ABBR) != null) {
								st.setAbbr((String) stJson.get(ABBR));
							}
							List<Etd> etdList = parseEtd(stJson);
							if (etdList != null && etdList.size() > 0) {
								st.setEtd(etdList);
							}

							System.out.println("station = " + st);
							stationList.add(st);
						}
					}
				}
			}
		}
		return stationList;
	}

	/*
	 * parse etd node object 
	 */
	public static List<Etd> parseEtd(JSONObject jsonObject) throws Exception {
		List<Etd> etdList = new ArrayList<Etd>();
		JSONArray etd = (JSONArray) jsonObject.get(ETD);
		if (etd != null) {
			for (int i = 0; i < etd.size(); i++) {
				JSONObject etdJson = (JSONObject) etd.get(i);
				if (etdJson != null) {
					Etd etdOb = new Etd();
					if (etdJson.get(DESTINATION) != null) {
						etdOb.setDestination((String) etdJson.get(DESTINATION));
					}
					if (etdJson.get(ABBREVIATION) != null) {
						etdOb.setAbbreviation((String) etdJson.get(ABBREVIATION));
					}
					
					List<Estimate> estimateList = parseEstimate(etdJson);
					if (estimateList != null && estimateList.size() > 0) {
						etdOb.setEstimate(estimateList);
					}
					
					etdList.add(etdOb);
				}
			}
		}
		return etdList;
	}

	/*
	 * parse estimate node object 
	 */
	public static List<Estimate> parseEstimate(JSONObject jsonObject) throws Exception {
		List<Estimate> estimateList = new ArrayList<Estimate>();
		JSONArray estimateArray = (JSONArray) jsonObject.get(ESTIMATE);
		if (estimateArray != null) {
			for (int i = 0; i < estimateArray.size(); i++) {
				JSONObject estimateJson = (JSONObject) estimateArray.get(i);
				if (estimateJson != null) {
					Estimate estimate = new Estimate();
					if (estimateJson.get(MINUTES) != null) {
						estimate.setMinutes((String) estimateJson.get(MINUTES));
					}
					if (estimateJson.get(PLATFORM) != null) {
						estimate.setPlatform((String) estimateJson.get(PLATFORM));
					}
					if (estimateJson.get(DIRECTION) != null) {
						estimate.setDirection((String) estimateJson.get(DIRECTION));
					}
					if (estimateJson.get(DELAY) != null) {
						estimate.setDelay((String) estimateJson.get(DELAY));
					}
					estimateList.add(estimate);
				}
			}
		}
		return estimateList;
	}
}
