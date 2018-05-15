package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String ERROR_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {

        //initialization
        JSONObject sandwichObjects;
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        try {

            sandwichObjects = new JSONObject(json);
            JSONObject sandwichName = sandwichObjects.getJSONObject("name");
            mainName = sandwichName.getString("mainName");
            placeOfOrigin = sandwichObjects.getString("placeOfOrigin");
            description = sandwichObjects.getString("description");
            image = sandwichObjects.getString("image");

            JSONArray jsonArray_alsoKnowAs = sandwichName.getJSONArray("alsoKnownAs");
            for (int i = 0; i < jsonArray_alsoKnowAs.length(); i++) {
                alsoKnownAs.add(jsonArray_alsoKnowAs.getString(i));
            }

            JSONArray jsonArray_ingredients = sandwichName.getJSONArray("ingredients");
            for (int i = 0; i < jsonArray_ingredients.length(); i++) {
                ingredients.add(jsonArray_ingredients.getString(i));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

    }


}
