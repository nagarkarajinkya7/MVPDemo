package com.appmaker.mvpdemo.View;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.appmaker.mvpdemo.Presenter.MainActivityPresenter;
import com.appmaker.mvpdemo.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private MainActivityPresenter mainActivityPresenter;
    private TextView mTextView;
    private ProgressBar mProgressBar;
    private EditText etUserName, etEmailId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainActivityPresenter = new MainActivityPresenter(this);

        mTextView = findViewById(R.id.myTextView);
        etUserName = findViewById(R.id.username);
        etEmailId = findViewById(R.id.email);
        initProgressBar();
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainActivityPresenter.updateFullName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideProgressBar();
            }
        });

        etEmailId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mainActivityPresenter.updateEmail(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                hideProgressBar();
            }
        });

    }

    private void initProgressBar() {
        mProgressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleSmall);
        mProgressBar.setIndeterminate(true);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(Resources.getSystem().getDisplayMetrics().widthPixels,
                250);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        this.addContentView(mProgressBar, params);
        showProgressBar();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        mTextView.setText(info);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }
}
