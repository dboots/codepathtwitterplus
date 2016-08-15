package com.codepath.apps.codepathtwitter.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.codepathtwitter.R;
import com.codepath.apps.codepathtwitter.activities.ProfileActivity;
import com.codepath.apps.codepathtwitter.models.Tweet;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetArrayAdapter(Context context, ArrayList<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Tweet tweet = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);

            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.ivImage.setImageResource(0);
        viewHolder.tvScreenName.setText(tweet.getUser().getScreenName());
        viewHolder.tvName.setText(tweet.getUser().getName());
        viewHolder.tvCreatedAt.setText(tweet.getRelativeCreatedAt());

        viewHolder.ivImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ProfileActivity.class);
                Bundle args = new Bundle();
                args.putString("screen_name", tweet.getUser().getScreenName());
                i.putExtras(args);
                getContext().startActivity(i);
            }
        });

        viewHolder.tvBody.setText(tweet.getBody());

        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(viewHolder.ivImage);

        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.ivImage) ImageView ivImage;
        @BindView(R.id.tvScreenName) TextView tvScreenName;
        @BindView(R.id.tvName) TextView tvName;
        @BindView(R.id.tvCreatedAt) TextView tvCreatedAt;
        @BindView(R.id.tvBody) TextView tvBody;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
