package com.sourcey.akiba;

/**
 * Created by jayjay on 17/02/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class ChallengesActivity extends AppCompatActivity {

    private GridView gridView;
    private View btnGo;
    private ArrayList<String> selectedStrings;
    private static final String[] numbers = new String[]{
            "10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "150",
            "200"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);

        gridView = (GridView) findViewById(R.id.grid);
        btnGo = findViewById(R.id.button);

        selectedStrings = new ArrayList<>();

        final ChallengeViewAdapter adapter = new ChallengeViewAdapter(numbers, this);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                int selectedIndex = adapter.selectedPositions.indexOf(position);
                if (selectedIndex > -1) {
                    adapter.selectedPositions.remove(selectedIndex);
                    ((ChallengeItemView) v).display(false);
                    selectedStrings.remove((String) parent.getItemAtPosition(position));
                } else {
                    adapter.selectedPositions.add(position);
                    ((ChallengeItemView) v).display(true);
                    selectedStrings.add((String) parent.getItemAtPosition(position));
                }
            }
        });

        //set listener for Button event
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChallengesActivity.this, SelectedChallengesActivity.class);
                intent.putStringArrayListExtra("SELECTED_LETTER", selectedStrings);
                startActivity(intent);
            }
        });
    }
}