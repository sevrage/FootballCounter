package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public enum Team {
        A,
        B
    }
    public enum Card {
        RED,
        YELLOW
    }

    int scoreTeamA = 0;
    int scoreTeamB = 0;

    int redCardsTeamA = 0;
    int redCardsTeamB = 0;

    int yellowCardsTeamA = 0;
    int yellowCardsTeamB = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        Team team = Team.valueOf(view.getTag().toString());
        switch (team) {
            case A:
                scoreTeamA += 1;
                displayScoreForTeamX(Team.A, scoreTeamA);
                break;
            case B:
                scoreTeamB += 1;
                displayScoreForTeamX(Team.B, scoreTeamB);
                break;
        }
    }

    public void redForTeamX(View view) {
        if (maximumRedCards()) {
            return;
        }

        Team team = Team.valueOf(view.getTag().toString());
        switch (team) {
            case A:
                if (redCardsTeamA < 5) {
                    redCardsTeamA += 1;
                    displayCardsForTeamX(Team.A, Card.RED, redCardsTeamA);
                }
                break;
            case B:
                if (redCardsTeamB < 5) {
                    redCardsTeamB += 1;
                    displayCardsForTeamX(Team.B, Card.RED, redCardsTeamB);
                }
                break;
        }
    }

    public void yellowForTeamX(View view) {
        if (maximumRedCards()) {
            return;
        }

        Team team = Team.valueOf(view.getTag().toString());
        switch (team) {
            case A:
                yellowCardsTeamA += 1;
                displayCardsForTeamX(Team.A, Card.YELLOW, yellowCardsTeamA);
                break;
            case B:
                yellowCardsTeamB += 1;
                displayCardsForTeamX(Team.B, Card.YELLOW, yellowCardsTeamB);
                break;
        }
    }

    public void displayScoreForTeamX(Team team, int score) {
        int viewId = 0;
        switch (team) {
            case A:
                viewId = R.id.team_a_score;
                break;
            case B:
                viewId = R.id.team_b_score;
                break;
        }

        TextView scoreView = findViewById(viewId);
        scoreView.setText(String.valueOf(score));
    }

    public void displayCardsForTeamX(Team team, Card card, int score) {
        int viewId = 0;
        switch (team) {
            case A: {
                switch (card) {
                    case RED: viewId = R.id.team_a_red; break;
                    case YELLOW: viewId = R.id.team_a_yellow; break;
                }
                break;
            }
            case B: {
                switch (card) {
                    case RED: viewId = R.id.team_b_red; break;
                    case YELLOW: viewId = R.id.team_b_yellow; break;
                }
                break;
            }
        }

        TextView scoreView = findViewById(viewId);
        scoreView.setText(String.valueOf(score));
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
        displayScoreForTeamX(Team.A, scoreTeamA);
        displayScoreForTeamX(Team.B, scoreTeamB);

        displayCardsForTeamX(Team.A, Card.RED, redCardsTeamA);
        displayCardsForTeamX(Team.B, Card.RED, redCardsTeamB);

        displayCardsForTeamX(Team.A, Card.YELLOW, yellowCardsTeamA);
        displayCardsForTeamX(Team.B, Card.YELLOW, yellowCardsTeamB);
    }

    public  boolean maximumRedCards(){
        return redCardsTeamA == 5 || redCardsTeamB == 5;
    }

}
