package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainFragment extends BaseFragment {
    public static final String TAG = "MainFragment";

    public View onCreateView(LayoutInflater inflater, ViewGroup parentView, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_main, parentView, false);

        Button select_btn = rootView.findViewById(R.id.shopselect_btn);
        Button eat_btn = rootView.findViewById(R.id.eat_btn);

        select_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentChanged(0);
            }
        });

        eat_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFragmentChanged(1);
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }
}
