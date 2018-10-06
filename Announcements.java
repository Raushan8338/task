package com.ample.ample.nps.Fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ample.ample.nps.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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


public class Announcements extends Fragment {
    private static final String URL_PRODUCTS = "https://raushankumar8338.000webhostapp.com/store/Announcement/Api.php";
    List<AnnouncementsProduct> announcements;

    RecyclerView recyclerView;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_announcements, container, false);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        announcements = new ArrayList<>();
        loadProduct();
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

       asyncHttpClient.post(URL_PRODUCTS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String s = new String(responseBody);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                /*Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();*/
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadProduct() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject AnnouncementsProduct = array.getJSONObject(i);


                                announcements.add(new AnnouncementsProduct(
                                        AnnouncementsProduct.getInt("id"),
                                        AnnouncementsProduct.getString("date"),
                                        AnnouncementsProduct.getString("holidays")
                                ));
                            }

                            AnnouncementsAdapter adapter = new AnnouncementsAdapter(getContext(),announcements);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }


}
