package com.android.demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.android.demo.R;

/**
 * Created by Sukrut on 9/9/2017.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataObjectHolder>{


    ArrayList<DataObject> dataObjects;
    Context context;

    public DataAdapter(ArrayList<DataObject> dataObjects, Context context)


    {
        this.dataObjects=dataObjects;
        this.context=context;
    }

    public void removeItem(int position)
    {
        new DatabaseHandler(context).delete(dataObjects.get(position).getName());
        dataObjects.remove(position);
        notifyItemRemoved(position);

    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_layout,parent,false);
        DataObjectHolder dataObjectHolder=new DataObjectHolder(view,context,dataObjects);
        return  dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position)
    {

        holder.t1.setText("Name: "+dataObjects.get(position).getName());
        holder.t2.setText("Gender: "+dataObjects.get(position).getGender());
        holder.t3.setText("Age: "+dataObjects.get(position).getAge());
        holder.t4.setText("Occupation: "+dataObjects.get(position).getOccupation());
        holder.t5.setText("Description: "+dataObjects.get(position).getDescription());
        holder.t6.setText("Address: "+dataObjects.get(position).getAddress());

    }


    @Override
    public int getItemCount() {
        return dataObjects.size();
    }

    public class DataObjectHolder extends  RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        TextView t1,t2,t3,t4,t5,t6;
        Context context;
        ArrayList<DataObject> dataObjects;

        public DataObjectHolder(View itView,Context context,ArrayList<DataObject> dataObjects)
        {
            super(itView);
            t1= (TextView) itView.findViewById(R.id.text1);
            t2= (TextView) itView.findViewById(R.id.text2);

            t3= (TextView) itView.findViewById(R.id.text3);
            t4= (TextView) itView.findViewById(R.id.text4);
            t5= (TextView) itView.findViewById(R.id.text5);
            t6= (TextView) itView.findViewById(R.id.text6);

            this.context=context;
            this.dataObjects=dataObjects;
            itView.setOnClickListener(this);
            itView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View v) {

            int position= getAdapterPosition();
            Toast.makeText(this.context,this.dataObjects.get(position).getName(), Toast.LENGTH_SHORT).show();



        }


        @Override
        public boolean onLongClick(View v) {

            int position= getAdapterPosition();
            removeItem(position);

            return  true;
        }
    }
}
