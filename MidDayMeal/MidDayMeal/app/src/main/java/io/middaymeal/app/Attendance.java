package io.middaymeal.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Attendance extends AppCompatActivity implements VolleyApi.ResponseListener{
    RecyclerView rv;
    ArrayList<StudentModel> studentModels = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        findViewById();
        getData();
    }

    public void getData() {
        studentModels.clear();
        VolleyApi.getInstance().getStudent(this,this,Utility.getPreferences(this,Constants.id));
    }

    private void findViewById() {

        rv = (RecyclerView)findViewById(R.id.rv);
    }

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("data");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                studentModels.add(new StudentModel(
                        ""+json_data.getString("id"),
                        ""+json_data.getString("first_name"),
                        ""+json_data.getString("last_name"),
                        ""+json_data.getString("father_name"),
                        ""+json_data.getString("isAttandance"),
                        ""+json_data.getString("photo")
                ));

            }

            StudentAdapter studentAdapter = new StudentAdapter(this,studentModels);
            rv.setAdapter(studentAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
