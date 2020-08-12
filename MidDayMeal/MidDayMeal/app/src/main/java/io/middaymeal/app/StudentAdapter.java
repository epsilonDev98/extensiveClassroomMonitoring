package io.middaymeal.app;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class StudentAdapter extends
        RecyclerView.Adapter<StudentAdapter.MyViewHolder> implements VolleyApi.ResponseListener{

    private ArrayList<StudentModel> dataItem;
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
        public TextView fatherName,name, mob,btB,btA;
        ImageView photo;
        CardView cv;




        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            fatherName = (TextView) view.findViewById(R.id.fatherName);
            btA = (Button) view.findViewById(R.id.btA);
            btB = (Button) view.findViewById(R.id.btB);
            photo = (ImageView) view.findViewById(R.id.photo);
            cv = (CardView) view.findViewById(R.id.cv);




        }



    }


    public StudentAdapter(Context mContext, ArrayList<StudentModel> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final StudentModel c = dataItem.get(position);
        holder.name.setText(c.getFirst_name() + c.getLast_name());
        holder.fatherName.setText(c.getFather_name());
        if (Utility.getPreferences(mContext,Constants.userType).equalsIgnoreCase("2")){
            if (c.getIsAttandance().equalsIgnoreCase("true")){
                holder.btA.setVisibility(View.GONE);
                //holder.btB.setVisibility(View.VISIBLE);
            }else {
                holder.btA.setVisibility(View.VISIBLE);
                //holder.btB.setVisibility(View.GONE);
            }
            holder.btA.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    VolleyApi.getInstance().innsetAttandance((Attendance)mContext,responseListener,dataItem.get(position).getId());
                }
            });
        }else {

            holder.btA.setVisibility(View.GONE);
            holder.cv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Utility.getPreferences(mContext,Constants.userType).equalsIgnoreCase("1")){
                        SingltonData.userId=dataItem.get(position).getId();
                        mContext.startActivity(new Intent(mContext,Feedback.class));
                    }
                }
            });
        }



    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_students,parent, false);

        return new MyViewHolder(v);
    }
}



