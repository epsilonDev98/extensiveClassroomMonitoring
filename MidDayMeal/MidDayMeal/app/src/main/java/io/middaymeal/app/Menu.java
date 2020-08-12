package io.middaymeal.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Menu extends AppCompatActivity implements VolleyApi.ResponseListener{
    RecyclerView rv;
    ArrayList<MenuModel> menuModels = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        findViewById();
        getData();
    }

    public void getData() {
        menuModels.clear();
        VolleyApi.getInstance().getMennu(this,this);
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
            JSONArray jArray = obj1.getJSONArray("login");
            //int len = jArray.length();
            for (int i = 0; i < jArray.length(); i++) {

                JSONObject json_data = jArray.getJSONObject(i);

                menuModels.add(new MenuModel(
                        ""+json_data.getString("day"),
                        ""+json_data.getString("food")
                ));
            }

            MenuAdapter studentAdapter = new MenuAdapter(this,menuModels);
            rv.setAdapter(studentAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Wrong Mobile No. or Password", Toast.LENGTH_SHORT).show();

        }

    }
}
