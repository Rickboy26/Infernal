package com.InfernalFC.helpers;

import com.InfernalFC.models.EventData;
import com.InfernalFC.models.PlayerData;
import com.InfernalFC.models.PointsData;
import com.InfernalFC.models.RankData;
import com.google.gson.Gson;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

import static com.InfernalFC.helpers.PointsEnum.*;

@Singleton
public class DataManager {
    private final static String baseUrl = "https://infernal-fc.com/api/";
    private RankData[] ranks;

    @Inject
    private DataManager() {}

    public PlayerData[] GetPlayerData(String searchString) {
        try {
            URL url = new URL(baseUrl + "Members?active=1&_start=0&_end=10&Clan_id=1&username="
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
            URL url = new URL(baseUrl + "Members?active=1&_start=0&_end=10&Clan_id=1&parentAccount=" + id);

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            return new Gson().fromJson(reader, PlayerData[].class);

        } catch (Exception e) {
            System.out.println(e);
            return new PlayerData[0];
        }
    }

    public PlayerData GetParentData(int id) {
        try {
            URL url = new URL(baseUrl + "Members?active=1&_start=0&_end=10&Clan_id=1&id=" + id);

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            PlayerData[] result = new Gson().fromJson(reader, PlayerData[].class);
            return result[0];

        } catch (Exception e) {
            System.out.println(e);
            return new PlayerData();
        }
    }

    public RankData[] GetRankData() {
        if (ranks != null) {
            return ranks;
        }
        try {
            URL url = new URL(baseUrl + "ranks?_start=0&_end=5000&Clan_id=1");

            InputStream input = url.openStream();
            Reader reader = new InputStreamReader(input, StandardCharsets.UTF_8);
            ranks = new Gson().fromJson(reader, RankData[].class);

            return ranks;

        } catch (Exception e) {
            System.out.println(e);
            return new RankData[0];
        }
    }

    public PointsData GetOverallPoints() {
        return GetPoints(OVERALL);
    }

    public PointsData GetEhbPoints() {
        return GetPoints(EHB);
    }

    public PointsData GetKcPoints() {
        return GetPoints(KC);
    }

    public PointsData GetPetPoints() {
        return GetPoints(PETS);
    }

    private PointsData GetPoints(PointsEnum score) {
        try {
            URL url;

            switch (score) {
                case EHB:
                    url = new URL(baseUrl + "Members/killcounts/ehb?_start=0&_end=1&Clan_id=1");
                    break;
                case KC:
                    url = new URL(baseUrl + "Members/killcounts/overall?_start=0&_end=1&Clan_id=1");
                    break;
                default:
                    url = new URL(baseUrl + "Members/points/overall?_start=0&_end=1&Clan_id=1");
                    break;
                case PETS:
                    url = new URL(baseUrl + "Members/pets?_sort=count&_order=DESC&_start=0&_end=1&Clan_id=1");
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
            URL url = new URL(baseUrl + "Events?_end=10&_order=DESC&_sort=date&_start=0&Clan_id=1");

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
    KC,
    PETS
}
