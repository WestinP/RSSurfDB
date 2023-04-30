package RSSurfDB.API;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import java.time.*;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import RSSurfDB.API.Modals.SwellModal;
import RSSurfDB.API.Modals.WindModal;

public class WeatherAPITarget {

    ArrayList<WindModal> windData = new ArrayList<WindModal>();
    ArrayList<SwellModal> swellData = new ArrayList<SwellModal>();

    WeatherAPITarget() {
    }

    public static void main(String[] args) throws Exception {
        WeatherAPITarget weatherAPITarget = new WeatherAPITarget();
        weatherAPITarget.getWeather();
        weatherAPITarget.print();
        weatherAPITarget.insert();

        // dataRepositiory
        // .insertWind(new WindModal(LocalDateTime.now(), 10.0, 10.0, "Flagler Beach"));
        // dataRepositiory
        // .insertSwell(new SwellModal(LocalDateTime.now().plusHours(1), 10.0, 10.0,
        // 10.0, "Flagler Beach"));
    }

    public void getWeather() throws Exception {
        double lat = 29.4791;
        double lng = -81.1231;
        String params = "windSpeed,windDirection,waveHeight,waveDirection,wavePeriod";
        LocalDateTime start = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

        URL url = new URL(
                String.format(
                        "https://api.stormglass.io/v2/weather/point?lat=%f&lng=%f&params=%s&start=%s&end=%s",
                        lat, lng, params, start.format(formatter), start.plusDays(1).format(formatter)));
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization",
                "5a269510-ba9e-11ed-bc36-0242ac130002-5a26957e-ba9e-11ed-bc36-0242ac130002");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        response.toString();
        Console console = System.console();
        console.printf(response.toString());

        parser(response.toString(), start);
    }

    private static ObjectMapper gObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
        return objectMapper;
    }

    public void parser(String response, LocalDateTime start) throws Exception {
        ObjectMapper objectMapper = gObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response);
        jsonNode = jsonNode.get("hours");
        Integer i = 0;
        for (JsonNode node : jsonNode) {
            // node contrustion
            LocalDateTime TS = start;

            JsonNode windNode = node.get("windSpeed");
            JsonNode swellNode = node.get("waveHeight");
            JsonNode swellDirectionNode = node.get("waveDirection");
            JsonNode swellPeriodNode = node.get("wavePeriod");
            JsonNode windDirectionNode = node.get("windDirection");

            // print nodes
            // System.out.println(windNode.get("noaa").asDouble());
            // System.out.println(swellNode.get("noaa").asDouble());
            // System.out.println(swellDirectionNode.get("noaa").asDouble());
            // System.out.println(swellPeriodNode.get("noaa").asDouble());
            // System.out.println(windDirectionNode.get("noaa").asDouble());

            // add to arraylist
            WindModal windModal = new WindModal(TS.plusHours(i), windNode.get("noaa").asDouble(),
                    windDirectionNode.get("noaa").asDouble(), "Flagler Beach");
            windData.add(windModal);
            SwellModal swellModal = new SwellModal(TS.plusHours(i), swellNode.get("noaa").asDouble(),
                    swellDirectionNode.get("noaa").asDouble(), swellPeriodNode.get("noaa").asDouble(), "Flagler Beach");
            swellData.add(swellModal);
            i++;
        }
    }

    public void print() {
        for (WindModal windModal : windData) {
            System.out.println(windModal.TS + " " + windModal.Speed + " " + windModal.Direction + " " + windModal.Name);
        }
        for (SwellModal swellModal : swellData) {
            System.out.println(swellModal.TS + " " + swellModal.Height + " " + swellModal.Direction + " "
                    + swellModal.Period + " " + swellModal.Name);
        }
    }

    public void insert() {
        DataRepositiory dataRepositiory = new DataRepositiory();
        for (WindModal windModal : windData) {
            dataRepositiory.insertWind(windModal);
        }
        for (SwellModal swellModal : swellData) {
            dataRepositiory.insertSwell(swellModal);
        }
    }

}
