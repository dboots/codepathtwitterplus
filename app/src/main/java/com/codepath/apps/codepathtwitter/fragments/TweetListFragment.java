package com.codepath.apps.codepathtwitter.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.codepath.apps.codepathtwitter.EndlessScrollListener;
import com.codepath.apps.codepathtwitter.R;
import com.codepath.apps.codepathtwitter.adapters.TweetArrayAdapter;
import com.codepath.apps.codepathtwitter.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetListFragment extends Fragment {
    ListView lvTweets;
    private TweetArrayAdapter adapterTweets;
    private ArrayList<Tweet> tweets;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tweets_list, container, false);
        lvTweets = (ListView) v.findViewById(R.id.lvTweets);
        lvTweets.setAdapter(adapterTweets);

        lvTweets.setOnScrollListener(new EndlessScrollListener() {
            @Override
            public boolean onLoadMore(int page, int totalItemsCount) {
            load();
            return true;
            }
        });


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tweets = new ArrayList<>();
        adapterTweets = new TweetArrayAdapter(getActivity(), tweets);
    }

    public void addAll(List<Tweet> tweets) {
        adapterTweets.addAll(tweets);
    }

    public void setOnScroll(EndlessScrollListener esl) {
        lvTweets.setOnScrollListener(esl);
    }

    public void clear() {
        adapterTweets.clear();
    }
    public void load() { }

    public interface onComposeFinishedListener {
        void onComposeFinish();
    }
}
