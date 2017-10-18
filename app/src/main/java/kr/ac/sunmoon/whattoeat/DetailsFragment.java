package kr.ac.sunmoon.whattoeat;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import static com.google.android.gms.internal.zzs.TAG;
import static kr.ac.sunmoon.whattoeat.MainActivity.mRestDatabase;

public class DetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        //call layout
        View view = inflater.inflate(R.layout.activity_todayfoodlist_details, null);

        final Bundle bundle = getArguments();

        String imgPath = bundle.getString("img");
        String foodName = bundle.getString("name");
        final int shopIndex = bundle.getInt("shopIndex");
        final int menuIndex = bundle.getInt("menuIndex");

        ImageAsync.setRemoteImage((ImageView) view.findViewById(R.id.food_img), imgPath);

        ((TextView) view.findViewById(R.id.food_name)).setText((foodName));

        long epoachSeconds = System.currentTimeMillis();
        Date dateNow = new Date(epoachSeconds);

        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        final String date = sdfDate.format(dateNow);

        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm:ss");
        final String time = sdfTime.format(dateNow);

        Log.d(TAG, "dateNOW WTE" + String.valueOf(date));
        Log.d(TAG, "dateNOW WTE" + String.valueOf(time));


        final FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        if(mRestDatabase.restaurants.get(shopIndex).menus.get(menuIndex).response != null) {
            HashMap<String, HashMap<String, Object>> commentsOrig = (HashMap<String, HashMap<String, Object>>) mRestDatabase.restaurants.get(shopIndex).menus.get(menuIndex).response.get("comment");
            TreeMap<String, HashMap<String, Object>> comments = new TreeMap<String, HashMap<String, Object>>();
            comments.putAll(commentsOrig);
            HashMap<String, String> likes = (HashMap<String, String>) mRestDatabase.restaurants.get(shopIndex).menus.get(menuIndex).response.get("like");

            int commentCnt = 0;
            int likeCnt = 0;
            if(comments != null) {
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                for (String index : comments.keySet()) {
                    HashMap<String, Object> comment = comments.get(index);
                    if (comment.get("date").equals(date)) {
                        commentCnt++;
                        CommentFragment cmt = new CommentFragment();
                        Bundle bunleToPass = new Bundle();
                        bunleToPass.putString("content", (String) comment.get("content"));
                        bunleToPass.putString("name", (String) comment.get("name"));
                        cmt.setArguments(bunleToPass);

                        fragmentTransaction.add(R.id.layout_comment, cmt);
                    }
                }
                fragmentTransaction.commit();
            }

            if(likes != null) {
                for (String index : likes.keySet()) {
                    String like = likes.get(index);
                    if (like.equals(date)) {
                        likeCnt++;
                    }
                }
            }

            ((TextView) view.findViewById(R.id.tv_reply)).setText(String.valueOf(commentCnt));
            ((TextView) view.findViewById(R.id.tv_like)).setText(String.valueOf(likeCnt));

        }

        Button btnComment = view.findViewById(R.id.btn_comment);
        final EditText etWriter = view.findViewById(R.id.et_writer);
        final EditText etContent = view.findViewById(R.id.et_content);
        final ScrollView scrollView = view.findViewById(R.id.detail_scroll);
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etWriter.getText().length() > 0 && etContent.getText().length() > 0) {
                    FBDB.addComment(shopIndex, menuIndex, etWriter.getText().toString(), etContent.getText().toString(),
                            date, time);

                    Bundle bunleToPass = new Bundle();
                    bunleToPass.putString("content", (String) etContent.getText().toString());
                    bunleToPass.putString("name", (String) etWriter.getText().toString());
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    CommentFragment cmt = new CommentFragment();
                    cmt.setArguments(bunleToPass);

                    fragmentTransaction.add(R.id.layout_comment, cmt);
                    fragmentTransaction.commit();
                    etContent.setText("");
                    etWriter.setText("");

                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.scrollTo(0, scrollView.getBottom());
                        }
                    });
                } else {
                    Toast.makeText(getContext(), "작성자와 댓글 내용이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
