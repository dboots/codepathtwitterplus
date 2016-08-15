// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.codepathtwitter.fragments;

import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.codepathtwitter.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetListFragment_ViewBinding<T extends TweetListFragment> implements Unbinder {
  protected T target;

  public TweetListFragment_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.lvTweets = finder.findRequiredViewAsType(source, R.id.lvTweets, "field 'lvTweets'", ListView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.lvTweets = null;

    this.target = null;
  }
}
