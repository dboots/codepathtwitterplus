package com.codepath.apps.codepathtwitter.models;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
    private String name;
    private long uid;
    private String screenName;
    private String profileImageUrl;
    private String tagline;
    private String followers;
    private String following;

    public static User fromJSON(JSONObject jsonObject) {
        User u = new User();

        try {
            u.name = jsonObject.getString("name");
            u.uid = jsonObject.getLong("id");
            u.screenName = jsonObject.getString("screen_name");
            u.profileImageUrl = jsonObject.getString("profile_image_url");
            u.tagline = jsonObject.getString("description");
            u.followers = jsonObject.getString("followers_count");
            u.following = jsonObject.getString("friends_count");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return u;
    }

    public long getUid() {
        return uid;
    }
    public String getName() {
        return name;
    }
    public String getScreenName() {
        return "@" + screenName;
    }
    public String getProfileImageUrl() {
        return profileImageUrl;
    }
    public String getTagline() { return tagline; }
    public String getFollowers() { return followers; }
    public String getFollowing() { return following; }
}
