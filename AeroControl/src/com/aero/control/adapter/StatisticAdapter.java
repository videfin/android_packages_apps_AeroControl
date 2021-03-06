package com.aero.control.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.aero.control.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ac on 18.09.13.
 */
public class StatisticAdapter extends ArrayAdapter<statisticInit> {

    private Context mContext;
    private int mLayoutResourceId;
    private List<statisticInit> mData = new ArrayList<statisticInit>();
    private final static Typeface mFont = Typeface.create("sans-serif-condensed", Typeface.NORMAL);
    private int mIndex = 0;
    private boolean[] mIsVisible;
    private String[] mTags = new String[0];

    // Color Code, redundant but necessary
    public static final String[] color_code = {
            "#009688", /* Turquoise */
            "#ff5722", /* Orange */
            "#8bc34a", /* Midnight Blue */
            "#03a9f4", /* Nephritis */
            "#e51c23", /* Monza */
            "#00bcd4", /* Wisteria */
            "#607d8b", /* Peter River */
            "#e91e63", /* Pomegrante */
    };

    public StatisticAdapter(Context context, int layoutResourceId, statisticInit[] data) {
        super(context, layoutResourceId, data);
        this.mLayoutResourceId = layoutResourceId;
        this.mContext = context;
        this.mIsVisible = new boolean[data.length];
        this.mTags = new String[data.length];

        for (statisticInit i : data) {
            mData.add(i);
        }

        int j, i = 0;
        for (boolean b : mIsVisible) {
            mIsVisible[i] = false;
            i++;
        }

        i = 0;
        j = 0;

        for (String c : mTags) {
            if (i == color_code.length)
                i = 0;

            mTags[j] = color_code[i];

            i++;
            j++;
        }
    }

    public static class Holder {
        TextView frequency;
        TextView timeInState;
        TextView percentage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        Holder holder;

        if (mIndex == color_code.length)
            mIndex = 0;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(mLayoutResourceId, parent, false);

            holder = new Holder();
            holder.frequency = (TextView) row.findViewById(R.id.frequency);
            holder.timeInState = (TextView) row.findViewById(R.id.timeInState);
            holder.percentage = (TextView) row.findViewById((R.id.percentage));

            holder.frequency.setTypeface(mFont);
            holder.timeInState.setTypeface(mFont);
            holder.percentage.setTypeface(mFont);

            holder.frequency.setTextColor(Color.parseColor(color_code[mIndex]));
            holder.timeInState.setTextColor(Color.parseColor(color_code[mIndex]));
            holder.percentage.setTextColor(Color.parseColor(color_code[mIndex]));

            mIndex++;

            row.setTag(holder);
        } else {
            holder = (Holder) row.getTag();
        }

        final statisticInit overview = mData.get(position);
        if(overview == null)
            return row;

        if (mData != null) {

            if(overview.mFrequency != null)
                holder.frequency.setText(overview.mFrequency);

            if(overview.mTimeInState != null)
                holder.timeInState.setText(overview.mTimeInState);

            if(overview.mPercentage != null)
                holder.percentage.setText(overview.mPercentage);

            if (mTags[position] != null) {
                holder.frequency.setTextColor(Color.parseColor(mTags[position]));
                holder.timeInState.setTextColor(Color.parseColor(mTags[position]));
                holder.percentage.setTextColor(Color.parseColor(mTags[position]));
            }

        } else {
            Log.e("Aero",
                    "No Data found for adapter.");
        }

        if (!mIsVisible[position]) {
            /* Small animation effect */
            int delay = (position * 50);
            Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.bottom_up);
            animation.setStartOffset(delay);
            row.setAnimation(animation);
            mIsVisible[position] = true;
        }

        return row;
    }

}
