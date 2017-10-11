package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    FrameLayout container;
    FragmentManager myFragmentManager;

    //-----Fragment View
    MainFragment mainFragment;

    //TAG
    final String M_MSG = "main";
    final String M_TAG = "main_tag";
    final String M_Start = "mainfragmentstart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //framelayout.
        container = findViewById(R.id.content);

        myFragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();


        //goto TAG_msg
        Bundle bundle = new Bundle();
        bundle.putString(M_MSG, M_Start);
        mainFragment.setArguments(bundle);

        //excute fragmnet
        FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, mainFragment, M_TAG);
        fragmentTransaction.commit();
    }
}
