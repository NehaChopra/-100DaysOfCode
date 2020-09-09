package Delphix;

import java.util.Vector;

//import java.io.*;
//import java.util.*;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.Scanner;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Iterator;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.time.LocalDateTime;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import java.time.ZonedDateTime;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
///*
// * To execute Java, please define "static void main" on a class
// * named Solution.
// *
// * If you need more classes, simply define them inline.
// */
//
//class Solution {
//  public static void main(String[] args){
//    try {
//      // Assumption-if train is leaving then it will display 0 min is it is leaving right away
//      BartRealTimeEstimator estimator = new BartRealTimeEstimator();
//      estimator.estimator("MONT",10);
//      //Bart api has an api where we can fetch the list of avaialable station. Method can be used to display the list and station code can be passed to estimator to fetch the real time available trains for menioned origination. 
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//  }
//}
//
//
//
//
//
//
///*
// * BartRealTimeEstimator - has a real-time 
// * information feed containing information about real-time estimated 
// * departures for specific stations
// */
//class BartRealTimeEstimator {
//
//  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a z");
//
//  /*
//   * preparing a final list where every train leaves to respective destination
//   * from source
//   */
//  public void printDepartingTrains(List<Station> stationList, int trainLimit) throws BartException {
//    List<DepartingTrain> estimateTimeOfDep = new ArrayList<DepartingTrain>();
//    List<DepartingTrain> fList = null;
//    try {
//      if (stationList != null && stationList.size() > 0) {
//        for (Station st : stationList) {
//          if (st != null && st.getEtd() != null && st.getEtd().size() > 0) {
//            for (Etd etd : st.getEtd()) {
//              if (etd != null && etd.getEstimate() != null && etd.getEstimate().size() > 0) {
//                for (Estimate est : etd.getEstimate()) {
//                  DepartingTrain dpTrain = new DepartingTrain();
//                  dpTrain.setStation(st.getName());
//                  dpTrain.setFinalEtd(est.getFinalEtd());
//                  dpTrain.setDestination(etd.getDestination());
//                  estimateTimeOfDep.add(dpTrain);
//                }
//              }
//            }
//          }
//        }
//
//        Date date = new Date();
//        System.out.println(" --------------------------------------------------");
//        System.out.println("  " + stationList.get(0).getName() + " " + dateTimeFormatter.format(ZonedDateTime.now(ZoneId.of("America/Los_Angeles"))));
//        System.out.println(" --------------------------------------------------");
//
//        if (estimateTimeOfDep != null) {
//          Collections.sort(estimateTimeOfDep);
//          if (estimateTimeOfDep.size() > 10) {
//            fList = estimateTimeOfDep.subList(0, trainLimit);
//          } else {
//            fList = estimateTimeOfDep;
//          }
//        }
//
//        if (fList != null && fList.size() > 0) {
//          for (DepartingTrain dT : fList) {
//            System.out.println(dT.getFinalEtd() + " min " + dT.getDestination());
//          }
//        }else{
//        System.out.println("Updates are temporary not available");
//        }
//      }
//
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      throw new BartException(BartExceptionCodes.INTERNAL_SERVER_ERROR.getCode(),
//          BartExceptionCodes.INTERNAL_SERVER_ERROR.getMessage());
//    }
//  }
//
//  public void estimator(String stationCode, int trainLimit) throws Exception {
//    try {
//      List<Station> stationList = BartApi.parseDepartureJson(stationCode);
//      printDepartingTrains(stationList, trainLimit);
//    } catch (Exception ex) {
//      ex.printStackTrace();
//    }
//  }
//}
//
//
//
//
//
//
///*
// * BART API Helper - GET API 
// * 1. fetches the list of stations
// * 2. fetches the list of train from destination
// */
//class BartApi {
//  private static final String API_KEY = "MW9S-E7SL-26DU-VV8V";
//  private static final String BASE_API_URL = "http://api.bart.gov/api";
//  private static final String STATIONS_URL = BASE_API_URL + "/stn.aspx?cmd=stns";
//  private static final String ETD_URL = BASE_API_URL + "/etd.aspx?cmd=etd&orig=";
//  private static final String GET = "GET";
//  private static final String POST = "POST";
//
//  /*
//   * GET VERB API to fetch the details of given API METHOD - GET Response CODE -
//   * 200 OK returns the json string response
//   */
//  public static String GET(String api) throws Exception {
//    String response = "";
//    URL url = new URL(api + "&key=" + API_KEY + "&json=y");
//    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//    // GET VERB
//    conn.setRequestMethod(GET);
//    // create the connection
//    conn.connect();
//    // fetch the response status of the Rest API
//    int responsecode = conn.getResponseCode();
////      System.out.println("Response code is: " + responsecode);
//
//    // if response code is not 200 then throw an exception else continue parsing
//    if (responsecode != 200)
//      throw new RuntimeException("HttpResponseCode: " + responsecode);
//    else {
//      String inline = "";
//      Scanner sc = new Scanner(url.openStream());
//      while (sc.hasNext()) {
//        inline += sc.nextLine();
//      }
//      response = inline;
////        System.out.println("\nJSON Response in String format : " + response);
//      sc.close();
//    }
//
//    return response;
//  }
//
//  /*
//   * List of station objects
//   */
//  public static List<Station> parseStationJson() throws BartException {
//    List<Station> stationList = new ArrayList<Station>();
//    try {
//      String response = GET(STATIONS_URL);
//      JSONObject jsonObject = CustomParser.parseJson(response);
//      stationList = BartParser.parseStation(jsonObject);
//
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      throw new BartException(BartExceptionCodes.PARSING_ERROR.getCode(),
//          BartExceptionCodes.PARSING_ERROR.getMessage());
//    }
//    return stationList;
//  }
//
//  public static List<Station> parseDepartureJson(String stationCode) throws BartException {
//    List<Station> stationList = new ArrayList<Station>();
//    try {
//      String response = GET(ETD_URL + stationCode);
//      JSONObject jsonObject = CustomParser.parseJson(response);
//      stationList = BartParser.parseDepartures(jsonObject);
//
//    } catch (Exception ex) {
//      ex.printStackTrace();
//      throw new BartException(BartExceptionCodes.PARSING_ERROR.getCode(),
//          BartExceptionCodes.PARSING_ERROR.getMessage());
//    }
//    return stationList;
//  }
//}
//
//
//
//
//
//
//
///*
// * BartParser to parse stations and trains at destination json object to POJO
// * Model- Station and Trains
// */
//class BartParser {
//  private static final String ROOT = "root";
//  private static final String STATIONS = "stations";
//  private static final String STATION = "station";
//  private static final String NAME = "name";
//  private static final String ABBR = "abbr";
//  private static final String ADDRESS = "address";
//  private static final String CITY = "city";
//  private static final String COUNTRY = "county";
//  private static final String STATE = "state";
//  private static final String ZIPCODE = "zipcode";
//  private static final String ETD = "etd";
//  private static final String ESTIMATE = "estimate";
//  private static final String DESTINATION = "destination";
//  private static final String ABBREVIATION = "abbreviation";
//  private static final String MINUTES = "minutes";
//  private static final String PLATFORM = "platform";
//  private static final String DELAY = "delay";
//  private static final String DIRECTION = "direction";
//
//  /*
//   * parse station object
//   */
//  public static List<Station> parseStation(JSONObject jsonObject) throws Exception {
//    List<Station> stationList = new ArrayList<Station>();
//    if (jsonObject != null) {
//      JSONObject root = (JSONObject) jsonObject.get(ROOT);
//      if (root != null) {
//        JSONObject stationObject = (JSONObject) root.get(STATIONS);
//        if (stationObject != null) {
//          JSONArray stations = (JSONArray) stationObject.get(STATION);
//          if (stations != null) {
//            for (int i = 0; i < stations.size(); i++) {
//              JSONObject stJson = (JSONObject) stations.get(i);
//              if (stJson != null) {
//                Station st = new Station();
//                if (stJson.get(NAME) != null) {
//                  st.setName((String) stJson.get(NAME));
//                }
//                if (stJson.get(ABBR) != null) {
//                  st.setAbbr((String) stJson.get(ABBR));
//                }
//                if (stJson.get(ADDRESS) != null) {
//                  st.setAddress((String) stJson.get(ADDRESS));
//                }
//                if (stJson.get(CITY) != null) {
//                  st.setCity((String) stJson.get(CITY));
//                }
//                if (stJson.get(COUNTRY) != null) {
//                  st.setCounty((String) stJson.get(COUNTRY));
//                }
//                if (stJson.get(STATE) != null) {
//                  st.setState((String) stJson.get(STATE));
//                }
//                if (stJson.get(ZIPCODE) != null) {
//                  st.setZipcode((String) stJson.get(ZIPCODE));
//                }
////                System.out.println("station = " + st);
//                stationList.add(st);
//              }
//            }
//          }
//        }
//      }
//    }
//    return stationList;
//  }
//
//  /*
//   * parse station node object with etd and estimate
//   */
//  public static List<Station> parseDepartures(JSONObject jsonObject) throws Exception {
//    List<Station> stationList = new ArrayList<Station>();
//    if (jsonObject != null) {
//      JSONObject root = (JSONObject) jsonObject.get(ROOT);
//      if (root != null) {
//        JSONArray stations = (JSONArray) root.get(STATION);
//        if (stations != null) {
//          for (int i = 0; i < stations.size(); i++) {
//            JSONObject stJson = (JSONObject) stations.get(i);
//            if (stJson != null) {
//              Station st = new Station();
//              if (stJson.get(NAME) != null) {
//                st.setName((String) stJson.get(NAME));
//              }
//              if (stJson.get(ABBR) != null) {
//                st.setAbbr((String) stJson.get(ABBR));
//              }
//              List<Etd> etdList = parseEtd(stJson);
//              if (etdList != null && etdList.size() > 0) {
//                st.setEtd(etdList);
//              }
//
//              // System.out.println("station = " + st);
//              stationList.add(st);
//            }
//          }
//        }
//      }
//    }
//    return stationList;
//  }
//
//  /*
//   * parse etd node object 
//   */
//  public static List<Etd> parseEtd(JSONObject jsonObject) throws Exception {
//    List<Etd> etdList = new ArrayList<Etd>();
//    JSONArray etd = (JSONArray) jsonObject.get(ETD);
//    if (etd != null) {
//      for (int i = 0; i < etd.size(); i++) {
//        JSONObject etdJson = (JSONObject) etd.get(i);
//        if (etdJson != null) {
//          Etd etdOb = new Etd();
//          if (etdJson.get(DESTINATION) != null) {
//            etdOb.setDestination((String) etdJson.get(DESTINATION));
//          }
//          if (etdJson.get(ABBREVIATION) != null) {
//            etdOb.setAbbreviation((String) etdJson.get(ABBREVIATION));
//          }
//          
//          List<Estimate> estimateList = parseEstimate(etdJson);
//          if (estimateList != null && estimateList.size() > 0) {
//            etdOb.setEstimate(estimateList);
//          }
//        
//          etdList.add(etdOb);
//        }
//      }
//    }
//    return etdList;
//  }
//
//  /*
//   * parse estimate node object 
//   */
//  public static List<Estimate> parseEstimate(JSONObject jsonObject) throws Exception {
//    List<Estimate> estimateList = new ArrayList<Estimate>();
//    JSONArray estimateArray = (JSONArray) jsonObject.get(ESTIMATE);
//    if (estimateArray != null) {
//      for (int i = 0; i < estimateArray.size(); i++) {
//        JSONObject estimateJson = (JSONObject) estimateArray.get(i);
//        if (estimateJson != null) {
//          Estimate estimate = new Estimate();
//          if (estimateJson.get(MINUTES) != null) {
//            estimate.setMinutes((String) estimateJson.get(MINUTES));
//          }
//          if (estimateJson.get(PLATFORM) != null) {
//            estimate.setPlatform((String) estimateJson.get(PLATFORM));
//          }
//          if (estimateJson.get(DIRECTION) != null) {
//            estimate.setDirection((String) estimateJson.get(DIRECTION));
//          }
//          if (estimateJson.get(DELAY) != null) {
//            estimate.setDelay((String) estimateJson.get(DELAY));
//          }
//          
//          estimateList.add(estimate);
//        }
//      }
//    }
//    return estimateList;
//  }
//}
//
//
//
//
//
//
//
//
//
///*
// * CustomParser - JSONParser
// * return custom JSONObject
// *  */
//class CustomParser {
//  private static final JSONParser parser = new JSONParser();
//
//  public static JSONObject parseJson(String response) throws Exception {
//    JSONObject jsonObject = (JSONObject) parser.parse(response);
//    return jsonObject;
//  }
//
//}
//
//
//
//
//
//
///*
// * Model - DepartingTrain
// * parameters - station , finalEtd, destination
// */
//class DepartingTrain implements Comparable<DepartingTrain> {
//  private String station;
//  private int finalEtd;
//  private String destination;
//
//  public String getDestination() {
//    return destination;
//  }
//
//  public void setDestination(String destination) {
//    this.destination = destination;
//  }
//
//  public int getFinalEtd() {
//    return finalEtd;
//  }
//
//  public void setFinalEtd(int finalEtd) {
//    this.finalEtd = finalEtd;
//  }
//
//  public String getStation() {
//    return station;
//  }
//
//  public void setStation(String station) {
//    this.station = station;
//  }
//
//  @Override
//  public int compareTo(DepartingTrain departingTrain) {
//    return (this.getFinalEtd() - departingTrain.getFinalEtd());
//  }
//
//  @Override
//  public int hashCode() {
//    final int prime = 31;
//    int result = 1;
//    result = prime * result + ((destination == null) ? 0 : destination.hashCode());
//    result = prime * result + finalEtd;
//    result = prime * result + ((station == null) ? 0 : station.hashCode());
//    return result;
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    DepartingTrain other = (DepartingTrain) obj;
//    if (destination == null) {
//      if (other.destination != null)
//        return false;
//    } else if (!destination.equals(other.destination))
//      return false;
//    if (finalEtd != other.finalEtd)
//      return false;
//    if (station == null) {
//      if (other.station != null)
//        return false;
//    } else if (!station.equals(other.station))
//      return false;
//    return true;
//  }
//
//  @Override
//  public String toString() {
//    return "DepartingTrain [station=" + station + ", finalEtd=" + finalEtd + ", destination=" + destination + "]";
//  }
//
//}
//
//
//
//
//
//
///*
// * Model - Station
// * parameters - name , abbr, address, city, county, state, zipcode
// */
//class Station {
//  private String name;
//  private String abbr;
//  private String address;
//  private String city;
//  private String county;
//  private String state;
//  private String zipcode;
//  private List<Etd> etd;
//
//  public String getName() {
//    return name;
//  }
//
//  public void setName(String name) {
//    this.name = name;
//  }
//
//  public String getAbbr() {
//    return abbr;
//  }
//
//  public void setAbbr(String abbr) {
//    this.abbr = abbr;
//  }
//
//  public String getAddress() {
//    return address;
//  }
//
//  public void setAddress(String address) {
//    this.address = address;
//  }
//
//  public String getCity() {
//    return city;
//  }
//
//  public void setCity(String city) {
//    this.city = city;
//  }
//
//  public String getCounty() {
//    return county;
//  }
//
//  public void setCounty(String county) {
//    this.county = county;
//  }
//
//  public String getState() {
//    return state;
//  }
//
//  public void setState(String state) {
//    this.state = state;
//  }
//
//  public String getZipcode() {
//    return zipcode;
//  }
//
//  public void setZipcode(String zipcode) {
//    this.zipcode = zipcode;
//  }
//
//  public List<Etd> getEtd() {
//    return etd;
//  }
//
//  public void setEtd(List<Etd> etd) {
//    this.etd = etd;
//  }
//
//  @Override
//  public int hashCode() {
//    final int prime = 31;
//    int result = 1;
//    result = prime * result + ((abbr == null) ? 0 : abbr.hashCode());
//    result = prime * result + ((address == null) ? 0 : address.hashCode());
//    result = prime * result + ((city == null) ? 0 : city.hashCode());
//    result = prime * result + ((county == null) ? 0 : county.hashCode());
//    result = prime * result + ((etd == null) ? 0 : etd.hashCode());
//    result = prime * result + ((name == null) ? 0 : name.hashCode());
//    result = prime * result + ((state == null) ? 0 : state.hashCode());
//    result = prime * result + ((zipcode == null) ? 0 : zipcode.hashCode());
//    return result;
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    Station other = (Station) obj;
//    if (abbr == null) {
//      if (other.abbr != null)
//        return false;
//    } else if (!abbr.equals(other.abbr))
//      return false;
//    if (address == null) {
//      if (other.address != null)
//        return false;
//    } else if (!address.equals(other.address))
//      return false;
//    if (city == null) {
//      if (other.city != null)
//        return false;
//    } else if (!city.equals(other.city))
//      return false;
//    if (county == null) {
//      if (other.county != null)
//        return false;
//    } else if (!county.equals(other.county))
//      return false;
//    if (etd == null) {
//      if (other.etd != null)
//        return false;
//    } else if (!etd.equals(other.etd))
//      return false;
//    if (name == null) {
//      if (other.name != null)
//        return false;
//    } else if (!name.equals(other.name))
//      return false;
//    if (state == null) {
//      if (other.state != null)
//        return false;
//    } else if (!state.equals(other.state))
//      return false;
//    if (zipcode == null) {
//      if (other.zipcode != null)
//        return false;
//    } else if (!zipcode.equals(other.zipcode))
//      return false;
//    return true;
//  }
//
//  @Override
//  public String toString() {
//    return "Station [name=" + name + ", abbr=" + abbr + ", address=" + address + ", city=" + city + ", county="
//        + county + ", state=" + state + ", zipcode=" + zipcode + ", etd=" + etd + "]";
//  }
//
//}
//
//
//
//
//
//
///*
// * Model - Etd
// * parameters - destination , abbreviation, limited, estimate
// */
//class Etd {
//  private String destination;
//  private String abbreviation;
//  private String limited;
//  private List<Estimate> estimate;
//
//  public String getDestination() {
//    return destination;
//  }
//
//  public void setDestination(String destination) {
//    this.destination = destination;
//  }
//
//  public String getAbbreviation() {
//    return abbreviation;
//  }
//
//  public void setAbbreviation(String abbreviation) {
//    this.abbreviation = abbreviation;
//  }
//
//  public String getLimited() {
//    return limited;
//  }
//
//  public void setLimited(String limited) {
//    this.limited = limited;
//  }
//
//  public List<Estimate> getEstimate() {
//    return estimate;
//  }
//
//  public void setEstimate(List<Estimate> estimate) {
//    this.estimate = estimate;
//  }
//
//  @Override
//  public int hashCode() {
//    final int prime = 31;
//    int result = 1;
//    result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
//    result = prime * result + ((destination == null) ? 0 : destination.hashCode());
//    result = prime * result + ((estimate == null) ? 0 : estimate.hashCode());
//    result = prime * result + ((limited == null) ? 0 : limited.hashCode());
//    return result;
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    Etd other = (Etd) obj;
//    if (abbreviation == null) {
//      if (other.abbreviation != null)
//        return false;
//    } else if (!abbreviation.equals(other.abbreviation))
//      return false;
//    if (destination == null) {
//      if (other.destination != null)
//        return false;
//    } else if (!destination.equals(other.destination))
//      return false;
//    if (estimate == null) {
//      if (other.estimate != null)
//        return false;
//    } else if (!estimate.equals(other.estimate))
//      return false;
//    if (limited == null) {
//      if (other.limited != null)
//        return false;
//    } else if (!limited.equals(other.limited))
//      return false;
//    return true;
//  }
//
//  @Override
//  public String toString() {
//    return "Etd [destination=" + destination + ", abbreviation=" + abbreviation + ", limited=" + limited
//        + ", estimate=" + estimate + "]";
//  }
//
//}
//
//
//
//
//
//
///*
// * Model - Estimate
// * parameters - minutes , platform, direction, delay, finalEtd
// */
//class Estimate {
//  private String minutes;
//  private String platform;
//  private String direction;
//  private String delay;
//  private int finalEtd;
//
//  public String getMinutes() {
//    return minutes;
//  }
//
//  public void setMinutes(String minutes) {
//    this.minutes = minutes;
//  }
//
//  public String getPlatform() {
//    return platform;
//  }
//
//  public void setPlatform(String platform) {
//    this.platform = platform;
//  }
//
//  public String getDirection() {
//    return direction;
//  }
//
//  public void setDirection(String direction) {
//    this.direction = direction;
//  }
//
//  public String getDelay() {
//    return delay;
//  }
//
//  public void setDelay(String delay) {
//    this.delay = delay;
//  }
//
//  public int getFinalEtd() {
//    int min = 0;
//    int del = 0;
//    if (this.minutes != null && this.minutes.equalsIgnoreCase("Leaving")){
//      return 0;
//    }
//    if (this.minutes != null && this.minutes!="") {
//      min = Integer.parseInt(this.minutes);
//    }
//    if (this.delay != null && this.delay!="") {
//      del = Integer.parseInt(this.delay);
//    }
//    this.finalEtd = min + del;
//    return this.finalEtd;
//  }
//
//  @Override
//  public int hashCode() {
//    final int prime = 31;
//    int result = 1;
//    result = prime * result + ((delay == null) ? 0 : delay.hashCode());
//    result = prime * result + ((direction == null) ? 0 : direction.hashCode());
//    result = prime * result + finalEtd;
//    result = prime * result + ((minutes == null) ? 0 : minutes.hashCode());
//    result = prime * result + ((platform == null) ? 0 : platform.hashCode());
//    return result;
//  }
//
//  @Override
//  public boolean equals(Object obj) {
//    if (this == obj)
//      return true;
//    if (obj == null)
//      return false;
//    if (getClass() != obj.getClass())
//      return false;
//    Estimate other = (Estimate) obj;
//    if (delay == null) {
//      if (other.delay != null)
//        return false;
//    } else if (!delay.equals(other.delay))
//      return false;
//    if (direction == null) {
//      if (other.direction != null)
//        return false;
//    } else if (!direction.equals(other.direction))
//      return false;
//    if (finalEtd != other.finalEtd)
//      return false;
//    if (minutes == null) {
//      if (other.minutes != null)
//        return false;
//    } else if (!minutes.equals(other.minutes))
//      return false;
//    if (platform == null) {
//      if (other.platform != null)
//        return false;
//    } else if (!platform.equals(other.platform))
//      return false;
//    return true;
//  }
//
//  @Override
//  public String toString() {
//    return "Estimate [minutes=" + minutes + ", platform=" + platform + ", direction=" + direction + ", delay="
//        + delay + ", finalEtd=" + finalEtd + "]";
//  }
//
//}
//
//
//
//
///*
// * HTTPS CODES ENUM
// * SUCCESS - 200 - OK
// * NO_CONTENT - 204
// * BAD_REQUEST - 400 
// * NOT_FOUND - 404
// * INTERNAL_SERVER_ERROR - 500
// * SERVICE_UNAVAILABLE - 503
// * BAD_GATEWAY - 502
// * 
// */
//enum HttpCodes {
//  SUCCESS("SUCCESS", 200), NO_CONTENT("NO_CONTENT", 204), BAD_REQUEST("BAD_REQUEST", 400),
//  NOT_FOUND("NOT_FOUND", 404), INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", 500),
//  SERVICE_UNAVAILABLE("SERVICE_UNAVAILABLE", 503), BAD_GATEWAY("BAD_GATEWAY", 502),;
//
//  String value;
//  int id;
//
//  HttpCodes(String value, int id) {
//    this.value = value;
//    this.id = id;
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public String getValue() {
//    return value;
//  }
//
//  public static HttpCodes getHttpCodes(String entityType) {
//    for (HttpCodes e : values()) {
//      if (e.getValue().equalsIgnoreCase(entityType)) {
//        return e;
//      }
//    }
//    return null;
//  }
//
//  public static HttpCodes getHttpCodes(int id) {
//    for (HttpCodes e : values()) {
//      if (e.getId() == id) {
//        return e;
//      }
//    }
//    return null;
//  }
//}
//
//
//
//
///*
// * interface for exception codes and exception messages
// */
//interface ExceptionCodes {
//  public Integer getCode();
//
//  public String getMessage();
//}
//
//
//
//
//
//
//
///*
// * BART exception description and codes 
// */
//enum BartExceptionCodes implements ExceptionCodes {
//  INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR"), PARSING_ERROR(501, "PARSING_ERROR"),;
//
//  private Integer code;
//  private String message;
//
//  BartExceptionCodes(Integer code, String message) {
//    this.code = code;
//    this.message = message;
//  }
//
//  @Override
//  public Integer getCode() {
//    return code;
//  }
//
//  @Override
//  public String getMessage() {
//    return message;
//  }
//}
//
//
//
//
//
//
///*
// * BartException - Exception information
// * Detail exception information
// */
//class BartException extends RuntimeException {
//private static final long serialVersionUID = -5617325434197351058L;
//  private Integer errorCode;
//  private String message;
//
//  private Map<Integer, String> errors = new HashMap<Integer, String>(0);
//
//  public BartException() {
//    super();
//  }
//
//  public BartException(Integer code, String message) {
//    super(code.toString().concat(" : " + message));
//    this.errorCode = code;
//    this.message = message;
//    errors.put(code, message);
//  }
//
//  public BartException(ExceptionCodes exception) {
//    super(exception.getCode().toString().concat(" : " + exception.getMessage()));
//    errors.put(exception.getCode(), exception.getMessage());
//  }
//
//  public void addError(Integer code, String message) {
//    errors.put(code, message);
//  }
//
//  public void addError(ExceptionCodes exception) {
//    errors.put(exception.getCode(), exception.getMessage());
//  }
//
//  public Map<Integer, String> getErrors() {
//    return errors;
//  }
//
//  public Integer getErrorCode() {
//    return errorCode;
//  }
//
//  public void setErrorCode(Integer errorCode) {
//    this.errorCode = errorCode;
//  }
//
//  public String getMessage() {
//    return message;
//  }
//
//  public void setMessage(String message) {
//    this.message = message;
//  }
//
//  public void setErrors(Map<Integer, String> errors) {
//    this.errors = errors;
//  }
//
//}
//
//
///* 
//Your previous Plain Text content is preserved below:
//
//This is just a simple shared plaintext pad, with no execution capabilities.
//
//When you know what language you'd like to use for your interview,
//simply choose it from the dropdown in the top bar.
//
//You can also change the default language your pads are created with
//in your account settings: https://coderpad.io/settings
//
//Enjoy your interview!
//
//===== Preface =====
//
//This question is very difficult in C and C++, where there is
//insufficient library support to answer it in an hour. If you
//prefer to program in one of those languages, please ask us to
//provide you with a question designed for those languages instead!
//
//
//===== Intro =====
//
//The Delphix San Francisco office is located in San Francisco’s
//financial district.  BART (Bay Area Rapid Transit) is a public
//transportation system serving the San Francisco Bay Area. Many
//engineers in the SF office use the Montgomery Street BART station
//to get to/from the office.
//
//As engineers in the office will tell you, the BART system is
//infamously off schedule. Luckily, the BART public API
//(http://api.bart.gov/docs/overview/index.aspx) has a real-time
//information feed containing information about real-time estimated
//departures for specific stations. Your goal is to write a small
//program that utilizes the BART API that will quickly tell us the
//current time, the next 10 trains leaving Montgomery Street station,
//where they are going, and in how many minutes they leave the
//station.
//
//Rules/constraints:
//* Print the trains in the order that they are leaving the station
//* Limit the output to the next 10 trains leaving the station
//* Print the number of minutes that the train will leave the station
//* Print the destination of the train
//
//Example output:
//
//    --------------------------------------------------
//    Montgomery St.  04:36:04 PM PDT
//    --------------------------------------------------
//    2 min  Dublin/Pleasanton
//    4 min  Daly City
//    5 min  Millbrae
//    5 min  Pittsburg/Bay Point
//    7 min  Fremont
//    9 min  Pleasant Hill
//    10 min  SF Airport
//    12 min  Daly City
//    12 min  Richmond
//    14 min  Millbrae
//
//Your output does not need to match this, this is just an example.
//If you have better ideas of how to display the data, please do!
//
//You should implement this in whatever language you're most
//comfortable with -- just make sure your code is production
//quality, well designed, and easy to read.
//
//Finally, please help us by keeping this question and your
//answer secret so that every candidate has a fair chance in
//future Delphix interviews.
//
//
//===== Steps =====
//
//1.  Choose the language you want to code in from the menu
//    labeled "Plain Text" in the top right corner of the
//    screen. You will see a "Run" button appear on the top
//    left -- clicking this will send your code to a Linux
//    server and compile / run it. Output will appear on the
//    right side of the screen.
//    
//    For information about what libraries are available for
//    your chosen language, see:
//
//        https://coderpad.io/languages
//
//2.  Pull up the documentation for the API you'll be using:
//
//      http://api.bart.gov/docs/etd/etd.aspx
//
//3.  You'll need an API key in order to query the data from
//    BART. You can create your own key
//    (http://www.bart.gov/schedules/developers/api) or use the demo
//    key:
//
//        MW9S-E7SL-26DU-VV8V
//
//    Make sure to use the API a bit to familiarize yourself 
//    with all of the outputs.
//
//4.  Implement the functionality described above, using data
//    fetched dynamically from the BART API described here:
//
//      http://api.bart.gov/docs/etd/etd.aspx
//
//5.  Add any tests for your code to the main() method of
//    your program so that we can easily run them.
//
//
//
//====== FAQs =====
//
//Q:  How do I know if my solution is correct?
//A:  Make sure you've read the prompt carefully and you're
//    convinced your program does what you think it should
//    in the common case. If your program does what the prompt 
//    dictates, you will get full credit. We do not use an
//    auto-grader, so we do not have any values for you to
//    check correctness against.
//    
//Q:  What is Delphix looking for in a solution?
//A:  After submitting your code, we'll have a pair of engineers
//    evaluate it and determine next steps in the interview process.
//    We are looking for correct, easy-to-read, robust code.
//    Specifically, ensure your code is idiomatic and laid out
//    logically. Ensure it is correct. Ensure it handles all edge
//    cases and error cases elegantly.
//    
//Q:  If I need a clarification, who should I ask?
//A:  Send all questions to the email address that sent you
//    this document, and an engineer at Delphix will get
//    back to you ASAP (we're pretty quick during normal
//    business hours).
//
//Q:  How long should this question take me?
//A:  Approximately 1 hour, but it could take more or less
//    depending on your experience with web APIs and the
//    language you choose.
//
//Q:  When is this due?
//A:  We will begin grading your answer 24 hours after it is
//    sent to you, so that is the deadline.
//    
//Q:  What if something comes up and I cannot complete the
//    problem during the 24 hours?
//A:  Reach out to us and let us know! We will work with you
//    to figure out an extension if necessary.
//
//Q:  How do I turn in my solution?
//A:  Anything you've typed into this document will be saved.
//    Email us when you are done with your solution. We will
//    respond confirming we've received the solution within
//    24 hours.
//
//Q:  Can I use any external resources to help me?
//A:  Absolutely! Feel free to use any online resources you
//    like, but please don't collaborate with anyone else.
//
//Q:  Can I use my favorite library in my program?
//A:  Unfortunately, there is no way to load external
//    libraries into CoderPad, so you must stick to what
//    they provide out of the box for your language:
//
//        https://coderpad.io/languages
//
//    If you really want to use something that's not
//    available, email the address that sent you this link
//    and we will work with you to find a solution.
//
//Q:  Can I code this up in a different IDE?
//A:  Of course! However, we do not have your environment
//    to run your code in. We ask that you submit your final
//    code via CoderPad (and make sure it can run). This gives
//    our graders the ability to run your code rather than guessing.
//
//Q:  Why does my program terminate unexpectedly in
//    CoderPad, and why can't I read from stdin or pass
//    arguments on the command line?
//A:  CoderPad places a limit on the runtime and amount of
//    output your code can use, but you should be able to
//    make your code fit within those limits. You can hard
//    code any arguments or inputs to the program in your
//    main() method or in your tests.
//
//Q:  I'm a Vim/Emacs fan -- is there any way to use those
//    keybindings? What about changing the tab width? Font
//    size?
//A:  Yes! Hit the button at the bottom of the screen that
//    looks like a keyboard.
// */
//
//
//
///*
//Write a function:
//
//class Solution { public int solution(int[] A); }
//
//that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
//
//For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
//
//Given A = [1, 2, 3], the function should return 4.
//
//Given A = [−1, −3], the function should return 1.
//
//Write an efficient algorithm for the following assumptions:
//
//N is an integer within the range [1..100,000];
//each element of array A is an integer within the range [−1,000,000..1,000,000].
//Copyright 2009–2020 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
//Solution
//Files
//task1
//
//*/

//you can also use imports, for example:
//import java.util.*;

//you can write to stdout for debugging purposes, e.g.
//System.out.println("this is a debug message");

//class Solution {
//	public static int evaluateLength(int n) {
//		int base = 10;
//		if (n < 2)
//			return n;
//		int ret = 1;
//
//		while (n > 0) {
//			ret++;
//			n /= base;
//		}
//		return ret;
//	}
//
//	public static int compress(String s, int k) {
//
//		int L = s.length();
//		if (L <= k)
//			return 0;
//
//		int i, fans = L - k;
//		int countS = 0, costS = 0;
//		int countE = 0, costE = 0;
//
//		for (i = k; i < L && s.charAt(i) == s.charAt(k); i++)
//			countE++;
//		costE = evaluateLength(countE);
//		for (; i < L;) {
//			int cnt = 0;
//			char d = s.charAt(i);
//			for (; i < L && d == s.charAt(i); i++)
//				cnt++;
//			costE += evaluateLength(cnt);
//		}
//
//		fans = Math.min(fans, costE);
//		if (k == 0)
//			return fans;
//
//		for (i = 1; i <= L - k; i++) {
//			if (i == 1) {
//				countS = 1;
//				costS = 1;
//			} else {
//				if (s.charAt(i - 1) == s.charAt(i - 2)) {
//					costS += evaluateLength(countS + 1) - evaluateLength(countS);
//					countS++;
//				} else {
//					costS++;
//					countS = 1;
//				}
//			}
//
//			if (i == L - k) {
//				costE = 0;
//				countE = 0;
//			} else if (s.charAt(i + k - 1) == s.charAt(i + k)) {
//				costE += evaluateLength(countE - 1) - evaluateLength(countE);
//				countE--;
//			} else {
//				costE--;
//				countE = 0;
//				for (int j = i + k; j < L && s.charAt(j) == s.charAt(i + k); j++)
//					countE++;
//			}
//
//			int cost = costS + costE;
//			if (i != L - k && s.charAt(i - 1) == s.charAt(i + k))
//				cost += evaluateLength(countS + countE) - evaluateLength(countS) - evaluateLength(countE);
//			fans = Math.min(fans, cost);
//		}
//
//		return fans;
//	}
//
//	public int solution(String S, int K) {
//		// write your code in Java SE 8
//		return compress(S, K);
//	}
//}

public class Solution {
	public int solution(String S,  int[] X, int[] Y){  

	Vector<Character> tags;

	Vector<Integer> tagsDis;


	int sameTagDis = 0;

	int noOfPoints = 0;

	int maxDis = Integer.MIN_VALUE;

	 int N = X.length;

	   vector<char> ::iterator itr;

	   

	   for(size_t i = 0; i < S.length() ; i++) {

	       int tmp = 0;

	       if(tags.size() == 0) {

	           tags.push_back(S.at(i));

	           tmp = sqrt((X[i] * X[i]) + (Y[i] * Y[i]));

	           tagsDis.push_back(tmp);

	           noOfPoints++;

	       } else {

	           

	           itr = std::find(tags.begin(), tags.end(), S.at(i));          

	           if (itr != tags.end()) {

	               int idx = std::distance(tags.begin(), itr);

	               tmp = sqrt((X[i] * X[i]) + (Y[i] * Y[i]));

	               tmp = std::max(tmp, tagsDis[idx]);

	               maxDis = std::max(maxDis, tmp);

	               tagsDis.push_back(tmp);

	           } else {

	               int tmpDis = sqrt((X[i] * X[i]) + (Y[i] * Y[i]));

	               tags.push_back(S.at(i));

	               noOfPoints++;

	           }

	           

	       }

	   }

	   

	   noOfPoints = 0;

	   for(size_t i = 0; i < N; i++) {

	       int tmpDis = sqrt((X[i] * X[i]) + (Y[i] * Y[i]));

	       if(maxDis > tmpDis) {

	           noOfPoints++;

	       }

	   }

	   return noOfPoints;

	}

	int main()

	{

	 string S("ABDCAD");

	 vector <int> X {2, -1,-4, -3, 3, 2};

	 vector <int> Y {2, -2, 4, 1, -3, 2};

	 

	 int N = solution(S, X, Y);

	 

	 cout << "N : " <<  N << "\n";

	}
}
