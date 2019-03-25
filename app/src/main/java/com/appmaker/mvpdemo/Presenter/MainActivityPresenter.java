package com.appmaker.mvpdemo.Presenter;

import android.view.View;

import com.appmaker.mvpdemo.Model.User;

public class MainActivityPresenter {

    private View mView;
    private User mUser;

    public MainActivityPresenter(View mView) {
        this.mView = mView;
        this.mUser = new User();
    }

    public void updateFullName(String fName) {

    }

    public void updateEmail(String eMail) {

    }

    public interface View {

        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }
}
