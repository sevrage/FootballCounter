package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TEAM_A = "A";
    private static final String TEAM_B = "B";
    private static final String RED_CARD = "RED";
    private static final String YELLOW_CARD = "YELLOW";

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    int redCardsTeamA = 0;
    int redCardsTeamB = 0;

    int yellowCardsTeamA = 0;
    int yellowCardsTeamB = 0;

    TextView scoreView_A;
    TextView scoreView_B;
    TextView redCardView_A;
    TextView redCardView_B;
    TextView yellowCardView_A;
    TextView yellowCardView_B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreView_A = findViewById(R.id.team_a_score);
        scoreView_B = findViewById(R.id.team_b_score);

        redCardView_A = findViewById(R.id.team_a_red);
        redCardView_B = findViewById(R.id.team_b_red);

        yellowCardView_A = findViewById(R.id.team_a_yellow);
        yellowCardView_B = findViewById(R.id.team_b_yellow);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putInt("ScoreA", scoreTeamA);
        savedInstanceState.putInt("ScoreB", scoreTeamB);

        savedInstanceState.putInt("RedA", redCardsTeamA);
        savedInstanceState.putInt("RedB", redCardsTeamB);

        savedInstanceState.putInt("YellowA", yellowCardsTeamA);
        savedInstanceState.putInt("YellowB", yellowCardsTeamB);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        scoreTeamA = savedInstanceState.getInt("ScoreA");
        scoreTeamB = savedInstanceState.getInt("ScoreB");

        redCardsTeamA = savedInstanceState.getInt("RedA");
        redCardsTeamB = savedInstanceState.getInt("RedB");

        yellowCardsTeamA = savedInstanceState.getInt("YellowA");
        yellowCardsTeamB = savedInstanceState.getInt("YellowB");

        displayStats();
    }

    public void scoreForTeamX(View view) {
        if (maximumRedCards()) {
            return;
        }

        switch (view.getTag().toString()) {
            case TEAM_A:
                scoreTeamA += 1;
                displayScoreForTeamX(TEAM_A, scoreTeamA);
                break;
            case TEAM_B:
                scoreTeamB += 1;
                displayScoreForTeamX(TEAM_B, scoreTeamB);
                break;
        }
    }

    public void redForTeamX(View view) {
        if (maximumRedCards()) {
            return;
        }
        switch (view.getTag().toString()) {
            case TEAM_A:
                redCardsTeamA += 1;
                displayCardsForTeamX(TEAM_A, RED_CARD, redCardsTeamA);
                break;
            case TEAM_B:
                redCardsTeamB += 1;
                displayCardsForTeamX(TEAM_B, RED_CARD, redCardsTeamB);
                break;
        }
    }

    public void yellowForTeamX(View view) {
        if (maximumRedCards()) {
            return;
        }

        switch (view.getTag().toString()) {
            case TEAM_A:
                yellowCardsTeamA += 1;
                displayCardsForTeamX(TEAM_A, YELLOW_CARD, yellowCardsTeamA);
                break;
            case TEAM_B:
                yellowCardsTeamB += 1;
                displayCardsForTeamX(TEAM_B, YELLOW_CARD, yellowCardsTeamB);
                break;
        }
    }

    public void displayScoreForTeamX(String team, int score) {
        switch (team) {
            case TEAM_A:
                scoreView_A.setText(String.valueOf(score));
                break;
            case TEAM_B:
                scoreView_B.setText(String.valueOf(score));
                break;
        }
    }

    public void displayCardsForTeamX(String team, String card, int score) {
        switch (team) {
            case TEAM_A: {
                switch (card) {
                    case RED_CARD:
                        redCardView_A.setText(String.valueOf(score));
                        break;
                    case YELLOW_CARD:
                        yellowCardView_A.setText(String.valueOf(score));
                        break;
                }
                break;
            }
            case TEAM_B: {
                switch (card) {
                    case RED_CARD:
                        redCardView_B.setText(String.valueOf(score));
                        break;
                    case YELLOW_CARD:
                        yellowCardView_B.setText(String.valueOf(score));
                        break;
                }
                break;
            }
        }
    }

    public void resetStats(View view) {
        scoreTeamA = 0;
        scoreTeamB = 0;

        redCardsTeamA = 0;
        redCardsTeamB = 0;

        yellowCardsTeamA = 0;
        yellowCardsTeamB = 0;

        displayStats();
    }

    public void displayStats() {
        displayScoreForTeamX(TEAM_A, scoreTeamA);
        displayScoreForTeamX(TEAM_B, scoreTeamB);

        displayCardsForTeamX(TEAM_A, RED_CARD, redCardsTeamA);
        displayCardsForTeamX(TEAM_B, RED_CARD, redCardsTeamB);

        displayCardsForTeamX(TEAM_A, YELLOW_CARD, yellowCardsTeamA);
        displayCardsForTeamX(TEAM_B, YELLOW_CARD, yellowCardsTeamB);
    }

    public  boolean maximumRedCards(){
        return redCardsTeamA == 5 || redCardsTeamB == 5;
    }

}
