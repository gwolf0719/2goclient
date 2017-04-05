package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.silence5105.nosy.partner2go.R;

/**
 * Created by Nosy on 2017/3/13.
 */

public class NewLoginFragment extends Fragment implements View.OnClickListener {
    RelativeLayout phoneloginbtn;
    ImageView backbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.newloginactivity, container, false);
        phoneloginbtn = (RelativeLayout) view.findViewById(R.id.phoneloginbtn);
        phoneloginbtn.setOnClickListener(this);
        backbtn = (ImageView) view.findViewById(R.id.backbtn);
        backbtn.setOnClickListener(this);
        return view;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.newloginactivity);
//        initview();
//    }

    private void initview() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.phoneloginbtn:
//                Intent i = new Intent();
//                i.setClass(NewLoginFragment.this, PhoneCreateFragment.class);
//                startActivity(i);
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction.replace(R.id.container, new PhoneCreateFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

                break;
            case R.id.backbtn:
//                getActivity().getFragmentManager().beginTransaction().addToBackStack(new LoginSelectFragment()).commit()
                FragmentTransaction fragmentTransaction1 = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction1.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction1.replace(R.id.container, new LoginSelectFragment());
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
                break;
        }
    }
}
