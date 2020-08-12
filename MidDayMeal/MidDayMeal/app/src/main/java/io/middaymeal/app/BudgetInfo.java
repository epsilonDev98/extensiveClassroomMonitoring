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

public class BudgetInfo extends AppCompatActivity implements VolleyApi.ResponseListener{
    RecyclerView rv;
    ArrayList<budget_info_model> budget_info_models = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);

        findViewById();
        getData();
    }

    public void getData() {
        budget_info_models.clear();
        VolleyApi.getInstance().getBudgetInfo(this,this);
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

                budget_info_models.add(new budget_info_model(
                        ""+json_data.getString("head_name"),
                        ""+json_data.getString("amount")
                ));
            }

            BudgetInfoAdapter studentAdapter = new BudgetInfoAdapter(this,budget_info_models);
            rv.setAdapter(studentAdapter);

        } catch (JSONException e) {
            e.printStackTrace();

        }

    }
}
