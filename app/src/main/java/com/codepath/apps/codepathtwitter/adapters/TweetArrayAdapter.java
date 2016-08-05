package com.codepath.apps.codepathtwitter.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

import com.codepath.apps.codepathtwitter.models.Tweet;

import java.util.ArrayList;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetArrayAdapter(Context context, ArrayList<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }
}
