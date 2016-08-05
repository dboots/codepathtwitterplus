package com.codepath.apps.codepathtwitter.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.codepathtwitter.R;
import com.codepath.apps.codepathtwitter.models.Tweet;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TweetArrayAdapter extends ArrayAdapter<Tweet> {
    public TweetArrayAdapter(Context context, ArrayList<Tweet> tweets) {
        super(context, android.R.layout.simple_list_item_1, tweets);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Tweet tweet = getItem(position);
        ViewHolder viewHolder;
        ImageView ivProfileImage;
        TextView tvUsername;
        TextView tvBody;
        TextView tvCreatedAt;

        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
        dateFormat.setLenient(true);
        Date createdAt = new Date();
        String relativeCreatedAt;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_tweet, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
            viewHolder.tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
            viewHolder.tvCreatedAt = (TextView) convertView.findViewById(R.id.tvCreatedAt);
            viewHolder.tvBody = (TextView) convertView.findViewById(R.id.tvBody);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        try {
            createdAt = dateFormat.parse(tweet.getCreatedAt());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        relativeCreatedAt = DateUtils.getRelativeDateTimeString(getContext(), createdAt.getTime(), DateUtils.DAY_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, 0).toString();

        viewHolder.ivImage.setImageResource(0);
        viewHolder.tvUsername.setText(tweet.getUser().getScreenName() + " at " + tweet.getCreatedAt());
        viewHolder.tvCreatedAt.setText(relativeCreatedAt);

        viewHolder.tvBody.setText(tweet.getBody());

        Picasso.with(getContext()).load(tweet.getUser().getProfileImageUrl()).into(viewHolder.ivImage);

        return convertView;
    }

    public static class ViewHolder {
        ImageView ivImage;
        TextView tvUsername;
        TextView tvCreatedAt;
        TextView tvBody;
    }
}
