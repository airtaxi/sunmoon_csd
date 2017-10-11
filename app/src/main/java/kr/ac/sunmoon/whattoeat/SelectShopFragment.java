package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SelectShopFragment extends Fragment {


    //rest. name
    LinearLayout rest_orenge, rest_student, rest_faculty, rest_dormitory;
    FoodCycle_1_Fragment food_1_Fragment;

    //TAG
    final String F1_MSG = "food1";
    final String F1_TAG = "food1_tag";
    final String F1_Start = "food1_fragmentstart";

    android.support.v4.app.FragmentManager myFragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_selectshop, null);

        myFragmentManager = getActivity().getSupportFragmentManager();
        final Bundle bundle = getArguments();

        food_1_Fragment = new FoodCycle_1_Fragment();

        rest_orenge = view.findViewById(R.id.orengefood);
        rest_orenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(F1_MSG,F1_Start);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment, F1_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rest_student = view.findViewById(R.id.studentfood);
        rest_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(F1_MSG,F1_Start);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment, F1_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rest_dormitory = view.findViewById(R.id.dormitoryfood);
        rest_dormitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(F1_MSG,F1_Start);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment, F1_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rest_faculty = view.findViewById(R.id.facultyfood);
        rest_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString(F1_MSG,F1_Start);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment, F1_TAG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}
