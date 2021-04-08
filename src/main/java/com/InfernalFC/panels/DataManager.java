package com.InfernalFC.panels;

import com.google.gson.Gson;
import javax.inject.Inject;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

import static com.InfernalFC.panels.PointsEnum.*;

public class DataManager {
    private final static String baseUrl = "https://infernal-fc.com/api/";
    private RankData[] ranks;

    @Inject
    private DataManager() {}

    public PlayerData[] GetPlayerData(String searchString) {
        try {
            URL url = new URL(baseUrl + "Members?active=1&_start=0&_end=10&username="
                    + URLEncoder.encode(searchString, StandardCharsets.UTF_8.toString()));

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            return new Gson().fromJson(reader, PlayerData[].class);
        } catch (Exception e) {
            System.out.println(e);
            return new PlayerData[0];
        }
    }

    public PlayerData[] GetAltData(int id) {
        try {
            URL url = new URL(baseUrl + "Members?active=1&_start=0&_end=10&parentAccount=" + id);

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            return new Gson().fromJson(reader, PlayerData[].class);

        } catch (Exception e) {
            System.out.println(e);
            return new PlayerData[0];
        }
    }

    public RankData[] GetRankData() {
        if (ranks != null) {
            return ranks;
        }
        try {
            URL url = new URL(baseUrl + "ranks?_start=0&_end=5000");

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            ranks = new Gson().fromJson(reader, RankData[].class);

            return ranks;

        } catch (Exception e) {
            System.out.println(e);
            return new RankData[0];
        }
    }

    PointsData GetOverallPoints() {
        return GetPoints(OVERALL);
    }

    PointsData GetEhbPoints() {
        return GetPoints(EHB);
    }

    PointsData GetKcPoints() {
        return GetPoints(KC);
    }

    private PointsData GetPoints(PointsEnum score) {
        try {
            URL url;

            switch (score) {
                case EHB:
                    url = new URL(baseUrl + "Members/killcounts/ehb?_start=0&_end=1");
                    break;
                case KC:
                    url = new URL(baseUrl + "Members/killcounts/overall?_start=0&_end=1");
                    break;
                default:
                    url = new URL(baseUrl + "Members/points/overall?_start=0&_end=1");
                    break;
            }

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            PointsData[] pointsData  = new Gson().fromJson(reader, PointsData[].class);

            return pointsData[0];

        } catch (Exception e) {
            System.out.println(e);
            return new PointsData();
        }
    }

    public EventData[] GetEvents() {
        try {
            URL url = new URL(baseUrl + "Events?_end=10&_order=DESC&_sort=date&_start=0");

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            EventData[] eventData  = new Gson().fromJson(reader, EventData[].class);

            Date today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            eventData = Arrays.stream(eventData).filter(x -> x.getDate().after(today)).toArray(EventData[]::new);
            invertArray(eventData);

            return eventData;
        } catch (Exception e) {
            System.out.println(e);
            return new EventData[0];
        }
    }

    private void invertArray(EventData[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            EventData temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
    }
}

enum PointsEnum {
    OVERALL,
    EHB,
    KC
}
