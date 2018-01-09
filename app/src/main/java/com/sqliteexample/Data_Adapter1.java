package com.sqliteexample;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sqliteexample.Database.DBhelper;

import java.util.ArrayList;


/**
 * Created by Server on 10/24/2017.
 */

public class Data_Adapter1 extends RecyclerView.Adapter<Data_Adapter1.MyViewHolder> {


    AppCompatActivity appCompatActivity;
    DBhelper mydb;
    Context mContext;
    Dialog tDialog;
    MyViewHolder mHolder;
    ArrayList arrayList;
    ArrayList<ContactDataList> contactDataListArrayList;
    MainActivity mainActivity;


    public Data_Adapter1(AppCompatActivity appCompatActivity, ArrayList<ContactDataList> contactDataListArrayList, MainActivity mainActivity, Context mContext) {

        this.appCompatActivity = appCompatActivity;
        this.contactDataListArrayList = contactDataListArrayList;
        this.mainActivity = mainActivity;
        this.mContext=mContext;

    }

    @Override
    public Data_Adapter1.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_design_list, parent, false);
        Data_Adapter1.MyViewHolder myViewHolder = new Data_Adapter1.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        this.mHolder = holder;
        holder.mItem_name.setText(contactDataListArrayList.get(position).getName());
        holder.mItem_name1.setText(contactDataListArrayList.get(position).getEmail());

        holder.mDelete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mydb.deleteContacts(position);

                contactDataListArrayList.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
            }
        });


    }


    @Override
    public int getItemCount() {
        return contactDataListArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mItem_name, mItem_name1;
        // ProgressBar progressBar;
        RelativeLayout mLayoutblock;
        LinearLayout mLinear1;
        Button mDelete_item;

        public MyViewHolder(View itemView) {
            super(itemView);
            mydb = new DBhelper(mainActivity);

            mItem_name = (TextView) itemView.findViewById(R.id.item_name);
            mItem_name1 = (TextView) itemView.findViewById(R.id.item_name1);
            mDelete_item = (Button) itemView.findViewById(R.id.delete_item);
            mLinear1=(LinearLayout)itemView.findViewById(R.id.linear1);
            mLinear1.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.linear1:
                    int id_To_Search = getPosition()+1;

                    Bundle dataBundle = new Bundle();
                    dataBundle.putInt("id", id_To_Search);

                    Intent intent = new Intent(mContext,Contacts.class);

                    intent.putExtras(dataBundle);
                    mContext.startActivity(intent);

                     break;

            }
        }


/*    public void blockDialog(final String text) {

        tDialog = new Dialog(appCompatActivity);  //---------filter dialog------//////////
        tDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        tDialog.setContentView(R.layout.dialogbox);

        LinearLayout mLayoutwhiteblock = (LinearLayout) tDialog.findViewById(R.id.layoutwhiteblock);
        mLayoutwhiteblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
          *//*      Fragment fragment =new SupportListDesign();
                Bundle bundle =new Bundle();
                bundle.putString("total",text);*//*
                helper.moveFragment(new SupportListDesign(), Constant.TAB_GLOBAL, R.id.container, appCompatActivity);
                tDialog.dismiss();
            }
        });

        Window window = tDialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        WindowManager.LayoutParams wmlp = tDialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER | Gravity.RIGHT;
        wmlp.x = -50;   //x position
        //   wmlp.y = 100;
        tDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        tDialog.show();


    }*/

    }
}




