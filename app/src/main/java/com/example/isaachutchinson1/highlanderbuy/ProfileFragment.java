package com.example.isaachutchinson1.highlanderbuy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * @Author: Isaac Hutchinson
 * A simple {@link Fragment} subclass.
 * Some features used from: https://www.youtube.com/watch?v=ICDHlQjfiyc
 */
public class ProfileFragment extends Fragment {
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private String current_user;
    private TextView mUsername;
    private ImageView iONE;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewTwo;
    private RecyclerView.Adapter adapterTwo;

    private List<ListItem> listItems;
    private List<ListItem> listItemsTwo;

    private View mainView;

    public ProfileFragment() {

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
        mainView = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        mUsername = mainView.findViewById(R.id.profile_username);

        current_user = mAuth.getCurrentUser().getUid();
        myRef = FirebaseDatabase.getInstance().getReference().child("Users").child(current_user);
        myRef.keepSynced(true);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String testUSer = dataSnapshot.child("Username").getValue(String.class);
                mUsername.setText(testUSer);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

       /* iONE = (ImageView) mainView.findViewById(R.id.profile_AL_IONE);
        Glide.with(this).load("https://firebasestorage.googleapis.com/v0/b/highlanderbuy-76ea4" +
                ".appspot.com/o/User_Profile_Photos%2Fbe18b3a951a04e964e19863a307c580d.png?alt=media&token=d65d" +
                "1e89-4653-4dd6-b0d9-204960e00a65").into(iONE);*/

        recyclerView = (RecyclerView) mainView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(mainView.getContext(), LinearLayoutManager.HORIZONTAL, true));

        listItems = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ListItem listItem = new ListItem(
                    "Future Active Listings Item: " + (i + 1),
                    "Future Description of Active Listings item"
            );
            listItems.add(listItem);
        }
        adapter = new ListingAdaptor(listItems, mainView.getContext());

        recyclerView.setAdapter(adapter);

        recyclerViewTwo = (RecyclerView) mainView.findViewById(R.id.recyclerViewTwo);
        recyclerViewTwo.setHasFixedSize(true);

        recyclerViewTwo.setLayoutManager(new LinearLayoutManager(mainView.getContext(), LinearLayoutManager.HORIZONTAL, true));

        listItemsTwo = new ArrayList<>();

        for (int i = 0; i <= 10; i++) {
            ListItem listItem = new ListItem(
                    "Future Sold Listing Item:  " + (i + 1),
                    "Future Description of Active Listings Item"
            );
            listItemsTwo.add(listItem);
        }
        adapterTwo = new ListingAdaptor(listItemsTwo, mainView.getContext());

        recyclerViewTwo.setAdapter(adapterTwo);


        // Inflate the layout for this fragment
        return mainView;
    }

}
