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
        mUser.setFullName(fName);
        mView.updateUserInfoTextView(mUser.toString());
    }

    public void updateEmail(String eMail) {
        mUser.setEmailId(eMail);
        mView.updateUserInfoTextView(mUser.toString());

    }

    public interface View {

        void updateUserInfoTextView(String info);
        void showProgressBar();
        void hideProgressBar();
    }
}
