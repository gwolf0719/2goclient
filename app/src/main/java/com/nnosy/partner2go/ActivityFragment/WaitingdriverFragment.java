package com.nnosy.partner2go.ActivityFragment;

import android.app.DialogFragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.nnosy.partner2go.R;

/**
 * Created by Nosy on 2017/1/24.
 */

public class WaitingdriverFragment extends DialogFragment {
//    Context mContext;

    //    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        mContext = activity;
//    }
//
    VideoView videoView;
    public TextView cancelbtn;
//



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newwaitingdriverlayout, container, true);
//        initview();

//        cancelbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
        cancelbtn = (TextView) getActivity().findViewById(R.id.bookingcancelbtn);
        videoView = (VideoView) view.findViewById(R.id.videoView);
        Uri uri = Uri.parse("android.resource://" + "com.silence5105.nosy.partner2go" + "/" + R.raw.pad);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        android.widget.MediaController mediaController = new android.widget.MediaController(getActivity());
        mediaController.setVisibility(View.INVISIBLE);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
//        view.setEnabled(true);
//        view.setClickable(false);
        return view;
    }

    public void initview() {

//        cancelbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                dismiss();
//            }
//        });
//        cancelbtn.setOnClickListener((View.OnClickListener) getActivity());
    }


}
