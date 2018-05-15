package com.nnosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nnosy.partner2go.R;

/**
 * Created by Nosy on 2017/3/17.
 */

public class TimeselectFragment extends Fragment implements OnClickListener {
    TextView ytxt, dtxt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.timeselectfragment, container, false);
        ytxt = (TextView) view.findViewById(R.id.ytxt);
        ytxt.setOnClickListener(this);
        dtxt = (TextView) view.findViewById(R.id.dtxt);
        dtxt.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ytxt:
                break;
            case R.id.dtxt:
                break;
        }
    }
}
