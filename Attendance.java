package com.ample.ample.nps.Fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class Attendance extends Fragment {
    private static final String url_product="https://raushankumar8338.000webhostapp.com/store/Attendance/AttendanceApi.php";
    List<AttendanceProduct>attendance;
    AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        attendance=new ArrayList<>();
        loadProduct();
        loadData();
        /*user_name= (TextView) view.findViewById(R.id.user_name);
        name = Common.getSavedUserData(getContext(),"name");

        user_name.setText(name);*/
    }

    private void loadData() {
        final SweetAlertDialog pDialog = new SweetAlertDialog(getContext(),SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(getResources().getColor(R.color.colorPrimary));
        pDialog.setTitle("loading....");
        pDialog.setCancelable(false);
        pDialog.show();
        RequestParams requestParams=new RequestParams();
        asyncHttpClient.post(url_product, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String s=new String(responseBody);
                pDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                pDialog.dismiss();
            }
        });

    }
    private void loadProduct() {
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_product, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onResponse(String response) {
                try {

                    JSONArray array = new JSONArray(response);


                    for (int i = 0; i < array.length(); i++) {

                        JSONObject AttendanceProduct = array.getJSONObject(i);


                        attendance.add(new AttendanceProduct(
                                AttendanceProduct.getInt("id"),
                                AttendanceProduct.getString("month"),
                                AttendanceProduct.getString("present"),
                                AttendanceProduct.getString("allday")

                        ));
                    }

                    AttendanceAdapter adapter = new AttendanceAdapter(getContext(),attendance);
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
