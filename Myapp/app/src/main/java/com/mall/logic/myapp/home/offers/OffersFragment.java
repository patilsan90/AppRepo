package com.mall.logic.myapp.home.offers;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.mall.logic.myapp.AppState;
import com.mall.logic.myapp.R;
import com.mall.logic.myapp.home.mycart.MyCartAdapter;
import com.mall.logic.myapp.interfaces.IMall;

/**
 * A simple {@link Fragment} subclass.
 */
public class OffersFragment extends Fragment implements IMall {


    public OffersFragment() {
        // Required empty public constructor
    }

    Button locate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_offers, container, false);
        ListView listView = (ListView) view.findViewById(R.id.offers_list);

        Log.i("Generic info "," Activity onCreate OffersFragment ....");
        listView.setAdapter(new OffersAdapter(this.getActivity()));

        if(AppState.isMallSelected == false)
        {
            locate = (Button) view.findViewById(R.id.locate_categories);
            locate.setVisibility(View.INVISIBLE);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(AppState.isMallSelected == true)
            locate.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean isUpdated() {
        return false;
    }
}
