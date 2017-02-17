package com.sourcey.akiba;

/**
 * Created by jayjay on 17/02/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

public class ChallengeItemView extends FrameLayout {

    private TextView textView;

    public ChallengeItemView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.activity_challenges_view, this);
        textView = (TextView) getRootView().findViewById(R.id.text);
    }

    public void display(String text, boolean isSelected) {
        textView.setText(text);
        display(isSelected);
    }

    public void display(boolean isSelected) {
        textView.setBackgroundResource(isSelected ? R.drawable.green_square : R.drawable.gray_square);
    }
}
