package com.silence5105.nosy.partner2go;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.silence5105.nosy.partner2go.ActivityFragment.TobeVerifiedActivity;

import java.util.ArrayList;

/**
 * Created by Nosy on 2017/3/31.
 */

public class VerificationAdapter extends RecyclerView.Adapter<VerificationAdapter.ViewHolder> {
    View view;
    ArrayList<VerificationItems> arrayList = new ArrayList<VerificationItems>();
    Context context;
    VerificationItems verificationItems;

    public VerificationAdapter(Context context, ArrayList<VerificationItems> verificationItemses) {
        this.arrayList = verificationItemses;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.verificationlistlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        verificationItems = arrayList.get(position);
        holder.vnametxt.setText(verificationItems.official_id);
        holder.vtimetxt.setText(verificationItems.update_datetime);
        holder.vjobtxt.setText(verificationItems.official_info);
        holder.listview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(" click ===== " + position);
                PrefsHelper.getlistselect(context, position);
                Intent intent = new Intent();
                intent.setClass(context, TobeVerifiedActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout listview;
        TextView vnametxt, vjobtxt, vtimetxt;

        public ViewHolder(View itemView) {
            super(itemView);
            listview = (RelativeLayout) itemView.findViewById(R.id.listview);
            vnametxt = (TextView) itemView.findViewById(R.id.vnametxt);
            vjobtxt = (TextView) itemView.findViewById(R.id.vjobtxt);
            vtimetxt = (TextView) itemView.findViewById(R.id.vtimetxt);
        }
    }
}
