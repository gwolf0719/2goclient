package com.nnosy.taxigoclient;

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
    Onitemclick onitemclick;

    public interface Onitemclick {
        void onitemclick(View view, int position);
    }

    public void setonitemclick(Onitemclick onitemclick) {
        this.onitemclick = onitemclick;
    }

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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        myBookingItems = arrayList.get(position);
        holder.startadresstxt.setText(myBookingItems.start_address);
        holder.endadresstxt.setText(myBookingItems.end_address);
        holder.bookingtime.setText(myBookingItems.update_datetime);
        holder.ranktxt.setText(myBookingItems.partner_ranks);
        holder.bookingpaymenttxt.setText(myBookingItems.payment);
        holder.subjecttxt.setText(myBookingItems.official_subject);
        holder.infotxt.setText(myBookingItems.official_info);
        holder.bookingorderstatus.setText(myBookingItems.order_status);
        holder.bookingtexinumber.setText(myBookingItems.texi_number);
        holder.orderidtxt.setText(myBookingItems.order_id);
//        holder.
        holder.drivernumbertxt.setText(myBookingItems.partner_id);
        holder.cartxt.setText(myBookingItems.car);
        holder.drivernametxt.setText(myBookingItems.partner_name);
        holder.costtxt.setText(myBookingItems.cost);
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
        if (myBookingItems.order_status.equals("5")) {
            holder.detailcolor.setBackgroundColor(Color.parseColor("#f6a623"));
            holder.detailtxt.setText("Pending");
        } else {
            holder.detailcolor.setBackgroundColor(Color.parseColor("#62b606"));
            holder.detailtxt.setText("Detail");
        }
        holder.viewlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PrefsHelper.getlistselect(context, position);
//                Intent intent = new Intent();
//                intent.setClass(context, BookingDetailActivity.class);
//                context.startActivity(intent);
                PrefsHelper.getbookingdrivername(context, holder.drivernametxt.getText().toString());
                PrefsHelper.getbookingcar(context, holder.cartxt.getText().toString());
                PrefsHelper.getbookingcost(context, holder.costtxt.getText().toString());
                PrefsHelper.getbookingdrivernumber(context, holder.drivernumbertxt.getText().toString());
                PrefsHelper.getbookingrank(context, holder.ranktxt.getText().toString());
                PrefsHelper.getbookingstartadress(context, holder.startadresstxt.getText().toString());
                PrefsHelper.getbookingendadress(context, holder.endadresstxt.getText().toString());
                PrefsHelper.getbookingsubject(context, holder.subjecttxt.getText().toString());
                PrefsHelper.getbookinginfo(context, holder.infotxt.getText().toString());
                //stauts
                PrefsHelper.getbookingorderstauts(context, holder.bookingorderstatus.getText().toString());
                //
                PrefsHelper.getbookingupdatetime(context, holder.bookingtime.getText().toString());
                PrefsHelper.getbookingpayment(context, holder.bookingpaymenttxt.getText().toString());
                PrefsHelper.getbookingtexinumber(context, holder.bookingtexinumber.getText().toString());
                PrefsHelper.getbookingorderid(context, holder.orderidtxt.getText().toString());
                onitemclick.onitemclick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size()
                ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderidtxt;
        TextView startadresstxt;
        TextView endadresstxt;
        ImageView redimg, blueimg;
        TextView bookingtime, bookingtype, cartxt, subjecttxt, costtxt, infotxt, drivernametxt, ranktxt, drivernumbertxt;
        TextView detailtxt;
        RelativeLayout bglayout, detailcolor, viewlayout;
        TextView bookingtexinumber;
        TextView bookingpaymenttxt;
        TextView bookingorderstatus;


        public ViewHolder(View itemView) {
            super(itemView);
            orderidtxt = (TextView) itemView.findViewById(R.id.orderidtxt);
            bookingorderstatus = (TextView) itemView.findViewById(R.id.bookingorferstautstxt);
            bookingpaymenttxt = (TextView) itemView.findViewById(R.id.bookingpaymenttxt);
            bookingtexinumber = (TextView) itemView.findViewById(R.id.bookingtexinumber);
            bglayout = (RelativeLayout) itemView.findViewById(R.id.bglayout);
            startadresstxt = (TextView) itemView.findViewById(R.id.startadresstxt);
            endadresstxt = (TextView) itemView.findViewById(R.id.endadresstxt);
            redimg = (ImageView) itemView.findViewById(R.id.redimg);
            blueimg = (ImageView) itemView.findViewById(R.id.blueimg);
            bookingtime = (TextView) itemView.findViewById(R.id.createtimetxt);
            bookingtype = (TextView) itemView.findViewById(R.id.bookingtypetxt);
            detailcolor = (RelativeLayout) itemView.findViewById(R.id.detailcolor);
            detailtxt = (TextView) itemView.findViewById(R.id.detailtxt);
            viewlayout = (RelativeLayout) itemView.findViewById(R.id.viewlayout);
            cartxt = (TextView) itemView.findViewById(R.id.bookingcartxt);
            subjecttxt = (TextView) itemView.findViewById(R.id.bookingsubjecttxt);
            costtxt = (TextView) itemView.findViewById(R.id.bookingcost);
            infotxt = (TextView) itemView.findViewById(R.id.bookinginfo);
            drivernametxt = (TextView) itemView.findViewById(R.id.bookingdirvernametxt);
            ranktxt = (TextView) itemView.findViewById(R.id.bookingranktxt);
            drivernumbertxt = (TextView) itemView.findViewById(R.id.bookingdrivernumber);


        }
    }
}
