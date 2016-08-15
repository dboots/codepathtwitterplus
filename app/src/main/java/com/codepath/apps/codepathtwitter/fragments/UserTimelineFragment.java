package com.codepath.apps.codepathtwitter.fragments;

import android.os.Bundle;
import android.widget.Toast;

import com.codepath.apps.codepathtwitter.RestApplication;
import com.codepath.apps.codepathtwitter.RestClient;
import com.codepath.apps.codepathtwitter.fragments.TweetListFragment;
import com.codepath.apps.codepathtwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class UserTimelineFragment extends TweetListFragment {
    private RestClient client;
    private int page = 1;

    public static UserTimelineFragment newinstance(String screenName) {
        UserTimelineFragment userFragment = new UserTimelineFragment();
        Bundle args = new Bundle();
        args.putString("screenName", screenName);
        userFragment.setArguments(args);
        return userFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = RestApplication.getRestClient();
        populateTimeline(1);
    }

    @Override
    public void load() {
        populateTimeline(page++);
    }

    private void populateTimeline(int page) {
        String screenName = getArguments().getString("screenName");
        client.getUserTimeline(page, screenName, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                addAll(Tweet.fromJSONArray(response));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Toast.makeText(getContext(), responseString, Toast.LENGTH_LONG).show();
            }
        });
    }

}
