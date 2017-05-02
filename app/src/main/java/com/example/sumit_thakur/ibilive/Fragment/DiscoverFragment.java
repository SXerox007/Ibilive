package com.example.sumit_thakur.ibilive.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sumit_thakur.ibilive.Adapter.RecyclerViewAdapter;
import com.example.sumit_thakur.ibilive.Constants.Constants;
import com.example.sumit_thakur.ibilive.Model.SetDataModel;
import com.example.sumit_thakur.ibilive.R;

import java.util.ArrayList;

/**
 * Created by SUMIT_THAKUR on 01/05/17.
 */

public class DiscoverFragment extends Fragment implements Constants {
    private ArrayList<SetDataModel> setDataModels;
    private RecyclerView recyclerView;
    private String mMode;
    private SetDataModel setDataModel;

    /**
     * Constructor
     *
     * @param mMode mode
     */
    public DiscoverFragment(final String mMode) {
        this.mMode = mMode;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.discover_fragment, container, false);
        init(view);
        if (mMode.equals(MY_NETWORK)) {
            ViewGroup.MarginLayoutParams marginLayoutParams =
                    (ViewGroup.MarginLayoutParams) recyclerView.getLayoutParams();
            marginLayoutParams.setMargins(CONST, CONST, CONST, 0);
            recyclerView.setLayoutParams(marginLayoutParams);

            recyclerView.setBackgroundResource(R.drawable.shape_recycler);
        }
        setDataModels.add(setDataModel);
        setDataModels.add(setDataModel);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), setDataModels, mMode);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        return view;
    }

    /**
     * initilization
     *
     * @param view view
     */
    private void init(final View view) {
        setDataModels = new ArrayList<>();
        setDataModel = new SetDataModel("Explore Paris", "Paris", "Jhon", "6d 2h left",
                "222 Reviews", "Lorem ipsum dolor sit amet, consectetur lorem ipsum"
                + "dolor sit amet, consectetur lorem ipsum.");
        recyclerView = (RecyclerView) view.findViewById(R.id.rvHome);
    }

    @Override
    public void extra() {

    }
}
