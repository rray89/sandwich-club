package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();

    public static Sandwich parseSandwichJson(String json) {


        //declaration & initialization
        JSONObject sandwichObject;
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> alsoKnownAs = new ArrayList<>();
        List<String> ingredients = new ArrayList<>();

        try {

            // JSON example:
            // {
            //  "name":
            //  {
            //    "mainName": "Ham and cheese sandwich",
            //    "alsoKnownAs":[]
            //  },
            //
            //    "placeOfOrigin":"",
            //
            //    "description":"A ham and cheese
            //            sandwich is a common type of sandwich. It is made by putting cheese and sliced ham
            //            between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables
            //            like lettuce, tomato, onion or pickle slices can also be included. Various kinds of
            //            mustard and mayonnaise are also
            //            common.",
            //
            //    "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
            //
            //    "ingredients": ["Sliced bread","Cheese","Ham"]
            //}

            sandwichObject = new JSONObject(json);

            //"mainName" and "alsoKnownAs" are contained in "name" object
            JSONObject name = sandwichObject.getJSONObject("name");
            mainName = name.optString("mainName");

            JSONArray jsonArray_alsoKnowAs = name.getJSONArray("alsoKnownAs");

            for (int i = 0; i < jsonArray_alsoKnowAs.length(); i++) {
                alsoKnownAs.add(jsonArray_alsoKnowAs.optString(i));
            }

            //"name", "placeOfOrigin", "description", "image" and "ingredients" are contained in "sandwichObject" object
            placeOfOrigin = sandwichObject.optString("placeOfOrigin");
            description = sandwichObject.optString("description");
            image = sandwichObject.optString("image");

            JSONArray jsonArray_ingredients = sandwichObject.getJSONArray("ingredients");
            for (int i = 0; i < jsonArray_ingredients.length(); i++) {
                ingredients.add(jsonArray_ingredients.optString(i));
            }

        } catch (JSONException e) {
            //e.printStackTrace();
            Log.e(LOG_TAG, "Error occurred while parsing. ", e);
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);

    }


}
