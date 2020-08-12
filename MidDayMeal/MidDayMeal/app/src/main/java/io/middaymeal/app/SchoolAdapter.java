package io.middaymeal.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class SchoolAdapter extends
        RecyclerView.Adapter<SchoolAdapter.MyViewHolder> implements VolleyApi.ResponseListener{

    private ArrayList<SchoolModel> dataItem;
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
            ((Attendance)mContext).getData();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    /**
     * View holder class
     * */
    public class MyViewHolder extends RecyclerView.ViewHolder   {
        public TextView name,address,city,state;
        ImageView photo;
        CardView layout;




        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            address = (TextView) view.findViewById(R.id.address);
            city = (TextView) view.findViewById(R.id.city);
            state = (TextView) view.findViewById(R.id.state);
            layout = (CardView) view.findViewById(R.id.layout);





        }



    }


    public SchoolAdapter(Context mContext, ArrayList<SchoolModel> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final SchoolModel c = dataItem.get(position);
        holder.name.setText(c.getSchool_name());
        holder.city.setText(c.getDistrict());
        holder.address.setText(c.getAddress());
        holder.state.setText(c.getState());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SingltonData.schoolId=c.getSchool_id();
                view.getContext().startActivity(new Intent(view.getContext(),Feedback.class));
            }
        });



    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_school,parent, false);
        return new MyViewHolder(v);
    }
}



