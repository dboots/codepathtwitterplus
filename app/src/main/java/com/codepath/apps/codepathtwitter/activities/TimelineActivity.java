package com.codepath.apps.codepathtwitter.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.astuetz.PagerSlidingTabStrip;
import com.codepath.apps.codepathtwitter.R;
import com.codepath.apps.codepathtwitter.RestApplication;
import com.codepath.apps.codepathtwitter.RestClient;
import com.codepath.apps.codepathtwitter.adapters.TweetArrayAdapter;
import com.codepath.apps.codepathtwitter.fragments.HomeTimelineFragment;
import com.codepath.apps.codepathtwitter.fragments.MentionsTimelineFragment;

import com.codepath.apps.codepathtwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity implements ComposeFragment.FilterDialogListener {
    private ComposeFragment composeFragment;
    private RestClient client;
    private FragmentManager fm;
    private ArrayList<Tweet> tweets;
    PagerSlidingTabStrip tabStrip;
    ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);
        ButterKnife.bind(this);

        vp = (ViewPager) findViewById(R.id.viewpager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        vp.setAdapter(new TweetPagerAdapter(getSupportFragmentManager()));
        tabStrip.setViewPager(vp);

        client = RestApplication.getRestClient();
        fm = getSupportFragmentManager();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void OnCompose(MenuItem mi) {
        Bundle bundle = new Bundle();

        composeFragment = new ComposeFragment();
        composeFragment.setArguments(bundle);
        composeFragment.show(fm, "activity_compose");
    }

    public void OnProfileView(MenuItem mi) {
        Intent i = new Intent(this, ProfileActivity.class);
        startActivity(i);
    }

    @Override
    public void onFinishEditDialog(String status) {
        client.composeTweet(status, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public class TweetPagerAdapter extends FragmentStatePagerAdapter {
        private String[] tabTitles  = {"Home", "Mentions"};

        public TweetPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeTimelineFragment();
            } else if (position == 1) {
                return new MentionsTimelineFragment();
            } else {
                return null;
            }
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }
    }
}
