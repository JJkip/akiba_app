package com.sourcey.akiba;

/**
 * Created by jayjay on 17/02/2017.
 */
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChallengeViewAdapter extends BaseAdapter {

    private Activity activity;
    private String[] strings;
    public List selectedPositions;

    public ChallengeViewAdapter(String[] strings, Activity activity) {
        this.strings = strings;
        this.activity = activity;
        selectedPositions = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return strings[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChallengeItemView customView = (convertView == null) ? new ChallengeItemView(activity) : (ChallengeItemView) convertView;
        customView.display(strings[position], selectedPositions.contains(position));

        return customView;
    }
}