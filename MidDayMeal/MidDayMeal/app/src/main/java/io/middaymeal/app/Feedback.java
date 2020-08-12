package io.middaymeal.app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Feedback extends AppCompatActivity implements VolleyApi.ResponseListener{
    RecyclerView rv;
    FeedBackAdapter studentAdapter;
    ArrayList<FeedbackModel> feedbackModels = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        SingltonData.userId="0";
        findViewById();
        getData();
    }

    public void getData() {
        feedbackModels.clear();
        VolleyApi.getInstance().getFeedback(this,this);
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

                feedbackModels.add(new FeedbackModel(
                        ""+json_data.getString("id"),
                        ""+json_data.getString("name")
                ));

            }

            studentAdapter = new FeedBackAdapter(this,feedbackModels);
            rv.setAdapter(studentAdapter);

        } catch (JSONException e) {
            studentAdapter = new FeedBackAdapter(this,feedbackModels);
            rv.setAdapter(studentAdapter);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(true);

            //if successful
            builder.setTitle("Alert Dialog");
            builder.setMessage("Already sent Feedback. \n Again do in next month");
            builder.setInverseBackgroundForced(true);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    dialog.dismiss();
                    finish();
                }
            });
            builder.show();
            e.printStackTrace();
        }

    }


}
