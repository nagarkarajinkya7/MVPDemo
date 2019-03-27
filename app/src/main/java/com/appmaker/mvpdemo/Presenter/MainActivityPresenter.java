package com.appmaker.mvpdemo.Presenter;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import com.appmaker.mvpdemo.Model.DataModel;
import com.appmaker.mvpdemo.Model.User;
import com.appmaker.mvpdemo.View.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenter {

    private View mView;
    private User mUser;
    private List<DataModel> mDataModel;
    URL jsonUrl;
    HttpURLConnection mHttpURLConnection;
    private final int CONNECTION_TIMEOUT = 10000;
    private final int READ_TIMEOUT = 15000;

    public MainActivityPresenter(View mView) {
        this.mView = mView;
        this.mUser = new User();

    }

    public void fetchJSONData(String url) {

        try {
            jsonUrl = new URL(url);
            new JSONFetch().execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class JSONFetch extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                mHttpURLConnection = (HttpURLConnection) jsonUrl.openConnection();
                mHttpURLConnection.setConnectTimeout(CONNECTION_TIMEOUT);
                mHttpURLConnection.setReadTimeout(READ_TIMEOUT);
                mHttpURLConnection.setRequestMethod("GET");

                mHttpURLConnection.setDoOutput(true);

                int response_code = mHttpURLConnection.getResponseCode();
                if (response_code == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = mHttpURLConnection.getInputStream();

                    BufferedReader bufferedReader = new
                            BufferedReader(new InputStreamReader(inputStream));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    inputStream.close();
                    return (stringBuffer.toString());
                } else {
                    Log.v(MainActivityPresenter.class.getSimpleName(), "failed");
                    return "FAILED";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "FAILED";
            } finally {
                mHttpURLConnection.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            Log.v(MainActivityPresenter.class.getSimpleName(), "resullt : " + result);
            if (result != null && !result.equals("FAILED")) {
                mDataModel = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if (jsonObject.getString("status").equals("true")) {

                        JSONArray dataArray = jsonObject.getJSONArray("data");

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject jsonObject1 = dataArray.getJSONObject(i);
                            DataModel current = new DataModel(jsonObject1.getString("name"), jsonObject1.getString("imgURL"), jsonObject1.getString("country"),
                                    jsonObject1.getString("city"));
                            mDataModel.add(current);
                        }
                    }
                    
                    if (!mDataModel.isEmpty()) {
                        mView.updateUserView(mDataModel);
                    } else {
                        Log.v(MainActivityPresenter.class.getSimpleName(), "empty data model");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                mView.updateErrorInfo();
                Log.v(MainActivityPresenter.class.getSimpleName(), "result empty");
            }
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }
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

        void updateUserView(List<DataModel> dataModel);

        void updateErrorInfo();

        void showProgressBar();

        void hideProgressBar();
    }
}
