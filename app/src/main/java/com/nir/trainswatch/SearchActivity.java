package com.nir.trainswatch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity {
    protected static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = getApplicationContext();

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        final AutoCompleteTextView txtStart = (AutoCompleteTextView) findViewById(R.id.txtStart);
        final AutoCompleteTextView txtEnd = (AutoCompleteTextView) findViewById(R.id.txtEnd);

        // TODO: move this data to SQLite if that'd be better
        if (Data.stationIds == null || Data.stationIds.isEmpty()) {
            Data.fillStationIds();
        }
        // TODO: attach all keys of stations to two dropdowns
        String[] stations = Data.stationNames;
        ArrayAdapter<String> stationListAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_station, stations);
        txtStart.setAdapter(stationListAdapter);
        txtEnd.setAdapter(stationListAdapter);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager conmgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activenetw = conmgr.getActiveNetworkInfo();
                // TODO: handle input validations
                if (activenetw != null && activenetw.isConnectedOrConnecting()) {
                    // TODO: handle connectivity-process buffering in UX
                    Toast.makeText(SearchActivity.context, "Processing...", Toast.LENGTH_SHORT).show();
                    SearchResults results = new SearchResults();
                    results.start = txtStart.getText().toString();
                    results.end = txtEnd.getText().toString();
                    String url =
                            "http://www.eservices.railway.gov.lk/schedule/searchTrain.action?selectedLocale=en"
                                    +"&searchCriteria.startStationID="+Data.stationIds.get(results.start)
                                    +"&searchCriteria.endStationID="+Data.stationIds.get(results.end)
                                    +"&searchCriteria.startTime=-1"
                                    +"&searchCriteria.endTime=-1"
                                    +"&searchDate=";
                    (new ResultParser()).execute(new String[]{url});
                } else {
                    // TODO: handle lack of internet in UX
                    Toast.makeText(SearchActivity.context, "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
