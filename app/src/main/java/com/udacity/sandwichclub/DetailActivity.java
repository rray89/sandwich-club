package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    //declaration
    private Sandwich sandwich;
    private TextView alsoKnowAs_TextView;
    private TextView placeOfOrigin_TextView;
    private TextView description_TextView;
    private TextView ingredients_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.iv_image_of_sandwich);

        //initialization
        alsoKnowAs_TextView = findViewById(R.id.tv_also_known_as);
        placeOfOrigin_TextView = findViewById(R.id.tv_place_of_origin);
        description_TextView = findViewById(R.id.tv_description);
        ingredients_TextView = findViewById(R.id.tv_ingredients);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }


    private void populateUI() {

        for (String alsoKnownAs: sandwich.getAlsoKnownAs()) {
            alsoKnowAs_TextView.append(alsoKnownAs + "\n");
        }

        for (String ingredient: sandwich.getIngredients()) {
            ingredients_TextView.append(ingredient + "\n");
        }

        placeOfOrigin_TextView.setText(sandwich.getPlaceOfOrigin());
        description_TextView.setText(sandwich.getDescription());

    }


}
