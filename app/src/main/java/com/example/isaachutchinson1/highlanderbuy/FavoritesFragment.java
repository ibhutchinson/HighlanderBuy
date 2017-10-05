package com.example.isaachutchinson1.highlanderbuy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Isaac Hutchinson
 * A simple {@link Fragment} subclass.
 * Some features used from: https://www.youtube.com/watch?v=ICDHlQjfiyc
 */
public class FavoritesFragment extends Fragment {
    private View mainView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;


    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * The onCreateView method is the main control for the activity and generates the information and
     * content to the activity.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = (RecyclerView) mainView.findViewById(R.id.favorites_recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(mainView.getContext()));

        listItems = new ArrayList<>();

        for (int i = 0; i <= 30; i++) {
            ListItem listItem = new ListItem(
                    "Favorites Item:  " + (i + 1),
                    "Feature description of a item. "
            );
            listItems.add(listItem);
        }
        adapter = new ListingAdaptor(listItems, mainView.getContext());

        recyclerView.setAdapter(adapter);
        return mainView;
    }

}
