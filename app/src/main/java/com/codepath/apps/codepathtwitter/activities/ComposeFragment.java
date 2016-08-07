package com.codepath.apps.codepathtwitter.activities;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.apps.codepathtwitter.R;

public class ComposeFragment extends DialogFragment {
    public interface FilterDialogListener {
        void onFinishEditDialog(String status);
    }

    Button btnCancel;
    Button btnCompose;
    EditText etStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.activity_compose, container);

        btnCancel = (Button) view.findViewById(R.id.btnCancel);
        btnCompose = (Button) view.findViewById(R.id.btnCompose);
        etStatus = (EditText) view.findViewById(R.id.etStatus);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnCompose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterDialogListener listener = (FilterDialogListener) getActivity();
                listener.onFinishEditDialog(etStatus.getText().toString());
                dismiss();
            }
        });

        return view;
    }
}
