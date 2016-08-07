package com.codepath.apps.codepathtwitter.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.codepath.apps.codepathtwitter.EndlessScrollListener;
import com.codepath.apps.codepathtwitter.R;
import com.codepath.apps.codepathtwitter.RestApplication;
import com.codepath.apps.codepathtwitter.RestClient;
import com.codepath.apps.codepathtwitter.adapters.TweetArrayAdapter;
import com.codepath.apps.codepathtwitter.models.Tweet;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;
import org.scribe.builder.api.TwitterApi;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TimelineActivity extends AppCompatActivity implements ComposeFragment.FilterDialogListener {
    private RestClient client;
    private ArrayList<Tweet> tweets;
    private TweetArrayAdapter adapterTweets;
    private ListView lvTweets;
    private int timelinePage = 1;
    private ComposeFragment composeFragment;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        fm = getSupportFragmentManager();

        lvTweets = (ListView) findViewById(R.id.lvTweets);
        tweets = new ArrayList<Tweet>();
        adapterTweets = new TweetArrayAdapter(this, tweets);
        lvTweets.setAdapter(adapterTweets);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        client = RestApplication.getRestClient();

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
                populateTimeline(timelinePage++);
                return true;
            }
        });

        populateTimeline(1);
    }

    @Override
    public void onFinishEditDialog(String status) {
        client.composeTweet(status, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject jsonObject) {
                adapterTweets.clear();
                populateTimeline(1);
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

    private void populateTimeline(int page) {
        client.getHomeTimeline(page, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                adapterTweets.addAll(Tweet.fromJSONArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getApplicationContext(), responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void menuCompose(MenuItem item) {
        Bundle bundle = new Bundle();

        composeFragment = new ComposeFragment();
        composeFragment.setArguments(bundle);
        composeFragment.show(fm, "activity_compose");
    }
}
