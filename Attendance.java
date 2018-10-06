package com.ample.ample.nps.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ample.ample.nps.R;

public class Attendance extends Fragment {

    TextView user_name,sect,school;
    String name, pass, section, schoolname;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attendance, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*user_name= (TextView) view.findViewById(R.id.user_name);
        name = Common.getSavedUserData(getContext(),"name");

        user_name.setText(name);*/
    }
}
