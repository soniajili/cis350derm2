package cis350.versiontwo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class ViewOnlyPracticeSettingActivity extends ActionBarActivity {

    Spinner practiceSettingSpinner;
    Spinner secondarySpinner;
    Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_setting);

        // Add practice settings to the drop-down
        practiceSettingSpinner = (Spinner) findViewById(R.id.practice_setting_spinner);
        ArrayAdapter<CharSequence> pracSettingAdapter = ArrayAdapter.createFromResource(this,
                R.array.practice_setting_array, android.R.layout.simple_spinner_dropdown_item);
        pracSettingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        practiceSettingSpinner.setAdapter(pracSettingAdapter);

        proceedButton = (Button) findViewById(R.id.proceed_button);
        proceedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the practice setting that was selected as a String
                final String pracSetting = practiceSettingSpinner.getSelectedItem().toString();
                Intent intent = new Intent(getApplicationContext(), ViewOnlyDisclaimerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_only_practice_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
