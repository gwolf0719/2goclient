package com.nnosy.taxigoclient;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Nosy on 2017/3/5.
 */

public class PastAdapter extends RecyclerView.Adapter<PastAdapter.ViewHolder> {
    Context context;
    ArrayList<PastItem> arraylist;

    public PastAdapter(Context context, ArrayList<PastItem> arrayList) {
        this.context = context;
        this.arraylist = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
