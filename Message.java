package com.ample.ample.nps.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ample.ample.nps.Common;
import com.ample.ample.nps.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import cz.msebera.android.httpclient.Header;

public class Message extends Fragment {
    private static final String URL_PRODUCTS = "your api service";
    List<Product2> messagelist;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    RecyclerView recyclerView;
    String sid;
    private final Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_message, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        sid = Common.getSavedUserData(getActivity(),"studentid");


        messagelist = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(true);
        pDialog.show();

        RequestParams requestParams = new RequestParams();
        //requestParams.put("section",sec); //First remove "Sec A" also get Data from other activity or fragment and add thoes data into requestparams.
        requestParams.put("studentid",sid);// First remove "Montesri1"also get Data from other activity or fragment and add thoes data into requestparams.

        asyncHttpClient.post(URL_PRODUCTS, requestParams, new AsyncHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String s = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("success");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject Product2 = jsonArray.getJSONObject(i);
                        messagelist.add(new Product2(
                                Product2.getInt("id"),
                                Product2.getString("date"),
                                Product2.getString("message")
                        ));
                    }

                    MessageAdapter adapter = new MessageAdapter(getContext(),messagelist);
                    recyclerView.setAdapter(adapter);
                    pDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }
}