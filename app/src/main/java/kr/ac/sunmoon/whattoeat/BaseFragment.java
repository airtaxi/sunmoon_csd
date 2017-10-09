package kr.ac.sunmoon.whattoeat;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {

    public void onFragmentChanged(int index) {
        Fragment fragment = null;

        switch(index) {
            case 0:
                fragment = new SelectShopFragment();
                break;
            case 1:
                fragment = new EatFragment();
                break;
        }

        if (fragment != null) {
            getActivity().getSupportFragmentManager().beginTransaction().add(R.id.content, fragment).addToBackStack(null).commit();
        }
    }
}
