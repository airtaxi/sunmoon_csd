package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//수정요망
public class WeeklyFoodListFragment extends Fragment {


    android.support.v4.app.FragmentManager myFragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_weeklyfoodlist, null);

        myFragmentManager = getActivity().getSupportFragmentManager();
        final Bundle bundle = getArguments();



        return view;
    }


}
