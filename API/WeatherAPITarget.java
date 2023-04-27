package RSSurfDB.API;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherAPITarget {

    public double lat = 29.4791;
    public double lng = -81.1231;
    public String params = "windSpeed,windDirection,waveHeight,waveDirection,wavePeriod";
    public Date start = new Date();
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    public String startString = sdf.format(start);

    WeatherAPITarget() {
    }

    public void getWeather() throws Exception {
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
