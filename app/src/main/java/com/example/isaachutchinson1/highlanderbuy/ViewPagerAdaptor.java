package com.example.isaachutchinson1.highlanderbuy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by isaachutchinson1 on 9/30/17.
 *
 *
 */

class ViewPagerAdaptor extends FragmentPagerAdapter {

    public ViewPagerAdaptor(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ListingsFragment listingsFragment = new ListingsFragment();
                return listingsFragment;
            case 1:
                FavoritesFragment favoritesFragment = new FavoritesFragment();
                return favoritesFragment;
            case 2:
                MessagesFragment messagesFragment = new MessagesFragment();
                return messagesFragment;
            case 3:
                ProfileFragment profileFragment = new ProfileFragment();
                return profileFragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Listings";
            case 1:
                return "Favorites";
            case 2:
                return "Messages";
            case 3:
                return "Profile";
            default:
                return null;
        }
    }
}
