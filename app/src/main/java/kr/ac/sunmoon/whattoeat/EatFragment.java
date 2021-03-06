package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EatFragment extends Fragment {
    final String M_MSG = "main";
    final String M_TAG =  "main_tag";
    final String M_Start = "mainfragmentstart";

    Button check_btn;

    android.support.v4.app.FragmentManager myFragmentManager;

    MainFragment mainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_eat, null);

        myFragmentManager = getActivity().getSupportFragmentManager();

        mainFragment = new MainFragment();

        final Bundle bundle = getArguments();

        check_btn = view.findViewById(R.id.check_btn);
        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(M_MSG,M_Start);
                mainFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, mainFragment, M_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
