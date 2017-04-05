package com.silence5105.nosy.partner2go;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Nosy on 2017/3/23.
 */

public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.ViewHolder> {
    View view;
    ArrayList<MyBookingItems> arrayList = new ArrayList<MyBookingItems>();
    Context context;
    MyBookingItems myBookingItems;


    public MyBookingAdapter(Context context, ArrayList<MyBookingItems> myBookingItemses) {
        this.context = context;
        this.arrayList = arrayList;
        this.arrayList = myBookingItemses;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.mybookingsrclayout, parent, false);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        myBookingItems = arrayList.get(position);
        holder.startadresstxt.setText(myBookingItems.start_address);
        holder.endadresstxt.setText(myBookingItems.end_address);
        holder.bookingtime.setText(myBookingItems.create_datetime);
//        holder.bookingtype.setText(myBookingItems.);
        if (myBookingItems.payment.equals("official")) {
            holder.bookingtype.setText("Official Booking");
            holder.redimg.setVisibility(View.GONE);
            holder.blueimg.setVisibility(View.VISIBLE);
            holder.bglayout.setBackgroundColor(Color.parseColor("#0255a6"));

        } else {
            holder.bookingtype.setText("Personal Booking");
            holder.redimg.setVisibility(View.VISIBLE);
            holder.blueimg.setVisibility(View.GONE);
            holder.bglayout.setBackgroundColor(Color.parseColor("#c63725"));
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size()
                ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView startadresstxt;
        TextView endadresstxt;
        ImageView redimg, blueimg;
        TextView bookingtime, bookingtype;
        RelativeLayout bglayout;

        public ViewHolder(View itemView) {
            super(itemView);
            bglayout = (RelativeLayout) itemView.findViewById(R.id.bglayout);
            startadresstxt = (TextView) itemView.findViewById(R.id.startadresstxt);
            endadresstxt = (TextView) itemView.findViewById(R.id.endadresstxt);
            redimg = (ImageView) itemView.findViewById(R.id.redimg);
            blueimg = (ImageView) itemView.findViewById(R.id.blueimg);
            bookingtime = (TextView) itemView.findViewById(R.id.createtimetxt);
            bookingtype = (TextView) itemView.findViewById(R.id.bookingtypetxt);
        }
    }
}
