package com.mac.j.pathoflowestcost;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @BindView(R.id.gridEditText) EditText gridEditText;
    @BindView(R.id.resultEditText) EditText resultEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onButtonClick() {
        Log.d(TAG, "onButtonClick: ");
        try {
            Grid grid = new Grid(gridEditText.getText().toString());
            PathOfLowestCost polc = new PathOfLowestCost(50, 1, 5, 10, 100);
            PathOfLowestCost.Solution solution = polc.solve(grid);

            StringBuilder solutionString = new StringBuilder();
            solutionString.append(solution.isAllColumnsTraversed ? "yes" : "no");
            solutionString.append("\n");
            solutionString.append(solution.totalCost);
            solutionString.append("\n");

            for (Integer i : solution.pathRows) {
                solutionString.append(i);
                solutionString.append(" ");
            }

            solutionString.append("\n");

            resultEditText.setText(solutionString.toString());
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
