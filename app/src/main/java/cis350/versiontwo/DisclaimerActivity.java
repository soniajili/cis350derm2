package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

/**
 * DisclaimerActivity displays the disclaimer and forces the user to accept before proceeding.
 */
public class DisclaimerActivity extends ActionBarActivity {

    CheckBox checkbox;
    Button disclaimerButton;

    /** Display the page initially */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disclaimer);
        checkbox = (CheckBox) findViewById(R.id.disclaimerCheckBox);
        disclaimerButton = (Button) findViewById(R.id.disclaimerButton);
        disclaimerButton.setEnabled(false);
        disclaimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    /** Inflate the menu; this adds items to the action bar if it is present.*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_disclaimer, menu);
        return true;
    }

    /** Handle action bar item clicks */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /** Enable disclaimer button when checkbox is checked */
    public void onCheckboxClicked(View view) {

        if (checkbox.isChecked()) {
            disclaimerButton.setEnabled(true);
        } else {
            disclaimerButton.setEnabled(false);
        }
    }
}
