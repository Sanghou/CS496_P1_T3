package com.example.user.cs496_p1_t3;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    public static Context contextOfApplication;
    public static Context getContextOfApplication()
    {
        return contextOfApplication;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            contextOfApplication = getApplicationContext();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction tr = fm.beginTransaction();

            Fragment frag1 = new ReadContact();
            Fragment frag2 = new Imagefrag();
            Fragment frag3 = new freefrag();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    tr.replace(R.id.message,frag1);
                    tr.commit();
                    return true;
                case R.id.navigation_dashboard:
                    tr.replace(R.id.message,frag2);
                    tr.commit();
                    return true;
                case R.id.navigation_notifications:
                    tr.replace(R.id.message,frag3);
                    tr.commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
