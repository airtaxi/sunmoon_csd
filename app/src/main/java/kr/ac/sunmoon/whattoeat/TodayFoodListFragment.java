package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TodayFoodListFragment extends Fragment {


    //TAG(Menu_details)
    final String Mn_MSG = "food_details";
    final String Mn_TAG = "food_details_tag";
    final String Mn_Start = "food_details_fragmentstart";

    //menu. name(developing)
    LinearLayout menu;


    android.support.v4.app.FragmentManager myFragmentManager;

    DetailsFragment detailsFragment;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_todayfoodlist, null);

        myFragmentManager = getActivity().getSupportFragmentManager();

        detailsFragment = new DetailsFragment();

        final Bundle bundle = getArguments();

        menu = view.findViewById(R.id.menu1);
        menu.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view){

                bundle.putString(Mn_MSG,Mn_Start);
                detailsFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, detailsFragment, Mn_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });




        return view;
    }


}
