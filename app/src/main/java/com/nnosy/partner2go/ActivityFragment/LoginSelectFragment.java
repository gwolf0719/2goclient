package com.nnosy.partner2go.ActivityFragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.nnosy.partner2go.R;

/**
 * Created by Nosy on 2017/3/13.
 */

public class LoginSelectFragment extends Fragment implements View.OnClickListener {
    RelativeLayout loginbtn, createbtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.loginseletlayout, container, false);
        loginbtn = (RelativeLayout) view.findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(this);
        createbtn = (RelativeLayout) view.findViewById(R.id.createbtn);
        createbtn.setOnClickListener(this);
        return view;
    }

//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(com.silence5105.nosy.partner2go.R.layout.loginseletlayout);
//        initview();
//
//    }

    private void initview() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.loginbtn:
//                Intent intent1 = new Intent();
//                intent1.setClass(LoginSelectFragment.this, PhoneLoginFragment.class);
//                startActivity(intent1);
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction.replace(R.id.container, new PhoneLoginFragment());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new PhoneLoginFragment()).commit();

                break;
            case R.id.createbtn:
                //create

//                Intent intent = new Intent();
//                intent.setClass(LoginSelectFragment.this, NewLoginFragment.class);
//                startActivity(intent);
                FragmentTransaction fragmentTransaction1 = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction1.setCustomAnimations(R.anim.fragment_slide_left_enter, R.anim.fragment_slide_left_exit);
                fragmentTransaction1.replace(R.id.container, new NewLoginFragment());
                fragmentTransaction1.addToBackStack(null);
                fragmentTransaction1.commit();
//                getActivity().getFragmentManager().beginTransaction().replace(R.id.container,new Newlogin)
                break;
        }
    }
}
