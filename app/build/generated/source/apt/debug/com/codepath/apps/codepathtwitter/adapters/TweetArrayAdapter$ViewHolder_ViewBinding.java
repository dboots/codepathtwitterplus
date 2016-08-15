// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.codepathtwitter.adapters;

import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Finder;
import com.codepath.apps.codepathtwitter.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class TweetArrayAdapter$ViewHolder_ViewBinding<T extends TweetArrayAdapter.ViewHolder> implements Unbinder {
  protected T target;

  public TweetArrayAdapter$ViewHolder_ViewBinding(T target, Finder finder, Object source) {
    this.target = target;

    target.ivImage = finder.findRequiredViewAsType(source, R.id.ivImage, "field 'ivImage'", ImageView.class);
    target.tvScreenName = finder.findRequiredViewAsType(source, R.id.tvScreenName, "field 'tvScreenName'", TextView.class);
    target.tvName = finder.findRequiredViewAsType(source, R.id.tvName, "field 'tvName'", TextView.class);
    target.tvCreatedAt = finder.findRequiredViewAsType(source, R.id.tvCreatedAt, "field 'tvCreatedAt'", TextView.class);
    target.tvBody = finder.findRequiredViewAsType(source, R.id.tvBody, "field 'tvBody'", TextView.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.ivImage = null;
    target.tvScreenName = null;
    target.tvName = null;
    target.tvCreatedAt = null;
    target.tvBody = null;

    this.target = null;
  }
}
