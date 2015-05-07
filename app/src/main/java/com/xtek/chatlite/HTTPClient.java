package com.xtek.chatlite;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class HTTPClient {

    private static String url_register_prefix = "http://192.168.128.98:8888/?email=";

    private String json;

    public static String beaconId = "";


/*
    public ArrayList<Beacon> parseJSON(String input) {

        json = input.replace("\\", "");
        try {
            JSONArray jsonArray = new JSONArray(json);
            //JSONObject result = jsonArray.getJSONObject(0);
            JSONObject aBeacon;
            for(int i = 0; i < jsonArray.length(); i++){
                aBeacon = jsonArray.getJSONObject(i);
                beacons.add(new Beacon(aBeacon.getString("_id"), aBeacon.getString("uuid"), aBeacon.getString("major"), aBeacon.getString("minor"), aBeacon.getString("companyID")));
            }

            }
        catch (JSONException e) { e.printStackTrace(); }
        return beacons;
    }
*/
    public String getRequest(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        try{
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            String comeIn;
            if (entity != null){
                comeIn = EntityUtils.toString(entity, "UTF-8");
                System.out.println(comeIn);
                //parseJSON(comeIn);
            }
            else
                comeIn = "receiveError";
            return comeIn;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return "not received anything";
    }
}
