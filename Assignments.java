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

public class Assignments extends Fragment {
    private static final String URL_PRODUCTS = "http://www.amplesoftech.co.in/app/Assignments/Api.php";
    List<AssignmentsProduct> assignments;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    RecyclerView recyclerView;
    String sec,sname;
    private final Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_assignments, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        sec = Common.getSavedUserData(getActivity(),"section");
        sname = Common.getSavedUserData(getActivity(),"schoolname");


        assignments = new ArrayList<>();
        loadData();
    }

    private void loadData() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(true);
        pDialog.show();

        RequestParams requestParams = new RequestParams();
        requestParams.put("section",sec); //First remove "Sec A" also get Data from other activity or fragment and add thoes data into requestparams.
        requestParams.put("schoolname",sname);// First remove "Montesri1"also get Data from other activity or fragment and add thoes data into requestparams.

        asyncHttpClient.post(URL_PRODUCTS, requestParams, new AsyncHttpResponseHandler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String s = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("success");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject AssignmentsProduct = jsonArray.getJSONObject(i);
                        assignments.add(new AssignmentsProduct(
                                AssignmentsProduct.getInt("id"),
                                AssignmentsProduct.getString("language"),
                                AssignmentsProduct.getString("date"),
                                AssignmentsProduct.getString("message")
                        ));
                    }

                    AssigmentsAdapter adapter = new AssigmentsAdapter(getContext(),assignments);
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










