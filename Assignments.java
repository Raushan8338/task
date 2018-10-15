package com.ample.ample.nps.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ample.ample.nps.Common;
import com.ample.ample.nps.Login;
import com.ample.ample.nps.Mainpage;
import com.ample.ample.nps.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Assignments extends Fragment {
    private static final String URL_PRODUCTS = "http://www.amplesoftech.co.in/app/Assignments/Api.php";
    List<AssignmentsProduct> assignments;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
    RecyclerView recyclerView;
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

        assignments = new ArrayList<>();
        // loadProduct();
        loadData();
    }

    private void loadData() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(
                getContext(), SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitleText("Loading...");
        pDialog.setCancelable(false);
        pDialog.show();

        RequestParams requestParams = new RequestParams();
        requestParams.put("section",Common.getSavedUserData(getContext(),"section")); //First remove "Sec A" also get Data from other activity or fragment and add thoes data into requestparams.
        requestParams.put("schoolname",Common.getSavedUserData(getContext(),"schoolname"));
        // First remove "Montesri1"also get Data from other activity or fragment and add thoes data into requestparams.

        asyncHttpClient.post(URL_PRODUCTS, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String s = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("success");
                    for (int i = 0;i<jsonArray.length();i++)
                    {
                        jsonObject = jsonArray.getJSONObject(i);

                        String id = jsonObject.getString("id");
                        String language = jsonObject.getString("language");
                        String date = jsonObject.getString("date");
                        String message = jsonObject.getString("message");


                    }
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








/*

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

import com.ample.ample.nps.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class Assignments extends Fragment {
    private static final String URL_PRODUCTS = "http://www.amplesoftech.co.in/app/Assignments/Api2.php";
    List<AssignmentsProduct> assignments;
    AsyncHttpClient asyncHttpClient = new AsyncHttpClient();


    RecyclerView recyclerView;
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

        assignments = new ArrayList<>();
        loadProduct();
        loadData();
        //loadData();
    }
    private void loadData() {
       */
/* final SweetAlertDialog pDialog = new SweetAlertDialog(
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
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.getString("success").equalsIgnoreCase("1")){
                        pDialog.dismiss();
                        *//*
*/
/*Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show()*//*
*/
/*;



                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("language");
                        String pass = jsonObject.getString("date");
                        String message = jsonObject.getString("message");



                    }else if (jsonObject.getString("success").equalsIgnoreCase("0")){
                        pDialog.dismiss();
                        *//*
*/
/*Toast.makeText(Login.this, ""+jsonObject.getString("message"), Toast.LENGTH_SHORT).show();*//*
*/
/*
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
                *//*
*/
/*Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();*//*
*/
/*
            }
        });
*//*

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
                */
/*Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();*//*

            }
        });
    }


    private void doTheAutoRefresh() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                doRefreshingStuff(); // this is where you put your refresh code
                doTheAutoRefresh();
            }
        }, 300);
    }

    private void doRefreshingStuff() {
    }

    private void loadProduct() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray array = new JSONArray(response);


                            for (int i = 0; i < array.length(); i++) {

                                JSONObject AssignmentsProduct = array.getJSONObject(i);


                                assignments.add(new AssignmentsProduct(
                                        AssignmentsProduct.getInt("id"),
                                        AssignmentsProduct.getString("language"),
                                        AssignmentsProduct.getString("date"),

                                        AssignmentsProduct.getString("message")
                                ));
                            }

                            AssigmentsAdapter adapter = new AssigmentsAdapter(getContext(),assignments);
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
*/



 /*Common.saveUserData(getContext(),"id",id);
         Common.saveUserData(getContext(),"name",language);
         Common.saveUserData(getContext(),"pass",date);
         Common.saveUserData(getContext(),"section",message);
*/