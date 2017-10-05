package com.example.isaachutchinson1.highlanderbuy;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Isaac Hutchinson
 * A simple {@link Fragment} subclass.
 *  Some features used from: https://www.youtube.com/watch?v=ICDHlQjfiyc
 */
public class ListingsFragment extends Fragment {


    private View mainView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;
    private Button newListingsBtn;
    private Button sortBtn;

    public ListingsFragment() {
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


        mainView = inflater.inflate(R.layout.fragment_listings, container, false);
        newListingsBtn = (Button) mainView.findViewById(R.id.listings_newListings_btn);
        sortBtn = (Button) mainView.findViewById(R.id.listings_sort_btn);

        newListingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listingsToNewListingActivity();
            }
        });
        recyclerView = (RecyclerView) mainView.findViewById(R.id.listings_recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(mainView.getContext()));

        listItems = new ArrayList<>();

        for (int i = 0; i <= 30; i++) {
            ListItem listItem = new ListItem(
                    "Market Listing Item:  " + (i + 1),
                    "Future Description for Market Listing."
            );
            listItems.add(listItem);
        }
        adapter = new ListingAdaptor(listItems, mainView.getContext());

        recyclerView.setAdapter(adapter);
        return mainView;
    }

    /**
     * The listingsToNewListingActivity changes the current activity (View) to the New Listings
     * activity.
     */
    private void listingsToNewListingActivity() {
        Intent startIntent = new Intent(getActivity(), AddNewListing.class);
        startActivity(startIntent);
    }

}
