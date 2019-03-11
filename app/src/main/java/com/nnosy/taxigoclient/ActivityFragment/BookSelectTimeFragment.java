package com.nnosy.taxigoclient.ActivityFragment;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nnosy.taxigoclient.MainActivity;
import com.nnosy.taxigoclient.PrefsHelper;
import com.nnosy.taxigoclient.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Nosy on 2017/3/14.
 */

public  class BookSelectTimeFragment extends Fragment implements View.OnClickListener {
    TextView mytxt, dtimetxt, officletxt, oktxt, canceltxt;
    DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay, mH, mm;
    DatePickerDialog dpd;
    TimePickerDialog timePicker;
    RelativeLayout cancelbtn, nextbtn;
    Date nowdate, chosdate, ynowdate, ychosdate;

    int AM_PM;
    private RelativeLayout bnext;
    private RelativeLayout bcancelbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bookselecttimelayout, container, false);
        nextbtn = (RelativeLayout) view.findViewById(R.id.nextbtn);
        mytxt = (TextView) view.findViewById(R.id.mytxt);
//            oktxt = (TextView) view.findViewById(R.id.oktxt);
//            canceltxt = (TextView) view.findViewById(R.id.canceltxt);
        bcancelbtn = (RelativeLayout) view.findViewById(R.id.bcanclebtn);
        bcancelbtn.setOnClickListener(this);
        mytxt.setOnClickListener(this);
        bnext = (RelativeLayout) view.findViewById(R.id.bnextbtn);
        bnext.setOnClickListener(this);
        bnext.setVisibility(View.INVISIBLE);
        dtimetxt = (TextView) view.findViewById(R.id.dtimetxt);
        nextbtn.setOnClickListener(this);
        cancelbtn = (RelativeLayout) view.findViewById(R.id.canclebtn);
        cancelbtn.setOnClickListener(this);
        PrefsHelper.getlistselect(getActivity(), 0);
        officletxt = (TextView) view.findViewById(R.id.officletxt);
        final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final SimpleDateFormat dDateFormat = new SimpleDateFormat("HH:mm");
//        String date=sdf.format(new java.util.Date());
//            mytxt.setText(sDateFormat.format(new java.util.Date()));
//            dtimetxt.setText(dDateFormat.format(new java.util.Date()));
        mytxt.setText(Html.fromHtml("<u>" + sDateFormat.format(new java.util.Date()) + "</u>"));
        dtimetxt.setText(Html.fromHtml("<u>" + dDateFormat.format(new java.util.Date()) + "</u>"));
//            System.out.println("time ===== : " + testformat.format(new Date()));
        try {
            nowdate = dDateFormat.parse(dDateFormat.format(new Date()));
            ynowdate = sDateFormat.parse(sDateFormat.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dtimetxt.setOnClickListener(this);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        mYear = gregorianCalendar.get(Calendar.YEAR);
        mMonth = gregorianCalendar.get(Calendar.MONTH);
        mDay = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
        mH = gregorianCalendar.get(Calendar.HOUR_OF_DAY);
        mm = gregorianCalendar.get(Calendar.MINUTE);
        AM_PM = gregorianCalendar.get(Calendar.AM_PM);
        System.out.println("TIME ====== : AM_PM  " + AM_PM);
        dpd = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        mytxt.setText(year + "-" + (monthOfYear + 1) + "-"
                                + dayOfMonth);
                        try {
                            ychosdate = sDateFormat.parse(mytxt.getText().toString());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (PrefsHelper.setgoreservatione(getActivity()) != null || PrefsHelper.setgoreservatione(getActivity()) == null) {
                            Long ynowt = ynowdate.getTime();
                            Long ychost = ychosdate.getTime();
                            Long yanser = ychost - ynowt;
                            Long ydt = yanser / 86400000;
                            System.out.println("TIME Y ====== + " + yanser + " " + ynowt + " " + ychost);
                            if (ydt >= 1) {
                                PrefsHelper.getgoreservatione(getActivity(), "1");
                            }
                        }
                        if (PrefsHelper.setofficalbooking(getActivity()) != null) {
                            if (PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
                                Long ynowt = ynowdate.getTime();
                                Long ychost = ychosdate.getTime();
                                Long yanser = ychost - ynowt;
                                Long ydt = yanser / 86400000;
                                System.out.println("TIME Y ====== + " + yanser + " " + ynowt + " " + ychost);
                                if (ydt >= 1) {
//                                        oktxt.setVisibility(View.VISIBLE);
                                    bnext.setVisibility(View.VISIBLE);
                                }


                            }
                        }

                    }
                }, mYear, mMonth, mDay);

        timePicker = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {


//                            if (hourOfDay < 12) {
//                                AM_PM = false;
//                            }if(hourOfDay>12){
//                                AM_PM = true;
//                            }


                        dtimetxt.setText(hourOfDay + ":" + minute);
                        try {
                            chosdate = dDateFormat.parse(dtimetxt.getText().toString());
                            System.out.println("time ======= : " + nowdate + " " + chosdate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if (PrefsHelper.setofficalbooking(getActivity()) != null || PrefsHelper.setofficalbooking(getActivity()) == null) {
                            if (PrefsHelper.setofficalbooking(getActivity()).equals("0")) {
                                Long nowt = nowdate.getTime();
                                Long chost = chosdate.getTime();
                                Long ansert = chost - nowt;
                                Long min = ansert / 60000;
                                if (min >= 30) {
                                    PrefsHelper.getgoreservatione(getActivity(), "1");
                                }
                                if (min < 30) {
                                    PrefsHelper.getgoreservatione(getActivity(), "0");
                                }
                            }
                        }
                        if (PrefsHelper.setofficalbooking(getActivity()) != null) {
                            if (PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
                                Long nowt = nowdate.getTime();
                                Long chost = chosdate.getTime();
                                Long ansert = chost - nowt;
                                Long min = ansert / 60000;
                                if (min >= 45) {
//                                        oktxt.setVisibility(View.VISIBLE);
                                    bnext.setVisibility(View.VISIBLE);
                                }
                                if (min < 45) {
                                    Toast.makeText(getActivity(), "Need to advance 45 minutes ahead of time", Toast.LENGTH_SHORT).show();
                                }
                                System.out.println("Time 2 ======== : " + min + " " + ansert);
                            }
                        }


//                        hh = String.valueOf(hourOfDay);
//                        hh = hourOfDay;
                    }
                }, mH, mm, true);
//        dpd.show();
        if (PrefsHelper.setofficalbooking(getActivity()).equals("0")) {
//                oktxt.setVisibility(View.INVISIBLE);
            officletxt.setVisibility(View.INVISIBLE);
//                oktxt.setText(R.string.officialbookingok);
            nextbtn.setVisibility(View.VISIBLE);
            bnext.setVisibility(View.INVISIBLE);
//                canceltxt.setText(R.string.officialbookingcancel);
            cancelbtn.setVisibility(View.VISIBLE);
            bcancelbtn.setVisibility(View.INVISIBLE);
        }

        if (PrefsHelper.setofficalbooking(getActivity()).equals("1")) {
//                oktxt.setVisibility(View.INVISIBLE);
            officletxt.setVisibility(View.VISIBLE);
//                oktxt.setText(R.string.officialbookingok);
//                bnext.setVisibility(View.VISIBLE);
            nextbtn.setVisibility(View.INVISIBLE);
            cancelbtn.setVisibility(View.INVISIBLE);
            bcancelbtn.setVisibility(View.VISIBLE);
//                canceltxt.setText(R.string.officialbookingcancel);
        }
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bnextbtn:
                String hh = dtimetxt.getText().toString().substring(0, 2);
                PrefsHelper.gethtime(getActivity(), hh);
//                getActivity().
                String alltime = mytxt.getText().toString() + " " + dtimetxt.getText().toString();
//                getActivity().imvblockbg();
//                    imvblockbg();
                System.out.println("alltime ====== : " + alltime);

                ((MainActivity)getActivity()).test();
//                    BookSelectTimeFragment1 bookSelectTimeFragment1 = new BookSelectTimeFragment1();
                PrefsHelper.getalltime(getActivity(), alltime);
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction.remove(this);
//                BookingCarSelectFragment
                BookingCarSelectFragment bookingCarSelectFragment = new BookingCarSelectFragment();
//                BookingCarSelectFragment bookingCarSelectFragment = new BookingCarSelectFragment();
                fragmentTransaction.replace(R.id.showview,bookingCarSelectFragment);
                fragmentTransaction.commit();
                break;
            case R.id.bcanclebtn:
//                cleanmap();
                break;
            case R.id.canclebtn:
//                cleanmap();
                break;
            case R.id.nextbtn:
                String hhh = dtimetxt.getText().toString().substring(0, 2);
                PrefsHelper.gethtime(getActivity(), hhh);
//                getActivity().
                String alltime1 = mytxt.getText().toString() + " " + dtimetxt.getText().toString();
//                getActivity().imvblockbg();
//                    imvblockbg();
                System.out.println("alltime ====== : " + alltime1);


                ((MainActivity)getActivity()).test();


//                    BookSelectTimeFragment1 bookSelectTimeFragment1 = new BookSelectTimeFragment1();
                PrefsHelper.getalltime(getActivity(), alltime1);
                FragmentTransaction fragmentTransaction1 = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction1.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction1.remove(this);
                BookingCarSelectFragment bookingCarSelectFragment1 = new BookingCarSelectFragment();
                fragmentTransaction1.replace(R.id.showview, bookingCarSelectFragment1);
                fragmentTransaction1.commit();
//                    getActivity().getFragmentManager().beginTransaction().replace(R.id.showview, new BookingCarSelectFragment1()).commit();

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
