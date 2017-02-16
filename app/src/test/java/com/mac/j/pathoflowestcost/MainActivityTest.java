package com.mac.j.pathoflowestcost;

import android.widget.Button;
import android.widget.EditText;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class MainActivityTest {
    private MainActivity mainActivity;
    private EditText gridEditText;
    private EditText resultEditText;
    private Button button;

    @Before
    public void setup() {
        mainActivity = Robolectric.setupActivity(MainActivity.class);

        gridEditText = (EditText) mainActivity.findViewById(R.id.gridEditText);
        resultEditText = (EditText) mainActivity.findViewById(R.id.resultEditText);
        button = (Button) mainActivity.findViewById(R.id.button);

        gridEditText.setText("1 2 3 4 5");
    }

    @Test
    public void clickResult_shouldChangeResultEditText() {
        button.performClick();
        assertThat(resultEditText.getText().length(), greaterThan(0));
    }
}
