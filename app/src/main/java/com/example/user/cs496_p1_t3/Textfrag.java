package com.example.user.cs496_p1_t3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by user on 2017-12-27.
 */

public class Textfrag extends Fragment{

    public Textfrag(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedlnstanceState){
        View view = null;
        view = inflater.inflate(R.layout.textfrag, container, false);

        return view;
    }
}
