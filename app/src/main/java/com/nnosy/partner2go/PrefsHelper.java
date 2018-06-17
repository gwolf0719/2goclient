package com.nnosy.partner2go;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Nosy on 2017/1/24.
 */

public class PrefsHelper {
    public static void getavapic(Context context, String avapic) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("avapic", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.AVAPIC.toString(), avapic);
        editor.commit();
    }

    public static String setavapic(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("avapic", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.AVAPIC.toString(), null);
    }

    public static void getmcashdone(Context context, String mcashdone) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingselect", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.MCASHDON.toString(), mcashdone);
        editor.commit();
    }

    public static String setmcashdone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingselect", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.MCASHDON.toString(), null);
    }

    public static void getcashtype(Context context, String cashtype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingselect", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.CASH.toString(), cashtype);
        editor.commit();
    }

    public static String setcashtype(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingselect", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.CASH.toString(), null);
    }

    public static void getbookingselect(Context context, String bookingselect) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingselect", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGSELECT.toString(), bookingselect);
        editor.commit();
    }

    public static String setbookingselect(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingselect", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGSELECT.toString(), null);
    }

    public static void getbookingorderid(Context context, String bookingorderid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingorderid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGORDERID.toString(), bookingorderid);
        editor.commit();
    }

    public static String setbookingorderid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingorderid", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGORDERID.toString(), null);
    }

    public static void getbookingtexinumber(Context context, String bookingtexinumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingtexinumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGTEXINUMBER.toString(), bookingtexinumber);
        editor.commit();
    }

    public static String setbookingtexinumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingtexinumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGTEXINUMBER.toString(), null);
    }

    public static void getbookingpayment(Context context, String bookingpayment) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingpayment", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGPAYMENT.toString(), bookingpayment);
        editor.commit();
    }

    public static String setbookingpayment(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingpayment", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGPAYMENT.toString(), null);
    }

    public static void getbookingdrivernumber(Context context, String bookingdrivernumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingdrivernumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGDRIVERNUMBER.toString(), bookingdrivernumber);
        editor.commit();
    }

    public static String setbookingdrivernumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingdrivernumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGDRIVERNUMBER.toString(), null);
    }

    public static void getbookingdrivername(Context context, String bookingdrivername) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingdrivername", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGDRIVERNAME.toString(), bookingdrivername);
        editor.commit();
    }

    public static String setbookingdrivername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingdrivername", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGDRIVERNAME.toString(), null);
    }

    public static void getbookingorderstauts(Context context, String bookingorderstauts) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingorderstauts", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGORDERSTAUTS.toString(), bookingorderstauts);
        editor.commit();
    }

    public static String setbookingorderstauts(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingorderstauts", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGORDERSTAUTS.toString(), null);
    }

    public static void getbookingrank(Context context, String bookingrank) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingrank", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGRANK.toString(), bookingrank);
        editor.commit();
    }

    public static String setbookingrank(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingrank", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGRANK.toString(), null);
    }

    public static void getbookinginfo(Context context, String bookinginfo) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookinginfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGINFO.toString(), bookinginfo);
        editor.commit();
    }

    public static String setbookinginfo(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookinginfo", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGINFO.toString(), null);
    }

    public static void getbookingsubject(Context context, String bookingsubject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingsubject", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGSUBJECT.toString(), bookingsubject);
        editor.commit();
    }

    public static String setbookingsubject(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingsubject", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGSUBJECT.toString(), null);
    }

    public static void getbookingcar(Context context, String bookingcar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingcar", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGCAR.toString(), bookingcar);
        editor.commit();
    }

    public static String setbookingcar(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingcar", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGCAR.toString(), null);
    }

    public static void getbookingupdatetime(Context context, String bookingupdatetime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingupdatetime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGUPDATATIME.toString(), bookingupdatetime);
        editor.commit();
    }

    public static String setbookingupdatetime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingupdatetime", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGUPDATATIME.toString(), null);
    }

    public static void getbookingcost(Context context, String bookingcost) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingcost", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGCOST.toString(), bookingcost);
        editor.commit();
    }

    public static String setbookingcost(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingcost", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGCOST.toString(), null);
    }

    public static void getbookingendadress(Context context, String bookingendadress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingendadress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGENDADRESS.toString(), bookingendadress);
        editor.commit();
    }

    public static String setbookingendadress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingendadress", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGENDADRESS.toString(), null);
    }

    public static void getbookingstartadress(Context context, String bookingstartadress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingstartadress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGSTARTADRESS.toString(), bookingstartadress);
        editor.commit();
    }

    public static String setbookingstartadress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingstartadress", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGSTARTADRESS.toString(), null);
    }

    public static void getbookingtype(Context context, String bookingtype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingtype", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.BOOKINGTYPE.toString(), bookingtype);
        editor.commit();
    }

    public static String setbookingtype(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("bookingtype", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.BOOKINGTYPE.toString(), null);
    }

    public static void getgoreservatione(Context context, String goreservatione) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("goreservatione", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.RESERVATION.toString(), goreservatione);
        editor.commit();
    }

    public static String setgoreservatione(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("goreservatione", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.RESERVATION.toString(), null);
    }

    public static void getusername(Context context, String username) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("username", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.USERNAME.toString(), username);
        editor.commit();
    }

    public static String setusername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("username", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.USERNAME.toString(), null);
    }

    public static void getordertype(Context context, String ordertype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ordertype", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ORDERTYPE.toString(), ordertype);
        editor.commit();
    }

    public static String setordertype(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("ordertype", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ORDERTYPE.toString(), null);
    }

    public static void getlast5m(Context context, String last5m) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("last5m", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.LAST5M.toString(), last5m);
        editor.commit();
    }

    public static String setlast5m(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("last5m", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.LAST5M.toString(), null);
    }

    public static void getlast30m(Context context, String last30m) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("last30m", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.LAST30M.toString(), last30m);
        editor.commit();
    }

    public static String setlast30m(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("last30m", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.LAST30M.toString(), null);
    }

    public static void getlistselect(Context context, int listselect) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("listselect", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PreferenceKeys.LISTSELECT.toString(), listselect);
        editor.commit();
    }

    public static int setlistselect(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("listselect", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(PreferenceKeys.LISTSELECT.toString(), 0);
    }

    public static void getdriverotw(Context context, String driverotw) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverotw", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVEROTW.toString(), driverotw);
        editor.commit();
    }

    public static String setdriverotw(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverotw", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVEROTW.toString(), null);
    }

    public static void getendlat(Context context, String endlat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endlat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ENDLAT.toString(), endlat);
        editor.commit();
    }

    public static String setendlat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endlat", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ENDLAT.toString(), null);
    }

    public static void getendlng(Context context, String endlng) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endlng", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ENDLNG.toString(), endlng);
        editor.commit();
    }

    public static String setendlng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endlng", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ENDLNG.toString(), null);
    }

    public static void getstartlng(Context context, String startlng) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startlng", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.STARTLNG.toString(), startlng);
        editor.commit();
    }

    public static String setstartlng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startlng", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.STARTLNG.toString(), null);
    }

    public static void getstartlat(Context context, String startlat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startlat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.STARTLAT.toString(), startlat);
        editor.commit();
    }

    public static String setstartlat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startlat", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.STARTLAT.toString(), null);
    }

    public static void getdriverlng(Context context, String driverlng) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverlng", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVERLNG.toString(), driverlng);
        editor.commit();
    }

    public static String setdriverlng(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverlng", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVERLNG.toString(), null);
    }

    public static void getdriverlat(Context context, String driverlat) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverlat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVERLAT.toString(), driverlat);
        editor.commit();
    }

    public static String setdriverlat(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverlat", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVERLAT.toString(), null);
    }

    public static void getdrivercarclass(Context context, String drivercarclass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("drivercarclass", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVERCARCLASS.toString(), drivercarclass);
        editor.commit();
    }

    public static String setdrivercarclass(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("drivercarclass", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVERCARCLASS.toString(), null);
    }

    public static void gettextnumber(Context context, String textnumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("textnumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.TEXTNUMBER.toString(), textnumber);
        editor.commit();
    }

    public static String settextnumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("textnumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.TEXTNUMBER.toString(), null);
    }

    public static void getdriverphonenumber(Context context, String driverphonenumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverphonenumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVERPHONENUMBER.toString(), driverphonenumber);
        editor.commit();
    }

    public static String setdriverphonenumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("driverphonenumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVERPHONENUMBER.toString(), null);
    }

    public static void getgotime(Context context, String gotime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("gotime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.GOTIME.toString(), gotime);
        editor.commit();
    }

    public static String setgotime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("gotime", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.GOTIME.toString(), null);
    }

    public static void getrate(Context context, String rate) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("rate", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVERRATE.toString(), rate);
        editor.commit();
    }

    public static String setrate(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("rate", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVERRATE.toString(), null);
    }

    public static void getcost(Context context, String cost) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cost", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.COST.toString(), cost);
        editor.commit();
    }

    public static String setcost(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("cost", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.COST.toString(), null);
    }

    public static void getdrivername(Context context, String drivername) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("drivername", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.DRIVERNAME.toString(), drivername);
        editor.commit();
    }

    public static String setdrivername(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("drivername", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.DRIVERNAME.toString(), null);
    }

    public static void getofficalbooking(Context context, String officalbooking) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("officalbooking", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.OFFICIALBOOKING.toString(), officalbooking);
        editor.commit();
    }

    public static String setofficalbooking(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("officalbooking", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.OFFICIALBOOKING.toString(), null);
    }

    public static void getofficaltype(Context context, String officaltype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("officaltype", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.OFFICIALTYPE.toString(), officaltype);
        editor.commit();
    }

    public static String setofficaltype(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("officaltype", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.OFFICIALTYPE.toString(), null);
    }

    public static void getclientorderid(Context context, String clientorderid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("clientorderid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.CLIENTORDERID.toString(), clientorderid);
        editor.commit();
    }

    public static String setclientorderid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("clientorderid", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.CLIENTORDERID.toString(), null);
    }

    public static void getalltime(Context context, String alltime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("alltime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ALLTIME.toString(), alltime);
        editor.commit();
    }

    public static String setalltime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("alltime", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ALLTIME.toString(), null);
    }

    public static void gethtime(Context context, String htime) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("htime", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.HTIME.toString(), htime);
        editor.commit();
    }

    public static String sethtim(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("htime", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.HTIME.toString(), null);
    }

    public static void getphonenumber(Context context, String paymenttype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("phonenumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.PHONENUMBER.toString(), paymenttype);
        editor.commit();
    }

    public static String setphonenumber(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("phonenumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.PHONENUMBER.toString(), null);
    }

    public static void getpaymenttype(Context context, String paymenttype) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("paymenttype", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.PAYMENTTYPE.toString(), paymenttype);
        editor.commit();
    }

    public static String setpaymenttype(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("paymenttype", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.PAYMENTTYPE.toString(), null);
    }

    public static void getmenucost(Context context, String menucost) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("menucost", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.MENUCOST.toString(), menucost);
        editor.commit();
    }

    public static String setmenucost(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("menucost", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.MENUCOST.toString(), null);
    }

    public static void getorderid(Context context, String orderid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("orderid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ORDERID.toString(), orderid);
        editor.commit();
    }

    public static String setorderid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("orderid", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ORDERID.toString(), null);
    }

    public static void gettimes(Context context, String times) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("times", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.CLIENTTIME.toString(), times);
        editor.commit();
    }

    public static String settimes(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("times", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.CLIENTTIME.toString(), null);
    }

    public static void gethaved(Context context, String haved) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("haved", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.HAVED.toString(), haved);
        editor.commit();
    }

    public static String sethaved(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("haved", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.HAVED.toString(), null);
    }

    public static void getregiid(Context context, String regiid) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("regiid", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.REGIID.toString(), regiid);
        editor.commit();
    }

    public static String setregiid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("regiid", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.REGIID.toString(), null);
    }

    public static void getpushkey(Context context, String pushkey) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("pushkey", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.PUSHKEY.toString(), pushkey);
        editor.commit();
    }

    public static String setpushkey(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("pushkey", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.PUSHKEY.toString(), null);
    }

    public static void getsteps(Context context, String steps) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("steps", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.STEPS.toString(), steps);
        editor.commit();
    }

    public static String setsteps(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("steps", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.STEPS.toString(), null);
    }

    public static void getendlocation(Context context, String endlocation) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endlocation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ENDLOCATION.toString(), endlocation);
        editor.commit();
    }

    public static String setendlocation(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endlocation", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ENDLOCATION.toString(), null);
    }

    public static void getstartlocation(Context context, String startlocation) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startlocation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.STARTLOCATION.toString(), startlocation);
        editor.commit();
    }

    public static String setstartlocation(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startlocation", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.STARTLOCATION.toString(), null);
    }

    public static void getendaddress(Context context, String endaddress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endaddress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.ENDADDRESS.toString(), endaddress);
        editor.commit();
    }

    public static String setendaddress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("endaddress", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.ENDADDRESS.toString(), null);
    }

    public static void getstartadress(Context context, String startadress) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startaddress", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.STARTADDRESS.toString(), startadress);
        editor.commit();
    }

    public static String setstartadress(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("startaddress", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.STARTADDRESS.toString(), null);
    }

    public static void getcarclass(Context context, String carclass) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carclass", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.CARCLASS.toString(), carclass);
        editor.commit();
    }

    public static String setcarclass(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("carclass", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.CARCLASS.toString(), null);
    }

    public static void getkm(Context context, String km) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("kmnumber", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.KMKEY.toString(), km);
        editor.commit();
    }

    public static String setkm(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("kmnumber", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.KMKEY.toString(), null);
    }

    public static void getmail(Context context, String mail) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mail", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.MAIL.toString(), mail);
        editor.commit();
    }

    public static String setmail(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mail", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.MAIL.toString(), null);
    }

    public static void getautologin(Context context, String checklogin) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("checklogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(PreferenceKeys.AUTOLOGIN.toString(), checklogin);
        editor.commit();
    }

    public static String setautologin(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("checklogin", Context.MODE_PRIVATE);
        return sharedPreferences.getString(PreferenceKeys.AUTOLOGIN.toString(), null);
    }
}
