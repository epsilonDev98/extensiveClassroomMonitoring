package io.middaymeal.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityClass extends AppCompatActivity implements VolleyApi.ResponseListener{
    RecyclerView rv;
    ArrayList<ModelClass> modelClasses = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        findViewById();
        getData();

    }

    public void getData() {
        modelClasses.clear();
        VolleyApi.getInstance().getClaassApi(this,this);
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
            JSONArray jArray = obj1.getJSONArray("class");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                modelClasses.add(new ModelClass(
                        ""+json_data.getString("id"),
                        ""+json_data.getString("name")
                ));

            }

             ClassAdapter classAdapter= new ClassAdapter(this,modelClasses);
            rv.setAdapter(classAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
