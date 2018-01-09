package com.sqliteexample;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by Server on 10/24/2017.
 */

public class Data_Adapter extends RecyclerView.Adapter<Data_Adapter.MyViewHolder> {


    AppCompatActivity appCompatActivity;

    Context mContext;
    Dialog tDialog;
    MyViewHolder mHolder;
    ArrayList arrayList;

    public Data_Adapter(AppCompatActivity appCompatActivity, ArrayList arrayList, Context mContext) {

        this.appCompatActivity = appCompatActivity;
  this.arrayList=arrayList;


    }

    @Override
    public Data_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_design_list, parent, false);
        Data_Adapter.MyViewHolder myViewHolder = new Data_Adapter.MyViewHolder(itemView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        this.mHolder = holder;
    holder.mItem_name.setText(arrayList.get(position).toString());
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mItem_name,mItem_name1;
        // ProgressBar progressBar;
        RelativeLayout mLayoutblock;

        public MyViewHolder(View itemView) {
            super(itemView);
            mItem_name=(TextView)itemView.findViewById(R.id.item_name);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {


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




