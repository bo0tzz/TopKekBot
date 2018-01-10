package com.bo0tzz.topkekbot.engine.utils;

import com.bo0tzz.topkekbot.TopKekBot;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bo0tzz
 */
public class Util {

    public static final String KEY_URBAND = "dizTuKjw5YmshkKeiWx81uNJveqyp1ZzzudjsnlR2vv3ivQ7NM";


    private static final String KEY_OWM = "a81a1c5ad56bee73f957cc529ed07fa2";
    private static final String url = "http://api.openweathermap.org/data/2.5/weather?units=imperial&APPID=" + KEY_OWM + "&q=";

    public static String[] getWeather(String location) throws JSONException, IOException {
        String call = (url + location).replace(' ', '+');
        JSONObject json = new JSONObject(sendGet(call));

        if (json.getInt("cod") != 200) {
            return new String[]{"I CAN'T GET THE FUCKING WEATHER!"};
        }

        double temp = Math.round(json.getJSONObject("main").getDouble("temp"));
        double metric = Math.round((temp - 32) / 1.8000);

        String t;
        if (temp <= 32) {
            t = "ITS FUCKING FREEZING!";
        } else if (temp >= 33 && temp <= 60) {
            t = "ITS FUCKING COLD!";
        } else if (temp >= 61 && temp <= 75) {
            t = "ITS FUCKING PERFECT";
        } else {
            t = "ITS HOT AS FUCK";
        }

        return new String[]{t, "THE FUCKING WEATHER IN " + location.toUpperCase() + " IS " + temp + "F | " + metric + "C"};
    }

    public static String sendGet(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString().trim();
    }

    public static String searchGoogle(String query) {
        String url = "https://www.googleapis.com/customsearch/v1?key=" + TopKekBot.getGoogleKey() + "&cx=016322137100648159445:_tfnpvfyqok&q=";
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url + query.replace(" ", "+"))
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONArray array = response.getBody().getObject().getJSONArray("items");
        if (array.length() == 0) {
            return null;
        }
        return array.getJSONObject(0).getString("link");
    }

    public static String searchYoutube(String query) {
        String url = "https://www.googleapis.com/customsearch/v1?key=" + TopKekBot.getGoogleKey() + "&cx=016322137100648159445:79vkodvfzoq&q=";
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url + query.replace(" ", "+"))
                    .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        JSONArray array = response.getBody().getObject().getJSONArray("items");
        if (array.length() == 0) {
            return null;
        }
        return array.getJSONObject(0).getString("link");
    }
}
