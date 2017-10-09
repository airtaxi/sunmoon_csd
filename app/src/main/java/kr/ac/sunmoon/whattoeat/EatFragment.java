package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EatFragment extends BaseFragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup parentView, Bundle savedInstanceState){

        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_eat, parentView, false);

    /*    Button btn = rootView.findViewById(R.id.eatCheck_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity)getActivity();
                mainActivity.onFragmentChanged(3);
            }
        });*/
        return rootView;
    }
}
