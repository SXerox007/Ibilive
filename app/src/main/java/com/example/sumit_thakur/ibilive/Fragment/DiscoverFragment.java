package com.example.sumit_thakur.ibilive.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sumit_thakur.ibilive.Adapter.RecyclerViewAdapter;
import com.example.sumit_thakur.ibilive.Constants.Constants;
import com.example.sumit_thakur.ibilive.Model.SetDataModel;
import com.example.sumit_thakur.ibilive.R;

import java.util.ArrayList;

/**
 * Common Fragment to all
 */
public class DiscoverFragment extends Fragment implements Constants {
    private ArrayList<SetDataModel> setDataModels;
    private RecyclerView recyclerView;
    private String mMode;
    private SetDataModel setDataModel;
    private Button btnPosts, btnMap, btnNetwork;

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
            visibilitySet();
            recyclerViewDataAdd();
        } else {
            if (mMode.equals(REQUEST)) {
                Log.e("error", "sucess1");
                recyclerViewDataAdd();
            } else {
                visibilitySet();
                recyclerViewDataAdd();
            }
        }

        return view;
    }

    /**
     * visibility Set
     */
    private void visibilitySet() {
        btnPosts.setVisibility(View.GONE);
        btnNetwork.setVisibility(View.GONE);
        btnMap.setVisibility(View.GONE);
    }


    /**
     * recycler view data add
     */
    private void recyclerViewDataAdd() {
        setDataModels.add(setDataModel);
        setDataModels.add(setDataModel);
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(getContext(), setDataModels, mMode);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
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
        btnPosts = (Button) view.findViewById(R.id.btnPost);
        btnMap = (Button) view.findViewById(R.id.btnMap);
        btnNetwork = (Button) view.findViewById(R.id.btnNetwork);
    }

    @Override
    public void extra() {

    }
}
