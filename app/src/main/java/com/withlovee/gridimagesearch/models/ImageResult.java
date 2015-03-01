package com.withlovee.gridimagesearch.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by vee on 2/24/15.
 */
public class ImageResult implements Serializable {

    public String fullUrl;
    public String thumbUrl;
    public String title;

    public ImageResult(JSONObject json) {
        try {
            this.fullUrl = json.getString("url");
            this.thumbUrl = json.getString("tbUrl");
            this.title = json.getString("title");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ImageResult> fromJSONArray(JSONArray imagesJSON){
        ArrayList<ImageResult> imageResults = new ArrayList<ImageResult>();
        for(int i = 0; i < imagesJSON.length(); i++){
            try {
                imageResults.add(new ImageResult(imagesJSON.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return imageResults;
    }
}
