package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/3/14.
 */

public class BookingSelectFragment extends Fragment implements View.OnClickListener {
    Button bookbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookingselectlayout, container, false);
        bookbtn = (Button) view.findViewById(R.id.pbookbtn);
        bookbtn.setOnClickListener(this);
        return view;
    }

    private void initview() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pbookbtn:
                getActivity().getFragmentManager().beginTransaction().replace(R.id.container, new BookSelectTimeFragment()).commit();
                break;
        }
    }
}
