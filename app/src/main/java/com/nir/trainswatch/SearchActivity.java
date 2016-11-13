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
import android.widget.Toast;


public class SearchActivity extends AppCompatActivity {
    protected static Context context;
    AutoCompleteTextView txtStart, txtEnd;
    Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = getApplicationContext();

        btnSearch = (Button) findViewById(R.id.btnSearch);
        txtStart = (AutoCompleteTextView) findViewById(R.id.txtStart);
        txtEnd = (AutoCompleteTextView) findViewById(R.id.txtEnd);

        // TODO: move this data to SQLite if that'd be better
        if (GovAdapterData.stationIds == null || GovAdapterData.stationIds.isEmpty()) {
            GovAdapterData.fillStationIds();
        }
        // TODO: attach all keys of stations to two dropdowns
        String[] stations = GovAdapterData.stationNames;
        ArrayAdapter<String> stationListAdapter = new ArrayAdapter<String>(this, R.layout.dropdown_station, stations);
        txtStart.setAdapter(stationListAdapter);
        txtEnd.setAdapter(stationListAdapter);
    }

    public void getSearchResults(View view){
        ConnectivityManager conmgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activenetw = conmgr.getActiveNetworkInfo();
        // TODO: handle input validations
        if (activenetw != null && activenetw.isConnectedOrConnecting()) {
            // TODO: handle connectivity-process buffering in UX
            Toast.makeText(SearchActivity.context, "Processing...", Toast.LENGTH_SHORT).show();
            SearchResults results = new SearchResults();
            results.start = txtStart.getText().toString();
            results.end = txtEnd.getText().toString();
//            String url =
//                    "http://www.eservices.railway.gov.lk/schedule/searchTrain.action?selectedLocale=en"
//                            +"&searchCriteria.startStationID="+ GovAdapterData.stationIds.get(results.start)
//                            +"&searchCriteria.endStationID="+ GovAdapterData.stationIds.get(results.end)
//                            +"&searchCriteria.startTime=-1"
//                            +"&searchCriteria.endTime=-1"
//                            +"&searchDate=";
//            (new ResultParser(new ResultsActivity(), context)).execute(new String[]{url});
            (new ResultParser(this, context)).execute(new String[]{results.start, results.end});
        } else {
            // TODO: handle lack of internet in UX
            Toast.makeText(SearchActivity.context, "Please connect to the Internet.", Toast.LENGTH_SHORT).show();
        }
    }
}
