package ru.kpfu.itis.gimaletdinova.controller;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.itis.gimaletdinova.util.http_client.HttpClient;
import ru.kpfu.itis.gimaletdinova.util.http_client.HttpClientImplementation;
import ru.kpfu.itis.gimaletdinova.util.http_client.HttpClientRequestException;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/itis")
public class ExchangeRateController {

    private final String rub = "RUB";
    private final String eur = "EUR";
    private final String usd = "USD";
    @GetMapping("/rate")
    public String getExchangeRate() {
        return getExchange(rub) + "\n" +
                getExchange(eur) + "\n" +
                getExchange(usd);
    }

    public static String getExchange(String currency) {
        String json = getJson(currency);
        String text;
        if (json != null) {
            Double info = getInfo(json, currency);
            text = currency + ": " + info;
        } else {
            text = "currency not found\n";
        }
        return text;
    }

    private static String getJson(String currency) {
        try {
            HttpClient client = new HttpClientImplementation();
            String url = "https://api.currencyapi.com/v3/latest";
            Map<String, String> map = new HashMap<>();
            map.put("apikey", "cur_live_FxjnPJr7UmQovSHqgtV90NOaH9jfQY1jTTXOUiX7");
            map.put("currencies", currency);
            return client.get(url, map);
        } catch (HttpClientRequestException ex) {
            return null;
        }
    }

    private static Double getInfo(String json, String currency) {
        JSONObject jsonObject = new JSONObject(json);
        return jsonObject.getJSONObject("data").getJSONObject(currency).getDouble("value");
    }
}
