package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import kr.ac.sunmoon.whattoeat.datatypes.RestMenu;

import static kr.ac.sunmoon.whattoeat.MainActivity.mRestDatabase;

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

        Bundle bundle = getArguments();

        int shopIndex = bundle.getInt("shopIndex");

        LinearLayout menuLayout = (LinearLayout) view.findViewById(R.id.menu_layout);

        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
        if(mRestDatabase.restaurants.get(shopIndex).menus != null) {
            for (int index = 0; index < mRestDatabase.restaurants.get(shopIndex).menus.size(); index++) {
                RestMenu restMenu = mRestDatabase.restaurants.get(shopIndex).menus.get(index);
                int likeCnt = 0;
                int commentCnt = 0;
                if (restMenu.response != null) {
                    HashMap<String, HashMap<String, Object>> comments = (HashMap<String, HashMap<String, Object>>) restMenu.response.get("comment");
                    HashMap<String, String> likes = (HashMap<String, String>) restMenu.response.get("like");


                    long epoachSeconds = System.currentTimeMillis();
                    Date dateNow = new Date(epoachSeconds);

                    SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
                    String date = sdfDate.format(dateNow);

                    if(likes != null) {
                        for(String idx : likes.keySet()) {
                            String like = likes.get(idx);
                            if (like.equals(date)) {
                                likeCnt++;
                            }
                        }
                    }
                    if(comments != null) {
                        for(String idx : comments.keySet()) {
                            HashMap<String, Object> comment = comments.get(idx);
                            if (comment.get("date").equals(date)) {
                                commentCnt++;
                            }
                        }
                    }

                }

                ListMenuFragment menu = new ListMenuFragment();
                Bundle bundleToPass = new Bundle();
                bundleToPass.putString("img", restMenu.img);
                bundleToPass.putString("name", restMenu.name);
                bundleToPass.putInt("likes", likeCnt);
                bundleToPass.putInt("comments", commentCnt);
                bundleToPass.putInt("shopIndex", shopIndex);
                bundleToPass.putInt("menuIndex", index);
                menu.setArguments(bundleToPass);

                fragmentTransaction.add(R.id.menu_layout, menu);
            }
            fragmentTransaction.commit();
        }
        return view;
    }


}
