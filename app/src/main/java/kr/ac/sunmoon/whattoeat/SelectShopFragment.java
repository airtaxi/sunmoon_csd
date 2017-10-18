package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SelectShopFragment extends Fragment {


    //rest. name
    LinearLayout rest_orenge, rest_student, rest_faculty, rest_dormitory;
    FoodCycle_1_Fragment food_1_Fragment;

    android.support.v4.app.FragmentManager myFragmentManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_selectshop, null);

        myFragmentManager = getActivity().getSupportFragmentManager();

        food_1_Fragment = new FoodCycle_1_Fragment();

        ImageView imgOrange = (ImageView) view.findViewById(R.id.orangefood_img);
        ImageView imgStudent = (ImageView) view.findViewById(R.id.studentfood_img);
        ImageView imgDormitory = (ImageView) view.findViewById(R.id.dormitoryfood_img);
        ImageView imgFaculty = (ImageView) view.findViewById(R.id.facultyfood_img);

        ImageAsync.setRemoteImage(imgOrange, MainActivity.mRestDatabase.restaurants.get(1).img);
        ImageAsync.setRemoteImage(imgDormitory, MainActivity.mRestDatabase.restaurants.get(0).img);
        ImageAsync.setRemoteImage(imgFaculty, MainActivity.mRestDatabase.restaurants.get(2).img);
        ImageAsync.setRemoteImage(imgStudent, MainActivity.mRestDatabase.restaurants.get(3).img);

        rest_orenge = view.findViewById(R.id.orengefood);
        rest_orenge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("shopIndex", 1);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rest_student = view.findViewById(R.id.studentfood);
        rest_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("shopIndex", 3);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rest_dormitory = view.findViewById(R.id.dormitoryfood);
        rest_dormitory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("shopIndex", 0);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        rest_faculty = view.findViewById(R.id.facultyfood);
        rest_faculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("shopIndex", 2);
                food_1_Fragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, food_1_Fragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return view;
    }


}
