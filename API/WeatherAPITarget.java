package RSSurfDB.API;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherAPITarget {
    public static void main(String[] args) throws Exception {
        double lat = 58.7984;
        double lng = 17.8081;
        String params = "windSpeed,windDirection,waveHeight,waveDirection,wavePeriod";
        Date start = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String startString = sdf.format(start);

        URL url = new URL(
                String.format(
                        "https://api.stormglass.io/v2/weather/point?lat=%f&lng=%f&params=%s&startString",
                        lat, lng, params, startString));
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
    }
}
