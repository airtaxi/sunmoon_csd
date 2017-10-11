package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainFragment extends Fragment {

    //TAG
    final String E_MSG = "eat";
    final String E_TAG =  "eat_tag";
    final String E_Start = "eatfragmentstart";

    //TAG(SelectshopFragment)
    final String S_MSG = "shop";
    final String S_TAG = "shop_tag";
    final String S_Start = "shopfragmentstart";

    //TAG(BestMenu_details)
    final String B_MSG = "bestfood_details";
    final String B_TAG = "bestfood_details_tag";
    final String B_Start = "bestfood_details_fragmentstart";

    Button mshopselect_btn, meat_btn;
    LinearLayout food_bestmenu;


    android.support.v4.app.FragmentManager myFragmentManager;

    SelectShopFragment shopFragment;
    EatFragment eatFragment;
    DetailsFragment detailsFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_main, null);

        myFragmentManager = getActivity().getSupportFragmentManager();

        detailsFragment = new DetailsFragment();
        shopFragment = new SelectShopFragment();

        final Bundle bundle = getArguments();

        food_bestmenu = view.findViewById(R.id.bestmenu);
        food_bestmenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                bundle.putSerializable(B_MSG,B_Start);
                detailsFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, detailsFragment, B_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        mshopselect_btn = view.findViewById(R.id.shopselect_btn);
        mshopselect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bundle.putString(S_MSG,S_Start);
                shopFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, shopFragment, S_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        eatFragment = new EatFragment();

        meat_btn = view.findViewById(R.id.eat_btn);
        meat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //goto TAG_msg
                bundle.putString(E_MSG,E_Start);
                eatFragment.setArguments(bundle);

                //excute fragment
                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, eatFragment, E_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }
}
