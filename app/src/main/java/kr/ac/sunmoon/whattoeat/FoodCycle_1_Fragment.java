package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FoodCycle_1_Fragment extends Fragment {

    //TAG(Today food)
    final String T_MSG = "today_foodlist";
    final String T_TAG = "today_foodlist_tag";
    final String T_Start = "today_foodlist_fragmentstart";

    //TAG(Weekly food) --> developing layout
    final String W_MSG = "weekly_foodlist";
    final String W_TAG = "weekly_foodlist_tag";
    final String W_Start = "weekly_foodlist_fragmentstart";

    Button mtodayfood_btn,mweeklyfood_btn;

    android.support.v4.app.FragmentManager myFragmentManager;

    TodayFoodListFragment todayfoodlistFragment;
    WeeklyFoodListFragment weeklyfoodlistFragment;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_foodcycle_1, null);

        myFragmentManager = getActivity().getSupportFragmentManager();

        todayfoodlistFragment = new TodayFoodListFragment();
        weeklyfoodlistFragment = new WeeklyFoodListFragment();

        final Bundle bundle = getArguments();

        mtodayfood_btn = view.findViewById(R.id.todayfood_btn);
        mtodayfood_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                bundle.putString(T_MSG,T_Start);
                todayfoodlistFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, todayfoodlistFragment, T_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        mweeklyfood_btn = view.findViewById(R.id.weeklyfood_btn);
        mweeklyfood_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                bundle.putString(W_MSG,W_Start);
                todayfoodlistFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, weeklyfoodlistFragment, W_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });









        return view;
    }

}
