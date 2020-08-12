package io.middaymeal.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FeedbackManagerMainActivity extends AppCompatActivity implements VolleyApi.ResponseListener {
    RecyclerView rv;
    ArrayList<SchoolModel> schoolModels = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        findViewById();
        getData();
    }

    private void getData() {
        VolleyApi.getInstance().getSchool(this,this);
    }

    private void findViewById() {
        rv = (RecyclerView)findViewById(R.id.rv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Utility.clearPreferenceData(this);
            startActivity(new Intent(this,LoginActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("login");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                schoolModels.add(new SchoolModel(
                        ""+json_data.getString("school_id"),
                        ""+json_data.getString("school_name"),
                        ""+json_data.getString("district"),
                        ""+json_data.getString("state"),
                        ""+json_data.getString("address")
                ));

            }

            SchoolAdapter schoolAdapter = new SchoolAdapter(this,schoolModels);
            rv.setAdapter(schoolAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
