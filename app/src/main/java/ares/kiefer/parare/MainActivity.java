package ares.kiefer.parare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import ares.kiefer.parare.subject.AddSubject;
import ares.kiefer.parare.subject.SubjectFragment;

public class MainActivity extends AppCompatActivity{

    final static int REQUEST_CODE_LOGIN = 0;
    final static String KEY_USERNAME = "username";
    final static String KEY_ID = "id";

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private long currentID;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        currentID = sharedPreferences.getLong(KEY_ID, -1);

        if(currentID != -1){

        }
        else{
            startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), REQUEST_CODE_LOGIN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_LOGIN && resultCode == RESULT_OK){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            currentID = sharedPreferences.getLong(KEY_ID, -1);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle = new Bundle();
        bundle.putLong("curr_id", currentID);
        SubjectFragment subjectFragment = new SubjectFragment();
        subjectFragment.setArguments(bundle);
        adapter.addFragment(subjectFragment, "Subjects");
        adapter.addFragment(new TaskFragment(), "Tasks");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
    }
}
