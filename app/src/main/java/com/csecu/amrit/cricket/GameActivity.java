package com.csecu.amrit.cricket;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvTeamInnings, tvScore, tvTarget, tvOvers, tvRemaining;
    TextView tvFirstName, tvFirstRun, tvFirstBall;
    TextView tvSecondName, tvSecondRun, tvSecondBall;
    TextView tvBowlerName, tvBowlerOvers, tvBowlerWicket;
    TextView tvCommentary;
    Button btHit;

    int total = 0, wickets = 0, oversPlayed = 0, balls = 0;
    int firstRuns = 0, firstBalls = 0;
    int secondRuns = 0, secondBalls = 0;

    final int OVERS_TOTAL = 10;

    // For practise
    int batsmanSequence = 0, bowlerSequence = 0;
    String firstBatsman = null, secondBatsman = null, currentBatsman = null;
    String currentBowler = null;
    ArrayList<String> batsmen = new ArrayList<>();
    ArrayList<String> bowler = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        linkAll();

        // For Practise
        setBatsmanAndBowler();

        startMatch();

        btHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int runs = generateRuns();
                checkRuns(runs);
            }
        });
    }

    private void checkRuns(int runs) {
        if (runs == 5) {
            wickets++;
            getNewBatsman();
        } else if (runs == 3 || runs == 1) {
            total = total + runs;
            addRunsToBatsman(runs);
            changeBatsman();
        } else {
            total = total + runs;
            addRunsToBatsman(runs);
        }
        setCommentary(runs);
        changeBowlerStatus();
        setAllValues();
        checkWiningCondition();
    }

    private void setAllValues() {
        tvScore.setText("Score: " + total + " / " + wickets);
        tvOvers.setText("Overs: " + oversPlayed + " / " + balls);

        if (currentBatsman == firstBatsman) {
            tvFirstName.setText(firstBatsman);
            tvFirstRun.setText(String.valueOf(firstRuns));
            tvFirstBall.setText(String.valueOf(firstBalls));

            tvSecondName.setText(secondBatsman);
            tvSecondRun.setText(String.valueOf(secondRuns));
            tvSecondBall.setText(String.valueOf(secondBalls));
        } else {
            tvFirstName.setText(secondBatsman);
            tvFirstRun.setText(String.valueOf(secondRuns));
            tvFirstBall.setText(String.valueOf(secondBalls));

            tvSecondName.setText(firstBatsman);
            tvSecondRun.setText(String.valueOf(firstRuns));
            tvSecondBall.setText(String.valueOf(firstBalls));
        }

        // add bowler's data
    }

    private void addRunsToBatsman(int runs) {
        if (currentBatsman == firstBatsman) {
            firstRuns = firstRuns + runs;
            firstBalls++;
        } else {
            secondRuns = secondRuns + runs;
            secondBalls++;
        }
    }

    private void changeBatsman() {
        if (currentBatsman == firstBatsman) {
            currentBatsman = secondBatsman;
        } else {
            currentBatsman = firstBatsman;
        }
    }

    private void getNewBatsman() {
    }

    private void checkWiningCondition() {
    }

    private void changeBowlerStatus() {
        balls++;
        if (balls == 6) {
            oversPlayed++;
            balls = 0;
            changeBatsman();
        }
    }

    private void setCommentary(int runs) {
        tvCommentary.setText("" + runs);
    }

    private int generateRuns() {
        Random random = new Random();
        int runs = random.nextInt(7);
        return runs;
    }

    private void setBatsmanAndBowler() {
        batsmen.add("Imrul Kayes");
        batsmen.add("Anamul Haque");
        batsmen.add("Soumya Sarkar");
        batsmen.add("Mohammad Mithun");
        batsmen.add("Mosaddek Hossain");
        batsmen.add("Sabbir Rahman");
        batsmen.add("Shuvagata Hom");
        batsmen.add("Abu Jayed");
        batsmen.add("Mahmudul Hasan");
        batsmen.add("Kamrul Islam");
        batsmen.add("Shafiul Islam");

        bowler.add("Shafiul Islam");
        bowler.add("Kamrul Islam");
        bowler.add("Mahmudul Hasan");
        bowler.add("Abu Jayed");
        bowler.add("Shuvagata Hom");
    }

    private void startMatch() {
        firstInnings();
    }

    private void firstInnings() {
        setOpeningScorecard();
        getOpeningBatsman();
        getOpeningBowler();
    }

    private void getOpeningBowler() {
        bowlerSequence = 0;
        currentBowler = bowler.get(bowlerSequence);
        bowlerSequence++;
        tvBowlerName.setText(currentBowler);
    }

    private void setOpeningScorecard() {
        total = 0;
        wickets = 0;
        oversPlayed = 0;
        balls = 0;
        firstRuns = 0;
        firstBalls = 0;
        secondRuns = 0;
        secondBalls = 0;

        tvScore.setText("Score: " + total + " / " + wickets);
        tvOvers.setText("Overs: " + oversPlayed + " / " + balls);
        tvFirstRun.setText(String.valueOf(firstRuns));
        tvFirstBall.setText(String.valueOf(firstBalls));
        tvSecondRun.setText(String.valueOf(secondRuns));
        tvSecondBall.setText(String.valueOf(secondBalls));
    }

    private void getOpeningBatsman() {
        batsmanSequence = 0;
        firstBatsman = batsmen.get(batsmanSequence);
        batsmanSequence++;
        secondBatsman = batsmen.get(batsmanSequence);
        batsmanSequence++;
        tvFirstName.setText(firstBatsman);
        tvSecondName.setText(secondBatsman);
        currentBatsman = firstBatsman;
    }

    private void linkAll() {
        tvTeamInnings = findViewById(R.id.tv_team_innings);
        tvScore = findViewById(R.id.tv_score);
        tvTarget = findViewById(R.id.tv_target);
        tvOvers = findViewById(R.id.tv_overs);
        tvRemaining = findViewById(R.id.tv_remaining);
        tvFirstName = findViewById(R.id.tv_first_name);
        tvFirstRun = findViewById(R.id.tv_first_run);
        tvFirstBall = findViewById(R.id.tv_first_ball);
        tvSecondName = findViewById(R.id.tv_second_name);
        tvSecondRun = findViewById(R.id.tv_second_run);
        tvSecondBall = findViewById(R.id.tv_second_ball);
        tvBowlerName = findViewById(R.id.tv_bowler_name);
        tvBowlerOvers = findViewById(R.id.tv_bowler_overs);
        tvBowlerWicket = findViewById(R.id.tv_bowler_wicket);
        tvCommentary = findViewById(R.id.tv_commentary);
        btHit = findViewById(R.id.bt_hit);
    }
}
