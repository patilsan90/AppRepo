package com.mall.logic.myapp.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mall.logic.myapp.R;
import com.mall.logic.myapp.interfaces.IMall;

/**
 * A simple {@link Fragment} subclass.
 */
public class Offers extends Fragment implements IMall {


    public Offers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_offers, container, false);
    }

    @Override
    public boolean isUpdated() {
        return false;
    }
}
