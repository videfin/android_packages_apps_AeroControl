package com.aero.control.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.aero.control.AeroActivity;

/**
 * Created by Alexander Christ on 12.06.15.
 * Allows the hold various generic stuff for all fragments.
 */
public class PlaceHolderFragment extends PreferenceFragment {

    private String mTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Tuneup the layout a bit;
        View v = super.onCreateView(inflater, container, savedInstanceState);
        if(v != null) {
            ListView lv = (ListView) v.findViewById(android.R.id.list);
            lv.setPadding(10, 0, 10, 0);
            lv.setDivider(null);
            lv.setDividerHeight(10);
            // Set a nice on-touch ripple effect;
            lv.setDrawSelectorOnTop(true);
        }
        return v;
    }

    /**
     * Sets the title of the action bar
     * @param title String
     */
    public final void setTitle(String title) {
        ((AeroActivity)getActivity()).setActionBarTitle(title);
        mTitle = title;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Re-set our title if resumed;
        if (mTitle != null) {
            setTitle(mTitle);
        }
    }

}
