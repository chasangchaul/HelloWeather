package com.surefor.helloweather.entity;

import android.content.res.AssetManager;
import android.util.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ethan on 05/11/2015.
 */
public class CityManager {
    private static CityManager instance = null ;
    private final String LIST_CANADA = "city.list.ca.json" ;
    private final String LIST_US = "city.list.us.json" ;

    // private List<City> cities = new ArrayList<>() ;
    private Map<String, City> cities = new HashMap<>() ;

    public synchronized  static CityManager instance() {

        if (instance == null ) {
            instance = new CityManager() ;
        }

        return instance ;
    }

    private CityManager() {

    }

    public void clear() {
        cities.clear();
    }

    public Integer size() {
        return cities.size() ;
    }

    public void load(AssetManager asset) throws IOException {
        assert(asset != null) ;
        load(new InputStreamReader(asset.open(LIST_CANADA))) ;
        load(new InputStreamReader(asset.open(LIST_US))) ;
    }

    private void load(InputStreamReader in) throws IOException {
        JsonReader jsonReader = new JsonReader(in) ;
        try {
            jsonReader.beginArray();
            while(jsonReader.hasNext()) {
                load(jsonReader) ;
            }
            jsonReader.endArray();
        } finally {
            jsonReader.close();
        }
    }

    private void load(JsonReader jsonReader) throws IOException {
        City city = new City() ;

        jsonReader.beginObject();
        while(jsonReader.hasNext()) {
            String name = jsonReader.nextName() ;
            if("_id".equals(name)) {
                city.setId(jsonReader.nextLong());
            } else if("name".equals(name)) {
                city.setName(jsonReader.nextString());
            } else if("country".equals(name)) {
                city.setCountry(jsonReader.nextString());
            } else if("coord".equals(name)) {
                jsonReader.beginObject();
                while(jsonReader.hasNext()) {
                    name = jsonReader.nextName() ;
                    if("lon".equals(name)) {
                        city.setLongitude(jsonReader.nextDouble());
                    } else if("lat".equals(name)) {
                        city.setLatitude(jsonReader.nextDouble());
                    }
                }
                jsonReader.endObject();
            }
        }
        jsonReader.endObject();

        cities.put(city.getName() + "," + city.getCountry(), city) ;
    }
}
