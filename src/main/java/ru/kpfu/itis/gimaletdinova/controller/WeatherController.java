package ru.kpfu.itis.gimaletdinova.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.gimaletdinova.dto.WeatherDto;
import ru.kpfu.itis.gimaletdinova.util.http_client.HttpClient;
import ru.kpfu.itis.gimaletdinova.util.http_client.HttpClientImplementation;
import ru.kpfu.itis.gimaletdinova.util.http_client.HttpClientRequestException;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/itis")
public class WeatherController {
    private final String city = "Kazan";
    @GetMapping("/weather")
    public String getWeather() {
        return city + ":    " + Objects.requireNonNull(getInfo(getJson(city)));
    }

    private String getJson(String city) {
        try {
            HttpClient client = new HttpClientImplementation();
            String url = "https://api.openweathermap.org/data/2.5/weather";
            Map<String, String> map = new HashMap<>();
            map.put("q", city);
            map.put("appid", "40fe55098a238c34bc04e0735dabf32d");
            return client.get(url, map);
        } catch (HttpClientRequestException ex) {
            return null;
        }

    }

    private WeatherDto getInfo(String json) {
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.getInt("cod") == HttpURLConnection.HTTP_OK) {
            String temperature =
                    String.valueOf(Math.round(jsonObject.getJSONObject("main").getInt("temp") - 273.15));
            String description =
                    jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            String humidity = String.valueOf(jsonObject.getJSONObject("main").getInt("humidity"));
            return new WeatherDto(
                    temperature,
                    description,
                    humidity);
        }
        return null;
    }
}
