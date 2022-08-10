package sg.edu.rp.c346.id21018193.p13application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    ListView lvFlats;
    AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvFlats = findViewById(R.id.listviewFlats);

        client = new AsyncHttpClient();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ArrayList<Flats> alFlats = new ArrayList<>();

        client.get("https://data.gov.sg/api/action/datastore_search?resource_id=4af42bbf-1ac0-46bf-9318-6efa6aa475a7&limit=10", new JsonHttpResponseHandler() {

            int flatsBuilt;
            int years;

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    JSONArray jsonArrItems = response.getJSONArray("items");
                    JSONObject firstObj = jsonArrItems.getJSONObject(0);
                    JSONArray jsonArrForecasts = firstObj.getJSONArray("forecasts");
                    for(int i = 0; i < jsonArrForecasts.length(); i++) {
                        JSONObject jsonObjForecast = jsonArrForecasts.getJSONObject(i);
                        flatsBuilt = jsonObjForecast(Integer.parseInt(getString("flats_constructed")));
                        years = jsonObjForecast(Integer.parseInt(getString("years")));
                        Flats flats = new Flats(flatsBuilt, years);
                        alFlats.add(flats);
                    }
                }
                catch(JSONException ignored){

                }

                //POINT X – Code to display List View

                ArrayAdapter<Flats> aaFlats = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, alFlats);
                lvFlats.setAdapter(aaFlats);

            }//end onSuccess
        });
    }//end onResume

}