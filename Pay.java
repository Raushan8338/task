package com.ample.ample.nps.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


public class Pay extends Fragment {
    private static final String URL_PRODUCTS="http://www.amplesoftech.co.in/app/Announcement/Api2.php";
   List<PayProduct> pay;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    RecyclerView recyclerView;
    String sname;
    private final Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        sname = Common.getSavedUserData(getActivity(),"schoolname");


        pay = new ArrayList<PayProduct>();
        loaddata();
    }

    private void loaddata() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(
                getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        RequestParams requestParams = new RequestParams();
        requestParams.put("schoolname",sname);// First remove "Montesri1"also get Data from other activity or fragment and add thoes data into requestparams.


        asyncHttpClient.post(URL_PRODUCTS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("success");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject PayProduct = jsonArray.getJSONObject(i);
                        pay.add(new PayProduct(
                                PayProduct.getInt("id"),
                                PayProduct.getString("sem"),
                                PayProduct.getString("yearfee"),
                                PayProduct.getString("feedeposit"),
                                PayProduct.getString("letfine"),
                                PayProduct.getString("date"),
                                PayProduct.getString("due")

                        ));
                    }

                    PayAdapter adapter = new PayAdapter(getContext(),pay);
                    recyclerView.setAdapter(adapter);
                    pDialog.cancel();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                /*Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();*/
            }
        });

    }
}

