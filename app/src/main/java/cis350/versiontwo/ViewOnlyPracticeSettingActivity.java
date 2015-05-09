package cis350.versiontwo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

/**
 * ViewOnlyPracticeSettingActivity allows the user to select additional information about their
 * practice setting when registering a new account.
 */
public class ViewOnlyPracticeSettingActivity extends ActionBarActivity {

    Spinner practiceSettingSpinner;
    Spinner secondarySpinner;
    Button proceedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_only_practice_setting);

        // Add practice settings to the drop-down
        practiceSettingSpinner = (Spinner) findViewById(R.id.view_only_practice_setting_spinner);
        ArrayAdapter<CharSequence> pracSettingAdapter = ArrayAdapter.createFromResource(this,
                R.array.view_only_practice_setting_array, android.R.layout.simple_spinner_dropdown_item);
        pracSettingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        practiceSettingSpinner.setAdapter(pracSettingAdapter);
        practiceSettingSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            // Dynamically set the secondary spinner depending on what the user selected in the
            // primary spinner.
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                secondarySpinner = (Spinner) findViewById(R.id.secondary_setting_spinner);
                ArrayAdapter<CharSequence> secondarySpinnerAdapter;
                if (practiceSettingSpinner.getSelectedItem().toString()
                        .equals("Physician, Primary Care")) {
                    secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.physician_primary_care_array, android.R.layout.simple_spinner_dropdown_item);
                } else if (practiceSettingSpinner.getSelectedItem().toString()
                        .equals("Physician, Specialist/Subspecialist")) {
                    secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.physician_specialist_array, android.R.layout.simple_spinner_dropdown_item);
                } else if (practiceSettingSpinner.getSelectedItem().toString()
                        .equals("Physician, Surgery")) {
                    secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.physician_surgery_array, android.R.layout.simple_spinner_dropdown_item);
                } else if (practiceSettingSpinner.getSelectedItem().toString()
                        .equals("Nurse Practitioner/Physician Assistant, Primary Care")) {
                    secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.np_primary_care_array, android.R.layout.simple_spinner_dropdown_item);
                } else if (practiceSettingSpinner.getSelectedItem().toString()
                        .equals("Nurse Practitioner/Physician Assistant, Specialist/Subspecialist")) {
                    secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.np_specialist_array, android.R.layout.simple_spinner_dropdown_item);
                } else {
                    secondarySpinnerAdapter = ArrayAdapter.createFromResource(ViewOnlyPracticeSettingActivity.this,
                            R.array.empty_array, android.R.layout.simple_spinner_dropdown_item);
                }
                secondarySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                secondarySpinner.setAdapter(secondarySpinnerAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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
