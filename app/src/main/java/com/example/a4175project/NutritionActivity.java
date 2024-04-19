package com.example.a4175project;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NutritionActivity extends AppCompatActivity {

    private EditText foodEditText;
    private Button searchButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);

        foodEditText = findViewById(R.id.foodEditText);
        searchButton = findViewById(R.id.searchButton);
        resultTextView = findViewById(R.id.resultTextView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = foodEditText.getText().toString().trim();
                if (!query.isEmpty()) {
                    new FetchNutritionTask().execute(query);
                } else {
                    resultTextView.setText("Please enter a food item to search for nutrition information.");
                }
            }
        });
    }

    private class FetchNutritionTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String jsonResponse = null;

            try {
                // Construct the URL
                String baseUrl = "https://api.calorieninjas.com/v1/nutrition?query=";
                String apiKey = "J90O8iZATOB45dCdTMeZ2A==g4u3Kg5g0dVB8mn2";
                String query = params[0];
                URL url = new URL(baseUrl + query);

                // Open connection
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestProperty("X-Api-Key", apiKey);
                urlConnection.setRequestMethod("GET");

                // Read the response
                InputStream inputStream = urlConnection.getInputStream();
                StringBuilder buffer = new StringBuilder();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line).append("\n");
                }

                if (buffer.length() == 0) {
                    return null;
                }
                jsonResponse = buffer.toString();
            } catch (IOException e) {
                Log.e("NutritionActivity", "Error ", e);
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("NutritionActivity", "Error closing stream", e);
                    }
                }
            }
            return jsonResponse;
        }

        @Override
        protected void onPostExecute(String jsonResponse) {
            super.onPostExecute(jsonResponse);
            if (jsonResponse != null) {
                try {
                    // Parse JSON response
                    JSONObject jsonObject = new JSONObject(jsonResponse);
                    JSONArray itemsArray = jsonObject.getJSONArray("items");

                    // Display the retrieved nutrition information
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject item = itemsArray.getJSONObject(i);
                        result.append("Food: ").append(item.getString("name")).append("\n");
                        result.append("Calories: ").append(item.getDouble("calories")).append(" kcal").append("\n");
                        result.append("Serving Size: ").append(item.getDouble("serving_size_g")).append(" g").append("\n");
                        result.append("Total Fat: ").append(item.getDouble("fat_total_g")).append(" g").append("\n");
                        result.append("Saturated Fat: ").append(item.getDouble("fat_saturated_g")).append(" g").append("\n");
                        result.append("Protein: ").append(item.getDouble("protein_g")).append(" g").append("\n");
                        result.append("Sodium: ").append(item.getDouble("sodium_mg")).append(" mg").append("\n");
                        result.append("Potassium: ").append(item.getDouble("potassium_mg")).append(" mg").append("\n");
                        result.append("Cholesterol: ").append(item.getDouble("cholesterol_mg")).append(" mg").append("\n");
                        result.append("Total Carbohydrates: ").append(item.getDouble("carbohydrates_total_g")).append(" g").append("\n");
                        result.append("Fiber: ").append(item.getDouble("fiber_g")).append(" g").append("\n");
                        result.append("Sugar: ").append(item.getDouble("sugar_g")).append(" g").append("\n\n");
                    }
                    resultTextView.setText(result.toString());
                } catch (JSONException e) {
                    Log.e("NutritionActivity", "Error parsing JSON", e);
                }
            } else {
                resultTextView.setText("Failed to fetch nutrition information.");
            }
        }
    }
}
