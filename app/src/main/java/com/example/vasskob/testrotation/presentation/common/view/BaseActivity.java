package com.example.vasskob.testrotation.presentation.common.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.vasskob.testrotation.R;

import dagger.android.AndroidInjection;

public abstract class BaseActivity extends MvpAppCompatActivity {

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("ShowToast")
    protected void showToast(int messageRes) {
        if (toast != null) toast.cancel();
        toast = Toast.makeText(this, messageRes, Toast.LENGTH_SHORT);
        showCenteredToast();
    }

    private void showCenteredToast() {
        TextView msg = toast.getView().findViewById(android.R.id.message);
        if (msg != null) msg.setGravity(Gravity.CENTER);
        toast.show();
    }

    public void showConnectionFailedToast() {
        showToast(R.string.connection_error);
    }

    public void showConnectionSuccessToast() {
        showToast(R.string.connection_success);
    }

}
