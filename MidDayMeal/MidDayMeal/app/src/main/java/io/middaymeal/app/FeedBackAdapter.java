package io.middaymeal.app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class FeedBackAdapter extends
        RecyclerView.Adapter<FeedBackAdapter.MyViewHolder> implements VolleyApi.ResponseListener {

    private ArrayList<FeedbackModel> dataItem;
    private Context mContext;
    VolleyApi.ResponseListener responseListener=this;
    private LayoutInflater inflater;

    @Override
    public void _onResponseError(Throwable e) {

    }

    @Override
    public void _onNext(String obj) {
        try {

            JSONObject obj1 = new JSONObject(obj);
            JSONArray jArray = obj1.getJSONArray("msg");
            //int len = jArray.length();
            ((Feedback)mContext).getData();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView name,good,excellent,bad;
        ImageView photo;




        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            excellent = (TextView) view.findViewById(R.id.excellent);
            good = (TextView) view.findViewById(R.id.good);
            bad = (TextView) view.findViewById(R.id.bad);





        }



    }


    public FeedBackAdapter(Context mContext, ArrayList<FeedbackModel> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final FeedbackModel c = dataItem.get(position);
        holder.name.setText(c.getName());
        holder.excellent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingltonData.feedback="excellent";
                SingltonData.comment="excellent";
                SingltonData.feedback_head_id=dataItem.get(position).getId();
                callApi();
            }
        });


        holder.bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingltonData.feedback="bad";
                SingltonData.comment="bad";
                SingltonData.feedback_head_id=dataItem.get(position).getId();
                callApi();
            }
        });


        holder.good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingltonData.feedback="good";
                SingltonData.comment="good";
                SingltonData.feedback_head_id=dataItem.get(position).getId();
                callApi();
            }
        });


    }

    void callApi(){
        VolleyApi.getInstance().insetFeedback(mContext,this);
    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feedback,parent, false);

        return new MyViewHolder(v);
    }
}



