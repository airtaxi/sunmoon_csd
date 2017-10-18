package kr.ac.sunmoon.whattoeat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.FrameLayout;

import kr.ac.sunmoon.whattoeat.datatypes.Restaurants;

public class MainActivity extends AppCompatActivity {

    public static Restaurants mRestDatabase;

    private boolean isFirstLaunch;
    FrameLayout container;
    FragmentManager myFragmentManager;

    //-----Fragment View
    MainFragment mainFragment;

    //TAG
    final String M_MSG = "main";
    final String M_TAG = "main_tag";
    final String M_Start = "mainfragmentstart";
    public static final String TAG = "WHATTOEAT";


    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);


        context = this;
        //framelayout.
        container = findViewById(R.id.content);

        myFragmentManager = getSupportFragmentManager();
        mainFragment = new MainFragment();


        //goto TAG_msg
        Bundle bundle = new Bundle();
        bundle.putString(M_MSG, M_Start);
        mainFragment.setArguments(bundle);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            boolean showRationale = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                showRationale = shouldShowRequestPermissionRationale(android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
            isFirstLaunch = pref.getBoolean("isFirstLaunch", true);
            if (isFirstLaunch) {
                SharedPreferences prefWrite = getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor prefEdit = prefWrite.edit();
                prefEdit.putBoolean("isFirstLaunch", false);
                prefEdit.commit();
                showRationale = true;
            }
            Log.d(TAG, "Rationale = " + showRationale);
            if (!showRationale) {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("이 애플리케이션은 사용을 위해 저장소 쓰기 권한을 요구합니다.\n권한 설정으로 이동하여 쓰기 권한을 허용해주십시오.");
                dlgAlert.setTitle("권한 설정 안내");
                dlgAlert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        startActivity(intent);
                        finish();
                    }
                });
                dlgAlert.show();
            } else {
                AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                dlgAlert.setMessage("이 애플리케이션은 사용을 위해 저장소 쓰기 권한을 요구합니다.");
                dlgAlert.setTitle("권한 설정 안내");
                dlgAlert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 424);
                    }
                });
                dlgAlert.show();
            }
        }

        FBDB.refreshData(new FBDB.RestaurantGetListener() {
            @Override
            public void onReceived(boolean isError) {
                //excute fragmnet
                FragmentTransaction fragmentTransaction = myFragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.content, mainFragment, M_TAG);
                fragmentTransaction.commit();
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 424) {
            if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                finish();
            }
        }
    }
}
