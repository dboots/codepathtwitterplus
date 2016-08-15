// Generated code from Butter Knife. Do not modify!
package com.codepath.apps.codepathtwitter.activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Finder;
import com.codepath.apps.codepathtwitter.R;
import java.lang.IllegalStateException;
import java.lang.Object;
import java.lang.Override;

public class ComposeFragment_ViewBinding<T extends ComposeFragment> implements Unbinder {
  protected T target;

  private View view2131361877;

  private View view2131361878;

  public ComposeFragment_ViewBinding(final T target, final Finder finder, Object source) {
    this.target = target;

    View view;
    view = finder.findRequiredView(source, R.id.btnCompose, "field 'btnCompose' and method 'compose'");
    target.btnCompose = finder.castView(view, R.id.btnCompose, "field 'btnCompose'", Button.class);
    view2131361877 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.compose(finder.<Button>castParam(p0, "doClick", 0, "compose", 0));
      }
    });
    view = finder.findRequiredView(source, R.id.btnCancel, "field 'btnCancel' and method 'cancel'");
    target.btnCancel = finder.castView(view, R.id.btnCancel, "field 'btnCancel'", Button.class);
    view2131361878 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.cancel(finder.<Button>castParam(p0, "doClick", 0, "cancel", 0));
      }
    });
    target.etStatus = finder.findRequiredViewAsType(source, R.id.etStatus, "field 'etStatus'", EditText.class);
  }

  @Override
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.btnCompose = null;
    target.btnCancel = null;
    target.etStatus = null;

    view2131361877.setOnClickListener(null);
    view2131361877 = null;
    view2131361878.setOnClickListener(null);
    view2131361878 = null;

    this.target = null;
  }
}
