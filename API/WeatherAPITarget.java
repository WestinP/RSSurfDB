package RSSurfDB.API;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import java.time.*;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import RSSurfDB.API.Modals.RatingModal;
import RSSurfDB.API.Modals.ReviewModal;
import RSSurfDB.API.Modals.SwellModal;
import RSSurfDB.API.Modals.WindModal;

public class WeatherAPITarget {

    ArrayList<WindModal> windData = new ArrayList<WindModal>();
    ArrayList<SwellModal> swellData = new ArrayList<SwellModal>();

    WeatherAPITarget() {
    }

    // ! main fuction for user input
    public static void main(String[] args) throws Exception {
        WeatherAPITarget weatherAPITarget = new WeatherAPITarget();
        // get user input from console
        Console console = System.console();
        weatherAPITarget.printMenu();
        String choice = console.readLine("Enter a Choice: ");
        // System.out.println("1. Get Weather");
        // System.out.println("2. Print Weather");
        // System.out.println("3. Insert Review");
        // System.out.println("4. Get Review");
        // System.out.println("5. Get Rating");
        // System.out.println("6. Exit");

        while (!choice.equals("6")) {
            switch (choice) {
                case "1":
                    weatherAPITarget.getWeather();
                    weatherAPITarget.insert();
                    weatherAPITarget.ratingCalcAlgorithm();
                    break;
                case "2":
                    weatherAPITarget.getSwellData();
                    weatherAPITarget.getWindData();
                    weatherAPITarget.print();
                    break;
                case "3":
                    weatherAPITarget.insertReview();
                    break;
                case "4":
                    weatherAPITarget.getReviews();
                    break;
                case "5":
                    weatherAPITarget.getRating();
                    break;
                default:
                    System.out.println("Invalid Choice");
                    break;
            }
            weatherAPITarget.printMenu();
            choice = console.readLine("Enter a Choice: ");
        }

        // dataRepositiory
        // .insertWind(new WindModal(LocalDateTime.now(), 10.0, 10.0, "Flagler Beach"));
        // dataRepositiory
        // .insertSwell(new SwellModal(LocalDateTime.now().plusHours(1), 10.0, 10.0,
        // 10.0, "Flagler Beach"));
    }

    // !function that calls stromglass api to get weather data
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

    // ! function that parses json Object menu
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

    // ! function that prints json object
    public void print() {
        for (WindModal windModal : windData) {
            System.out
                    .println(windModal.TS + " Wind Speed " + windModal.Speed + " Wind Direction " + windModal.Direction
                            + " Location " + windModal.Name);
        }
        for (SwellModal swellModal : swellData) {
            System.out.println(swellModal.TS + " Swell Height " + swellModal.Height + " Swell Direction "
                    + swellModal.Direction + " Swell Period "
                    + swellModal.Period + " Location " + swellModal.Name);
        }
    }

    // ! function that inserts data into database through dataRepositiory
    public void insert() {
        DataRepositiory dataRepositiory = new DataRepositiory();
        for (WindModal windModal : windData) {
            dataRepositiory.insertWind(windModal);
        }
        for (SwellModal swellModal : swellData) {
            dataRepositiory.insertSwell(swellModal);
        }
    }

    // !Simple menu function
    public void printMenu() {
        System.out.println("1. Run Get Weather");
        System.out.println("2. Print Current Weather");
        System.out.println("3. Insert Review");
        System.out.println("4. Get Reviews");
        System.out.println("5. Get Rating");
        System.out.println("6. Exit");
    }

    // ! that causes data repositiory to insert reviews
    public void insertReview() {
        Console console = System.console();
        String name = console.readLine("Enter location: ");
        String review = console.readLine("Enter your review: ");
        LocalDateTime TS = LocalDateTime.now();
        DataRepositiory dataRepositiory = new DataRepositiory();
        dataRepositiory.insertReview(new ReviewModal(TS, Integer.valueOf(review), name));
    }

    // ! function that causes data repositiory to insert ratings
    public void insertRating() {
        Console console = System.console();
        String name = console.readLine("Enter location: ");
        String rating = console.readLine("Enter your rating: ");
        LocalDateTime TS = LocalDateTime.now();
        DataRepositiory dataRepositiory = new DataRepositiory();
        dataRepositiory.insertRating(new RatingModal(TS, Integer.valueOf(rating), name));
    }

    // ! function that calculates rating
    public void ratingCalcAlgorithm() {
        getSwellData();
        getWindData();
        double waveScore = 0;
        double windScore = 0;
        double totalScore = 0;
        for (SwellModal swellModal : swellData) {
            waveScore += swellModal.Height * swellModal.Period;
        }
        for (WindModal windModal : windData) {
            windScore -= windModal.Speed;
        }
        totalScore = waveScore + windScore;
        System.out.println("Wave Score: " + waveScore);
        System.out.println("Wind Score: " + windScore);
        System.out.println("Total Score: " + totalScore);
    }

    // ! function that gets reviews
    public void getReviews() {
        Console console = System.console();
        String name = console.readLine("Enter location: ");
        DataRepositiory dataRepositiory = new DataRepositiory();
        System.out.println(dataRepositiory.getReview(LocalDateTime.now(), name));
    }

    // ! function that gets ratings
    public void getRating() {
        Console console = System.console();
        String name = console.readLine("Enter location: ");
        LocalDateTime TS = LocalDateTime.now();
        DataRepositiory dataRepositiory = new DataRepositiory();
        System.out.println(dataRepositiory.getRating(TS, name));
    }

    // ! function that gets swell data
    public void getSwellData() {
        DataRepositiory dataRepositiory = new DataRepositiory();
        swellData = dataRepositiory.getSwellByDate(LocalDateTime.now(), "Flagler Beach");
    }

    // ! function that gets wind data
    public void getWindData() {
        DataRepositiory dataRepositiory = new DataRepositiory();
        windData = dataRepositiory.getWindByDate(LocalDateTime.now(), "Flagler Beach");
    }
}
