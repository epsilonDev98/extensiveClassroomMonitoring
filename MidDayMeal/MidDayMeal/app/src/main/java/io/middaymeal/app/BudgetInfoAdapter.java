package io.middaymeal.app;

import android.content.Context;
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


public class BudgetInfoAdapter extends
        RecyclerView.Adapter<BudgetInfoAdapter.MyViewHolder> implements VolleyApi.ResponseListener{

    private ArrayList<budget_info_model> dataItem;
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
        public TextView name,amount;
        ImageView photo;




        public MyViewHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);
            amount = (TextView) view.findViewById(R.id.amount);





        }



    }


    public BudgetInfoAdapter(Context mContext, ArrayList<budget_info_model> dataItem) {
        this.mContext = mContext;
        inflater= LayoutInflater.from(mContext);
        this.dataItem = dataItem;


    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        final budget_info_model c = dataItem.get(position);
        holder.name.setText(c.getName());
        holder.amount.setText(c.getAmount());


    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_budget_info,parent, false);

        return new MyViewHolder(v);
    }
}



