package com.appmaker.mvpdemo.View;

import android.app.ProgressDialog;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.appmaker.mvpdemo.Model.DataModel;
import com.appmaker.mvpdemo.Presenter.MainActivityPresenter;
import com.appmaker.mvpdemo.R;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View {

    private MainActivityPresenter mainActivityPresenter;
    private RecyclerView mRecyclerView;
    private ProgressDialog mProgressDialog;
    private CustomAdapter mCustomAdapter;

    private final String JSON_URL = "https://demonuts.com/Demonuts/JsonTest/Tennis/json_parsing.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainActivityPresenter = new MainActivityPresenter(this);

        initProgressBar();
        mainActivityPresenter.fetchJSONData(JSON_URL);
    }

    private void initProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage("Loading data...");
        mProgressDialog.setCancelable(false);
        showProgressBar();
    }

    @Override
    public void updateUserInfoTextView(String info) {
        //mTextView.setText(info);
    }

    @Override
    public void updateUserView(List<DataModel> dataModel) {
        mRecyclerView = findViewById(R.id.rv);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCustomAdapter = new CustomAdapter(this, dataModel);
        hideProgressBar();
        mRecyclerView.setAdapter(mCustomAdapter);
    }

    @Override
    public void updateErrorInfo() {
        hideProgressBar();
        Log.v(MainActivity.class.getSimpleName(), "error");
    }

    @Override
    public void showProgressBar() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        mProgressDialog.dismiss();
    }
}
