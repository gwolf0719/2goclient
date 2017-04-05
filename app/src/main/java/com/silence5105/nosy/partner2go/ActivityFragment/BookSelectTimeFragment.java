package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.silence5105.nosy.partner2go.PrefsHelper;
import com.silence5105.nosy.partner2go.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Nosy on 2017/3/14.
 */

public class BookSelectTimeFragment extends Fragment implements View.OnClickListener {
    TextView nextbtn, mytxt, dtimetxt;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mH, mm;
    DatePickerDialog dpd;
    TimePickerDialog timePicker;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookselecttimelayout, container, false);
        nextbtn = (TextView) view.findViewById(R.id.nextbtn);
        mytxt = (TextView) view.findViewById(R.id.mytxt);
        mytxt.setOnClickListener(this);
        dtimetxt = (TextView) view.findViewById(R.id.dtimetxt);
        nextbtn.setOnClickListener(this);
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dDateFormat = new SimpleDateFormat("HH:mm");
//        String date=sdf.format(new java.util.Date());
        mytxt.setText(sDateFormat.format(new java.util.Date()));
        dtimetxt.setText(dDateFormat.format(new java.util.Date()));
        dtimetxt.setOnClickListener(this);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

//        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
//                mytxt.setText(i + "/" + i1 + "/" + i2);
//            }
//        });
        mYear = gregorianCalendar.get(Calendar.YEAR);
        mMonth = gregorianCalendar.get(Calendar.MONTH);
        mDay = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        mH = gregorianCalendar.get(Calendar.HOUR);
        mm = gregorianCalendar.get(Calendar.MINUTE);
        dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mytxt.setText(year + "-" + (monthOfYear + 1) + "-"
                                + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        timePicker = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // 完成選擇，顯示時間
                        dtimetxt.setText(hourOfDay + ":" + minute);
//                        hh = String.valueOf(hourOfDay);
//                        hh = hourOfDay;
                    }
                }, mH, mm, false);
//        dpd.show();
        return view;
    }

    private void initview() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nextbtn:
                String hh = dtimetxt.getText().toString().substring(0, 2);
                PrefsHelper.gethtime(getActivity(), hh);
//                getActivity().
                String alltime = mytxt.getText().toString() +" "+dtimetxt.getText().toString();
//                getActivity().imvblockbg();

                PrefsHelper.getalltime(getActivity(),alltime);

                getActivity().getFragmentManager().beginTransaction().replace(R.id.showview, new BookingCarSelectFragment()).commit();

                break;
            case R.id.mytxt:
                dpd.show();
                break;
            case R.id.dtimetxt:
                timePicker.show();
                break;
        }
    }
}
