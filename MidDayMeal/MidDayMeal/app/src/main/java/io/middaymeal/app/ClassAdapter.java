package io.middaymeal.app;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ClassAdapter extends
        RecyclerView.Adapter<ClassAdapter.MyViewHolder> implements VolleyApi.ResponseListener{

    private ArrayList<ModelClass> dataItem;
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
        public TextView tvText;
        ImageView photo;
        CardView cv;




        public MyViewHolder(View view) {
            super(view);

            tvText = (TextView) view.findViewById(R.id.tvText);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SingltonData.classId=dataItem.get(getLayoutPosition()).getId();
                    view.getContext().startActivity(new Intent(view.getContext(),Attendance.class));
                }
            });



        }



    }


    public ClassAdapter(Context mContext, ArrayList<ModelClass> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final ModelClass c = dataItem.get(position);
        holder.tvText.setText(c.getName());




    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_class,parent, false);

        return new MyViewHolder(v);
    }
}



