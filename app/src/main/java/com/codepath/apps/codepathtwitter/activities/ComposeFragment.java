package com.codepath.apps.codepathtwitter.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.codepathtwitter.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ComposeFragment extends DialogFragment {
    public interface FilterDialogListener {
        void onFinishEditDialog(String status);
    }

    @BindView(R.id.btnCompose) Button btnCompose;
    @BindView(R.id.btnCancel) Button btnCancel;
    @BindView(R.id.etStatus) EditText etStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_compose, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnCancel)
    public void cancel(Button btn) {
        dismiss();
    }

    @OnClick(R.id.btnCompose)
    public void compose(Button btn) {
        FilterDialogListener listener = (FilterDialogListener) getActivity();
        listener.onFinishEditDialog(etStatus.getText().toString());
        dismiss();
    }
}
